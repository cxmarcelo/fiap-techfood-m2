package br.com.fiap.techfood.core.domain;

import java.util.List;

public class OrderRequestDomain {

	private String orderName;
	private List<OrderItemDomain> requestProducts;

	public OrderRequestDomain() {
		super();
	}

	public OrderRequestDomain(String orderName, List<OrderItemDomain> requestProducts) {
		super();
		this.orderName = orderName;
		this.requestProducts = requestProducts;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public List<OrderItemDomain> getRequestProducts() {
		return requestProducts;
	}

	public void setRequestProducts(List<OrderItemDomain> requestProducts) {
		this.requestProducts = requestProducts;
	}

}
