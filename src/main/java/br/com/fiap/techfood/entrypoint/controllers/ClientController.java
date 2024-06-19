package br.com.fiap.techfood.entrypoint.controllers;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.techfood.core.domain.PageInfo;
import br.com.fiap.techfood.core.usecase.ClientUseCase;
import br.com.fiap.techfood.entrypoint.dtos.ClientDTO;
import br.com.fiap.techfood.entrypoint.dtos.ClientResponseDTO;
import br.com.fiap.techfood.entrypoint.mappers.ClientMapper;

@RestController
@RequestMapping("/clients")
public class ClientController {

	@Autowired
	private ClientUseCase clientUseCase;

	@Autowired
	private ClientMapper clientMapper;


	@PostMapping
	public ResponseEntity<ClientResponseDTO> save(@RequestBody @Validated ClientDTO clientDTO)  {
		var clientDomain = clientMapper.toClientDomain(clientDTO);
		var clientDomainSaved = clientUseCase.save(clientDomain);
		var clientDTOSaved = clientMapper.toClientResponseDTO(clientDomainSaved);
		return ResponseEntity.status(HttpStatus.CREATED).body(clientDTOSaved);
	}

	@GetMapping("/{clientId}")
	ResponseEntity<ClientResponseDTO> findById(@PathVariable(value = "clientId") UUID clientId) {
		var clientDomain = clientUseCase.findById(clientId);
		var clientDTO = clientMapper.toClientResponseDTO(clientDomain);
		return ResponseEntity.status(HttpStatus.OK).body(clientDTO);
	}

	@GetMapping
	public ResponseEntity<Page<ClientResponseDTO>> findAllClients(
			@PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC) Pageable pageable) {
		var pageInfo = new PageInfo();
		BeanUtils.copyProperties(pageable, pageInfo);

		var clientDomainList = clientUseCase.findAll(pageInfo);

		var clientDTOList = clientDomainList.stream().map(domain -> clientMapper.toClientResponseDTO(domain)).toList();

		return ResponseEntity.status(HttpStatus.OK).body(new PageImpl<ClientResponseDTO>(clientDTOList, pageable, clientDomainList.size()));
	}

	//TODO MOVER ESTA LOGICA PARA USECASE
	@DeleteMapping("/{clientId}")
	public ResponseEntity<Object> deleteClient(@PathVariable(value = "clientId") UUID clientId) {
		var clientDomain = clientUseCase.findById(clientId);
		clientUseCase.delete(clientDomain);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Client deleted successfully.");
	}


	//TODO MOVER ESTA LOGICA PARA USECASE
	@PutMapping("/{clientId}")
	ResponseEntity<Object> updateClient(
			@PathVariable(value = "clientId") UUID clientId, 
			@RequestBody @Validated ClientDTO clientDto)  {

		var clientDomain = clientUseCase.findById(clientId);

		clientDomain.setName(clientDto.getName());
		clientDomain.setCpf(clientDto.getCpf());
		clientDomain.setEmail(clientDto.getEmail());

		var clientDomainSaved = clientUseCase.save(clientDomain);
		var clientDTO = clientMapper.toClientResponseDTO(clientDomainSaved);

		return ResponseEntity.status(HttpStatus.OK).body(clientDTO);
	}

	@GetMapping("cpf/{cpf}")
	public ResponseEntity<ClientResponseDTO> findByCpf(@PathVariable(value = "cpf") String clientCpf) {
		var clientDomain = clientUseCase.findByCpf(clientCpf);
		var clientDTO = clientMapper.toClientResponseDTO(clientDomain);
		return ResponseEntity.status(HttpStatus.OK).body(clientDTO);
	}

}
