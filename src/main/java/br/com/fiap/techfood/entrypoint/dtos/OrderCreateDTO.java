package br.com.fiap.techfood.entrypoint.dtos;

import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class OrderCreateDTO {

	@NotNull
	private List<OrderItemDto> orderItems;

	@NotBlank
	@Length(min = 3)
	private String orderName;

	@CPF
	private String clientCpf;

}
