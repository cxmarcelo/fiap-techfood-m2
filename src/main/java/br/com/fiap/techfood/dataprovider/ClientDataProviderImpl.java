package br.com.fiap.techfood.dataprovider;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import br.com.fiap.techfood.core.dataprovider.ClientDataProvider;
import br.com.fiap.techfood.core.domain.ClientDomain;
import br.com.fiap.techfood.core.domain.PageInfo;
import br.com.fiap.techfood.core.domain.exceptions.DataIntegrityException;
import br.com.fiap.techfood.dataprovider.repositories.ClientRepository;
import br.com.fiap.techfood.dataprovider.repositories.mappers.ClientEntityMapper;
import jakarta.transaction.Transactional;

@Component
public class ClientDataProviderImpl implements ClientDataProvider {

	@Autowired
	private ClientEntityMapper clientEntityMapper;

	@Autowired
	private ClientRepository clientRepository;


	//TODO MOVER ESTA REGRA PARA USECASE
	@Override
	@Transactional
	public ClientDomain save(ClientDomain clientDomain) {
		try {
			var clientEntity = clientEntityMapper.toClientEntity(clientDomain);
			var clientEntitySaved = clientRepository.save(clientEntity);
			clientRepository.flush();
			return clientEntityMapper.toClientDomain(clientEntitySaved);

		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Email or CPF already exists", e);
		}
	}

	@Override
	public Optional<ClientDomain> findById(UUID id) {
		return clientRepository.findById(id).map(clientEntity -> clientEntityMapper.toClientDomain(clientEntity));
	}

	@Override
	public Optional<ClientDomain> findByCpf(String cpf) {
		return clientRepository.findByCpf(cpf).map(clientEntity -> clientEntityMapper.toClientDomain(clientEntity));
	}

	@Override
	public List<ClientDomain> findAll(PageInfo pageInfo) {
		return clientRepository.findAll().stream().map(clientEntity -> clientEntityMapper.toClientDomain(clientEntity)).toList();
	}

	//TODO passar somente id
	@Transactional
	@Override
	public void delete(ClientDomain clientDomain) {
		clientRepository.deleteById(clientDomain.getId());
	}

}
