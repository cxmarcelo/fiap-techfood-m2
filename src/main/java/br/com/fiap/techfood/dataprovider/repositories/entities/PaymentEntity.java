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

	@Column(nullable = false, unique = true)
	private String externalId;

	private LocalDateTime dateCreated;
	private LocalDateTime dateApproved;
	private LocalDateTime dateLastUpdated;
	private LocalDateTime dateOfExpiration;
	private BigDecimal amount;
	private String qrCode;
	private Integer status;

	@Column(nullable = false)
	private LocalDateTime internalCreationDate;

	@Column(nullable = false)
	private LocalDateTime internalLastUpdateDate;

	@PrePersist
	private void beforePersist() {
		this.internalCreationDate = LocalDateTime.now();
		this.internalLastUpdateDate = LocalDateTime.now();
	}

	@PreUpdate
	private void beforeUpdate() {
		this.internalLastUpdateDate = LocalDateTime.now();
	}

	public PaymentStatusEnum getStatus() {
		return PaymentStatusEnum.toEnum(this.status);
	}

	public void setStatus(PaymentStatusEnum status) {
		this.status = status.getCode();
	}

}
