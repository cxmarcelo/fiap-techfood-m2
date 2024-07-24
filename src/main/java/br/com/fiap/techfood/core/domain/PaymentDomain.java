package br.com.fiap.techfood.core.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import br.com.fiap.techfood.core.domain.enums.PaymentStatusEnum;

public class PaymentDomain {

	private UUID id;
	private String externalId;
	private LocalDateTime dateCreated;
	private LocalDateTime dateApproved;
	private LocalDateTime dateLastUpdated;
	private LocalDateTime dateOfExpiration;
	private BigDecimal amount;
	private String qrCode;
	private PaymentStatusEnum status;

	private LocalDateTime internalCreationDate;
	private LocalDateTime internalLastUpdateDate;

	public PaymentDomain() {
		super();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public LocalDateTime getDateApproved() {
		return dateApproved;
	}

	public void setDateApproved(LocalDateTime dateApproved) {
		this.dateApproved = dateApproved;
	}

	public LocalDateTime getDateLastUpdated() {
		return dateLastUpdated;
	}

	public void setDateLastUpdated(LocalDateTime dateLastUpdated) {
		this.dateLastUpdated = dateLastUpdated;
	}

	public LocalDateTime getDateOfExpiration() {
		return dateOfExpiration;
	}

	public void setDateOfExpiration(LocalDateTime dateOfExpiration) {
		this.dateOfExpiration = dateOfExpiration;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public PaymentStatusEnum getStatus() {
		return status;
	}

	public void setStatus(PaymentStatusEnum status) {
		this.status = status;
	}

	public LocalDateTime getInternalCreationDate() {
		return internalCreationDate;
	}

	public void setInternalCreationDate(LocalDateTime internalCreationDate) {
		this.internalCreationDate = internalCreationDate;
	}

	public LocalDateTime getInternalLastUpdateDate() {
		return internalLastUpdateDate;
	}

	public void setInternalLastUpdateDate(LocalDateTime internalLastUpdateDate) {
		this.internalLastUpdateDate = internalLastUpdateDate;
	}

}
