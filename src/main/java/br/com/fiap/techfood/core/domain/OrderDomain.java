package br.com.fiap.techfood.core.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.fiap.techfood.core.domain.enums.OrderStatusEnum;

public class OrderDomain {

	private UUID id;
	private String name;
	private List<OrderItemDomain> items;
	private OrderStatusEnum status = OrderStatusEnum.ORDER_CREATED;
	private Boolean isAnonymous = true;
	private ClientDomain client;
	private List<PaymentDomain> payments;
	private LocalDateTime creationDate;
	private LocalDateTime lastUpdateDate;

	public OrderDomain() {
		super();
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

	public List<PaymentDomain> getPayments() {
		if(payments == null) {
			payments = new ArrayList<PaymentDomain>();
		}
		return payments;
	}

	public void setPayments(List<PaymentDomain> payments) {
		this.payments = payments;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public LocalDateTime getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public BigDecimal getTotal() {
		if(this.items == null || this.items.size() == 0) {
			return BigDecimal.ZERO;
		}

		BigDecimal total = BigDecimal.ZERO;
		for (OrderItemDomain orderItemDomain : items) {
			total = total.add(orderItemDomain.getTotal());
		}

		return total;
	}

}
