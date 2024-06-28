package br.com.fiap.techfood.dataprovider.repositories.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.fiap.techfood.core.domain.enums.PaymentStatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_PAYMENTS")
public class PaymentEntity {

	@EmbeddedId
	private PaymentPk id;

	private LocalDateTime paymentDateCreated;
	private LocalDateTime paymentDateApproved;
	private LocalDateTime paymentDateLastUpdated;
	private LocalDateTime paymentDateOfExpiration;
	private BigDecimal amount;
	private String qrCode;
	private PaymentStatusEnum status;

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

}
