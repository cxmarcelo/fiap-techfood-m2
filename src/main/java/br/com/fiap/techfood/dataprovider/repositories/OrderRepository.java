package br.com.fiap.techfood.dataprovider.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.techfood.dataprovider.repositories.entities.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {

	List<OrderEntity> findAllByStatus(Integer status);

}
