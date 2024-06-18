package br.com.fiap.techfood.core.domain;

import java.util.List;
import java.util.UUID;

import br.com.fiap.techfood.core.domain.enums.OrderStatusEnum;

public class OrderDomain {

	private  UUID id;
	private  String name;
	private  List<OrderItemDomain> items;
	private  OrderStatusEnum status = OrderStatusEnum.AWAITING_PAYMENT;
	private  Boolean isAnonymous = true;
	private  ClientDomain client;

	public OrderDomain() {
		super();
	}

	public OrderDomain(String name, List<OrderItemDomain> items, OrderStatusEnum status, Boolean isAnonymous,
			ClientDomain client) {
		super();
		this.name = name;
		this.items = items;
		this.status = status;
		this.isAnonymous = isAnonymous;
		this.client = client;
	}

	public OrderDomain(UUID id, String name, List<OrderItemDomain> items, OrderStatusEnum status, Boolean isAnonymous,
			ClientDomain client) {
		super();
		this.id = id;
		this.name = name;
		this.items = items;
		this.status = status;
		this.isAnonymous = isAnonymous;
		this.client = client;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<OrderItemDomain> getItems() {
		return items;
	}

	public void setItems(List<OrderItemDomain> items) {
		this.items = items;
	}

	public OrderStatusEnum getStatus() {
		return status;
	}

	public void setStatus(OrderStatusEnum status) {
		this.status = status;
	}

	public Boolean getIsAnonymous() {
		return isAnonymous;
	}

	public void setIsAnonymous(Boolean isAnonymous) {
		this.isAnonymous = isAnonymous;
	}

	public ClientDomain getClient() {
		return client;
	}

	public void setClient(ClientDomain client) {
		this.client = client;
	}

}
