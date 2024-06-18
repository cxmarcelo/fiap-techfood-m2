package br.com.fiap.techfood.core.usecase;

import java.util.List;
import java.util.UUID;

import br.com.fiap.techfood.core.domain.ClientDomain;
import br.com.fiap.techfood.core.domain.PageInfo;

public interface ClientUseCase {

	ClientDomain save(ClientDomain clientDomain);

	ClientDomain findById(UUID id);

	ClientDomain findByCpf(String cpf);

	List<ClientDomain> findAll(PageInfo pageInfo);

	void delete(ClientDomain clientDomain);

}
