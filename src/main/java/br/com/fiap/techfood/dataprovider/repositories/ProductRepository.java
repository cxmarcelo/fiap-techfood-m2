package br.com.fiap.techfood.dataprovider.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.techfood.dataprovider.repositories.entities.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

	List<ProductEntity> findByCategory(Integer category);

}
