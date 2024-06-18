package br.com.fiap.techfood.dataprovider.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.techfood.dataprovider.repositories.entities.OrderEntity;
import br.com.fiap.techfood.dataprovider.repositories.entities.OrderItemEntity;
import br.com.fiap.techfood.dataprovider.repositories.entities.OrderItemPk;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, OrderItemPk>{

	List<OrderItemEntity> findAllByIdOrder(OrderEntity order);
	
}
