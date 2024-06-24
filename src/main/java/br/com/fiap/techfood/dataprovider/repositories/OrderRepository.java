package br.com.fiap.techfood.dataprovider.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.techfood.dataprovider.repositories.entities.OrderEntity;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {

	List<OrderEntity> findAllByStatus(Integer status);

	@Query("SELECT order FROM OrderEntity order WHERE order.status != 5 AND order.status != 9 ORDER BY order.status, order.creationDate ASC")
	List<OrderEntity> findAllActiveOrders();

}