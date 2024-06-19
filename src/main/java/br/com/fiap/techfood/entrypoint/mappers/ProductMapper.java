package br.com.fiap.techfood.entrypoint.mappers;

import org.springframework.stereotype.Component;

import br.com.fiap.techfood.core.domain.ProductDomain;
import br.com.fiap.techfood.entrypoint.dtos.ProductCreateDTO;
import br.com.fiap.techfood.entrypoint.dtos.ProductDTO;

@Component
public class ProductMapper {

	//TODO talvez padronizar para não lançar esse illegalArgumentException

	public public ProductDomain dtoToDomain(ProductDTO productDto) {
		if (productDto == null) {
			throw new IllegalArgumentException("ProductDTO cannot be null");
		}

		return new ProductDomain(
				productDto.getName(),
				productDto.getDescription(),
				productDto.getPrice(),
				productDto.getCategory(),
				productDto.getImageURL()
				);
	}

	public ProductDomain toProductDomain(ProductCreateDTO productCreateDTO)  {
		if (productCreateDTO == null) {
			throw new IllegalArgumentException("ProductDTO cannot be null");
		}

		return new ProductDomain(
				productCreateDTO.getName(),
				productCreateDTO.getDescription(),
				productCreateDTO.getPrice(),
				productCreateDTO.getCategory(),
				productCreateDTO.getImageURL()
				);
	}

	public ProductDTO domainToDto(ProductDomain product) {
		if (product == null) {
			throw new IllegalArgumentException("ProductEntity cannot be null");
		}

		return new ProductDTO(
				product.getId(),
				product.getName(),
				product.getDescription(),
				product.getPrice(),
				product.getCategory(),
				product.getImageURL()
				);
	}

}
