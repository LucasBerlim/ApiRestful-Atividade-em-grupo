package org.serratec.ecommerce.pataMagica.service;

import java.util.List;
import java.util.Optional;
import org.serratec.ecommerce.pataMagica.dto.ClienteDto;
import org.serratec.ecommerce.pataMagica.model.Cliente;
import org.serratec.ecommerce.pataMagica.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	public List<ClienteDto> obterTodos(){
		return repository.findAll().stream().map(c -> ClienteDto.toDto(c)).toList();
	}
	
	public Optional<ClienteDto> obterPorId(Long id){
		if(!repository.existsById(id)) {
			return Optional.empty();
		}
		return Optional.of(ClienteDto.toDto(repository.findById(id).get()));
	}
	
	public ClienteDto salvarCliente(ClienteDto dto) {
		Cliente clienteEntity = repository.save(dto.toEntity());
		return ClienteDto.toDto(clienteEntity);
	}
	
	public boolean apagarCliente(Long id) {
		if(!repository.existsById(id)) {
			return false;
		}
		repository.deleteById(id);
		return true;
	}

	public Optional<ClienteDto> alterarCliente(Long id, ClienteDto dto){
		if(!repository.existsById(id)) {
			return Optional.empty();
		}
		Cliente clienteEntity = dto.toEntity();
		clienteEntity.setId(id);
		repository.save(clienteEntity);
		return Optional.of(ClienteDto.toDto(clienteEntity));
	}
}
