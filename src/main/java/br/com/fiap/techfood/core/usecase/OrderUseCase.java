package br.com.fiap.techfood.core.usecase;

import java.util.List;
import java.util.UUID;

import br.com.fiap.techfood.core.domain.OrderDomain;
import br.com.fiap.techfood.core.domain.OrderRequestDomain;
import br.com.fiap.techfood.core.domain.enums.OrderStatusEnum;

public interface OrderUseCase {

	OrderDomain save(OrderRequestDomain orderRequest, String clientCpf);

	OrderDomain findById(UUID id);

	List<OrderDomain> findAllByStatus(OrderStatusEnum orderStatus);

	void delete(UUID id);

	String approvePayment(UUID id);

	void prepareOrder(UUID id);

	void finishOrder(UUID id);

}
