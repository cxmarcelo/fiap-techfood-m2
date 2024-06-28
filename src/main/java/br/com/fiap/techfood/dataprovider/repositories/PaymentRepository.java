package br.com.fiap.techfood.dataprovider.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.techfood.dataprovider.repositories.entities.OrderEntity;
import br.com.fiap.techfood.dataprovider.repositories.entities.PaymentEntity;

public interface PaymentRepository extends JpaRepository<PaymentEntity, OrderEntity> {


}
