package br.com.fiap.techfood.core.usecase.impl;

import java.util.List;
import java.util.UUID;

import br.com.fiap.techfood.core.dataprovider.ClientDataProvider;
import br.com.fiap.techfood.core.domain.ClientDomain;
import br.com.fiap.techfood.core.domain.PageInfo;
import br.com.fiap.techfood.core.domain.exceptions.ObjectNotFoundException;
import br.com.fiap.techfood.core.usecase.ClientUseCase;

public class ClientUseCaseImpl implements ClientUseCase {

	private final ClientDataProvider clientDataProvider;

	public ClientUseCaseImpl(ClientDataProvider clientDataProvider) {
		this.clientDataProvider = clientDataProvider;
	}

	@Override
	public ClientDomain save(ClientDomain clientDomain) {
		return clientDataProvider.save(clientDomain);
	}

	@Override
	public ClientDomain findById(UUID id) {
		var clientEntityOptional = clientDataProvider.findById(id);
		if (clientEntityOptional.isEmpty()) {
			throw new ObjectNotFoundException("Client with id " + id + " not found.");
		}
		return clientEntityOptional.get();
	}

	@Override
	public ClientDomain findByCpf(String cpf) {
		var clientEntityOptional = clientDataProvider.findByCpf(cpf);
		if (clientEntityOptional.isEmpty()) {
			throw new ObjectNotFoundException("Client with cpf " + cpf + " not found.");
		}
		return clientEntityOptional.get();
	}

	@Override
	public List<ClientDomain> findAll(PageInfo pageInfo) {
		return clientDataProvider.findAll(pageInfo);
	}

	@Override
	public void delete(ClientDomain clientDomain) {
		clientDataProvider.delete(clientDomain);
	}

}
