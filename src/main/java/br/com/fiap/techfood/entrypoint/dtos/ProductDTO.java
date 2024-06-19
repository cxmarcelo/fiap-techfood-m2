package br.com.fiap.techfood.entrypoint.dtos;

import java.math.BigDecimal;
import java.util.UUID;

import br.com.fiap.techfood.core.domain.enums.CategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class ProductDTO {

	private  UUID id;
	private  String name;
	private  String description;
	private  BigDecimal price;
	private  CategoryEnum category;
	private  String imageURL;

}
