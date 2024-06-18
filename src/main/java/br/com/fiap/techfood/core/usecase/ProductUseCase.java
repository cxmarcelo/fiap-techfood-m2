package br.com.fiap.techfood.core.usecase;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import br.com.fiap.techfood.core.domain.ProductDomain;
import br.com.fiap.techfood.core.domain.enums.CategoryEnum;

public interface ProductUseCase {

	ProductDomain registerNewProduct(ProductDomain product);

	ProductDomain updateProduct(UUID id, ProductDomain productToUpdate);

	ProductDomain searchProductById(UUID id);

	List<ProductDomain> searchProductByCategory(CategoryEnum category);

	List<ProductDomain> findAll();

	List<ProductDomain> findAllByIds(Set<UUID> ids);

	void deleteProduct(UUID id);

}
