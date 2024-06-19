package br.com.fiap.techfood.entrypoint.dtos;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

	@CPF(message = "Please enter a valid CPF number.")
	@NotBlank(message = "CPF could not be null")
	private String cpf;

	@NotBlank(message = "Name could not be blank")
	private String name;

	@Email(message = "Please enter a valid email")
	@NotBlank(message = "Email could not be blank")
	private String email;

}
