package br.com.fiap.techfood.core.dataprovider;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import br.com.fiap.techfood.core.domain.ProductDomain;
import br.com.fiap.techfood.core.domain.enums.CategoryEnum;

public interface ProductDataProvider {

	ProductDomain save(ProductDomain product);

	Optional<ProductDomain> findById(UUID id);

	List<ProductDomain> findByCategory(CategoryEnum category);

	List<ProductDomain> findAll();

	void delete(UUID id);

	List<ProductDomain> findAllByIds(Set<UUID> ids);

}
