package br.com.fiap.techfood.dataprovider;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.techfood.core.dataprovider.ProductDataProvider;
import br.com.fiap.techfood.core.domain.ProductDomain;
import br.com.fiap.techfood.core.domain.enums.CategoryEnum;
import br.com.fiap.techfood.dataprovider.repositories.ProductRepository;
import br.com.fiap.techfood.dataprovider.repositories.mappers.ProductEntityMapper;
import jakarta.transaction.Transactional;

@Component
public class ProductDataProviderImpl implements ProductDataProvider {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductEntityMapper productEntityMapper;

	@Override
	@Transactional
	public ProductDomain save(ProductDomain product) {
		var productEntity = productEntityMapper.toProductEntity(product);
		var newProductEntity = productRepository.save(productEntity);
		return productEntityMapper.toProductDomain(newProductEntity);
	}

	@Override
	public Optional<ProductDomain> findById(UUID id) {
		var productEntityOpt = productRepository.findById(id);
		return productEntityOpt.map(productEntity -> productEntityMapper.toProductDomain(productEntity));
	}

	@Override
	public List<ProductDomain> findByCategory(CategoryEnum category) {
		var productEntities = productRepository.findByCategory(category.getCode());
		return productEntities.stream().map(productEntity -> productEntityMapper.toProductDomain(productEntity)).toList();
	}

	@Override
	public List<ProductDomain> findAll() {
		var products = productRepository.findAll();
		return products.stream().map(productEntity -> productEntityMapper.toProductDomain(productEntity)).toList();
	}

	@Override
	public void delete(UUID id) {
		productRepository.deleteById(id);
	}

	@Override
	public List<ProductDomain> findAllByIds(Set<UUID> ids) {
		var products = productRepository.findAllById(ids);
		return products.stream().map(productEntity -> productEntityMapper.toProductDomain(productEntity)).toList();
	}

}
