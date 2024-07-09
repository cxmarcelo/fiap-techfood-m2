package br.com.fiap.techfood.dataprovider.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.techfood.dataprovider.repositories.entities.PaymentEntity;
import br.com.fiap.techfood.dataprovider.repositories.entities.PaymentPk;

public interface PaymentRepository extends JpaRepository<PaymentEntity, PaymentPk> {


}
