package br.com.fiap.techfood.core.dataprovider;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.fiap.techfood.core.domain.OrderDomain;
import br.com.fiap.techfood.core.domain.enums.OrderStatusEnum;

public interface OrderDataProvider {

	OrderDomain save(OrderDomain orderDomain);

	Optional<OrderDomain> findById(UUID id);

	List<OrderDomain> findAllByOrderStatus(OrderStatusEnum status);

	void delete(UUID id);

	void updateStatus(UUID id, OrderStatusEnum status);

}
