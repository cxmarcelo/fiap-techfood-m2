package br.com.fiap.techfood.dataprovider.repositories.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.fiap.techfood.core.domain.ProductDomain;
import br.com.fiap.techfood.dataprovider.repositories.entities.ProductEntity;

@Component
public class ProductEntityMapper {

	public ProductDomain toProductDomain(ProductEntity productEntity) {
		return new ProductDomain(
				productEntity.getId(),
				productEntity.getName(),
				productEntity.getDescription(),
				productEntity.getPrice(),
				productEntity.getCategory(),
				productEntity.getImageURL()
				);
	}

	public ProductEntity toProductEntity(ProductDomain productDomain) {
		var productEntity = new ProductEntity();
		BeanUtils.copyProperties(productDomain, productEntity);
		return productEntity;
	}

}
