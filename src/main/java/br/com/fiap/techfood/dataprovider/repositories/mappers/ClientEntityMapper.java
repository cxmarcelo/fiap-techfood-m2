package br.com.fiap.techfood.dataprovider.repositories.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.fiap.techfood.core.domain.ClientDomain;
import br.com.fiap.techfood.dataprovider.repositories.entities.ClientEntity;

@Component
public class ClientEntityMapper {

	public ClientDomain toClientDomain(ClientEntity clientEntity) {
		return new ClientDomain(
				clientEntity.getId(),
				clientEntity.getCpf(),
				clientEntity.getName(),
				clientEntity.getEmail()
				);
	}

	public ClientEntity toClientEntity(ClientDomain clientDomain) {
		var clientEntity = new ClientEntity();
		BeanUtils.copyProperties(clientDomain, clientEntity);
		return clientEntity;
	}

}
