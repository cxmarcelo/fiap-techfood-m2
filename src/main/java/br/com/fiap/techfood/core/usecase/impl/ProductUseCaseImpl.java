package br.com.fiap.techfood.core.usecase.impl;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import br.com.fiap.techfood.core.dataprovider.ProductDataProvider;
import br.com.fiap.techfood.core.domain.ProductDomain;
import br.com.fiap.techfood.core.domain.enums.CategoryEnum;
import br.com.fiap.techfood.core.domain.exceptions.ObjectNotFoundException;
import br.com.fiap.techfood.core.usecase.ProductUseCase;

public class ProductUseCaseImpl implements ProductUseCase {

	private final ProductDataProvider productDataProvider;

	public ProductUseCaseImpl(ProductDataProvider productDataProvider) {
		this.productDataProvider = productDataProvider;
	}

	@Override
	public ProductDomain registerNewProduct(ProductDomain product) {
		product.setId(null);
		return productDataProvider.save(product);
	}

	@Override
	public ProductDomain updateProduct(UUID id, ProductDomain productToUpdate) {
		this.searchProductById(id);
		productToUpdate.setId(id);
		return productDataProvider.save(productToUpdate);
	}

	@Override
	public ProductDomain searchProductById(UUID id) {
		var productDomainOpt = productDataProvider.findById(id);
		if (productDomainOpt.isEmpty()) {
			throw new ObjectNotFoundException("Product with " + id + " not found.");
		}
		return productDomainOpt.get();
	}

	@Override
	public List<ProductDomain> searchProductByCategory(CategoryEnum category) {
		return productDataProvider.findByCategory(category);
	}

	@Override
	public List<ProductDomain> findAll() {
		return productDataProvider.findAll();
	}

	@Override
	public List<ProductDomain> findAllByIds(Set<UUID> ids) {
		return productDataProvider.findAllByIds(ids);
	}

	@Override
	public void deleteProduct(UUID id) {
		this.searchProductById(id);
		productDataProvider.delete(id);
	}

}
