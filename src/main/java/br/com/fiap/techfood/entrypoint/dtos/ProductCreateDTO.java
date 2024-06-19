package br.com.fiap.techfood.entrypoint.dtos;

import java.math.BigDecimal;

import br.com.fiap.techfood.core.domain.enums.CategoryEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class ProductCreateDTO {

	@NotBlank
	private String name;

	@NotBlank
	private String description;

	@NotNull
	private BigDecimal price;

	@NotNull
	private CategoryEnum category;

	@NotBlank
	private String imageURL;

}
