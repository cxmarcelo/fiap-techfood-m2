package br.com.fiap.techfood.core.dataprovider;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.fiap.techfood.core.domain.ClientDomain;
import br.com.fiap.techfood.core.domain.PageInfo;

public interface ClientDataProvider {

	ClientDomain save(ClientDomain clientDomain);

	Optional<ClientDomain> findById(UUID id);

	Optional<ClientDomain> findByCpf(String cpf);

	List<ClientDomain> findAll(PageInfo pageInfo);

	void delete(ClientDomain clientDomain);

}
