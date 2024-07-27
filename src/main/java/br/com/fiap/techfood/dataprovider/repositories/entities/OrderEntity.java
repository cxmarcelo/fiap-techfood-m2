package br.com.fiap.techfood.dataprovider.repositories.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.fiap.techfood.core.domain.enums.OrderStatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_ORDERS")
public class OrderEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Integer status;

	private Boolean isAnonymous;

	@OneToMany(mappedBy = "id.order")
	private List<OrderItemEntity> items = new ArrayList<OrderItemEntity>();
	
	@OneToMany(mappedBy = "id.order")
	private List<PaymentEntity> payments;

	@ManyToOne
	@JoinColumn(name = "client_id", foreignKey = @ForeignKey(name="fk_order_client"))
	private ClientEntity client;

	@Column(nullable = false)
	private LocalDateTime creationDate;

	@Column(nullable = false)
	private LocalDateTime lastUpdateDate;

	@PrePersist
	private void beforePersist() {
		this.creationDate = LocalDateTime.now();
		this.lastUpdateDate = LocalDateTime.now();
	}

	@PreUpdate
	private void beforeUpdate() {
		this.lastUpdateDate = LocalDateTime.now();
	}

	public OrderStatusEnum getStatus() {
		return OrderStatusEnum.toEnum(this.status);
	}

	public void setStatus(OrderStatusEnum status) {
		this.status = status.getCode();
	}

}
