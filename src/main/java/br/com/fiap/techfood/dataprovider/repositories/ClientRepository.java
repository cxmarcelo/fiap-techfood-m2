package br.com.fiap.techfood.dataprovider.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.techfood.dataprovider.repositories.entities.ClientEntity;

public interface ClientRepository extends JpaRepository<ClientEntity, UUID> {

	Optional<ClientEntity> findByCpf(String cpf);

}
