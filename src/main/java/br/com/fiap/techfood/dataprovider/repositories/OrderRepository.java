package br.com.fiap.techfood.dataprovider.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.techfood.dataprovider.repositories.entities.OrderEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {

	List<OrderEntity> findAllByStatus(Integer status);

	@Query("SELECT order FROM OrderEntity order WHERE order.status NOT IN (5, 9) ORDER BY order.status, order.creationDate ASC")
	List<OrderEntity> findAllActiveOrders();

	@Query("SELECT order FROM OrderEntity order JOIN order.payments payment WHERE payment.externalId = :externalId")
	Optional<OrderEntity> findOrderByPaymentExternalId(@Param("externalId") String externalId);

}