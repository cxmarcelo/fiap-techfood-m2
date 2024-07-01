package br.com.fiap.techfood.dataprovider;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.techfood.core.dataprovider.OrderDataProvider;
import br.com.fiap.techfood.core.domain.OrderDomain;
import br.com.fiap.techfood.core.domain.enums.OrderStatusEnum;
import br.com.fiap.techfood.dataprovider.repositories.ClientRepository;
import br.com.fiap.techfood.dataprovider.repositories.OrderItemRepository;
import br.com.fiap.techfood.dataprovider.repositories.OrderRepository;
import br.com.fiap.techfood.dataprovider.repositories.mappers.OrderEntityMapper;
import jakarta.transaction.Transactional;

@Component
public class OrderDataProviderImpl implements OrderDataProvider {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private OrderEntityMapper orderEntityMapper;

	@Autowired
	private ClientRepository clientRepository;

	@Override
	@Transactional
	public OrderDomain save(OrderDomain orderDomain) {
		var orderEntity = orderEntityMapper.toOrderEntity(orderDomain);

		if (orderDomain.getClient() != null && orderDomain.getClient().getId() != null) {
			orderEntity.setClient(clientRepository.findById(orderDomain.getClient().getId()).get());
		}

		orderEntity = orderRepository.save(orderEntity);

		var orderItemsEntity = orderEntityMapper.toOrderItemEntityList(orderDomain.getItems(), orderEntity);
		orderEntity.setItems(orderItemsEntity);
		orderItemRepository.saveAll(orderItemsEntity);

		return orderEntityMapper.toOrderDomain(orderEntity);
	}

	@Override
	public Optional<OrderDomain> findById(UUID id) {
		var optional = orderRepository.findById(id);
		return optional.map(orderEntity -> orderEntityMapper.toOrderDomain(orderEntity));
	}

	@Override
	public List<OrderDomain> findAllByOrderStatus(OrderStatusEnum status) {
		var orderEntityList = orderRepository.findAllByStatus(status.getCode());
		return orderEntityList.stream().map(orderEntity -> orderEntityMapper.toOrderDomain(orderEntity)).toList();
	}

	@Override
	public List<OrderDomain> findAllActive() {
		var orderEntityList = orderRepository.findAllActiveOrders();
		return orderEntityList.stream().map(orderEntity -> orderEntityMapper.toOrderDomain(orderEntity)).toList();
	}

	@Override
	public void delete(UUID id) {
		var orderEntityOpt = orderRepository.findById(id);

		if (orderEntityOpt.isPresent()) {
			var orderItemEntityList = orderItemRepository.findAllByIdOrder(orderEntityOpt.get());
			if (!orderItemEntityList.isEmpty()) {
				orderItemRepository.deleteAll(orderItemEntityList);
			}
			orderRepository.deleteById(id);
		}
	}

	@Override
	public void updateStatus(UUID id, OrderStatusEnum status) {
		var optOrderEntity = orderRepository.findById(id);
		if (optOrderEntity.isPresent()) {
			var orderEntity = optOrderEntity.get();
			orderEntity.setStatus(status);
			orderRepository.save(orderEntity);
		}
	}

}
