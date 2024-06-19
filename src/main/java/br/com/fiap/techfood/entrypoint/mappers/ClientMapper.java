package br.com.fiap.techfood.entrypoint.mappers;

import org.springframework.stereotype.Component;

import br.com.fiap.techfood.core.domain.ClientDomain;
import br.com.fiap.techfood.entrypoint.dtos.ClientDTO;
import br.com.fiap.techfood.entrypoint.dtos.ClientResponseDTO;

@Component
public class ClientMapper {

	public ClientDomain toClientDomain(ClientDTO clientDTO)  {
		return new ClientDomain(
				null,
				clientDTO.getCpf(),
				clientDTO.getName(),
				clientDTO.getEmail()
				);
	}

	public ClientDTO toClientDTO(ClientDomain clientDomain) {
		return new ClientDTO(
				clientDomain.getCpf(),
				clientDomain.getName(),
				clientDomain.getEmail()
				);
	}

	public ClientResponseDTO toClientResponseDTO(ClientDomain clientDomain) {
		return new ClientResponseDTO(
				clientDomain.getId(),
				clientDomain.getCpf(),
				clientDomain.getName(),
				clientDomain.getEmail()
				);
	}

}
