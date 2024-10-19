package org.serratec.ecommerce.pataMagica.service;

import java.util.List;
import java.util.Optional;
import org.serratec.ecommerce.pataMagica.dto.ClienteDto;
import org.serratec.ecommerce.pataMagica.model.Cliente;
import org.serratec.ecommerce.pataMagica.model.DadosCep;
import org.serratec.ecommerce.pataMagica.model.Endereco;
import org.serratec.ecommerce.pataMagica.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

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
        String json = ConsumoApiCep.obterDados(dto.toEntity().getEndereco().getCep());
        DadosCep dadosCep = new Gson().fromJson(json, DadosCep.class);

        Endereco endereco = new Endereco();
        endereco.setCep(dto.toEntity().getEndereco().getCep());
        endereco.setRua(dadosCep.rua());
        endereco.setBairro(dadosCep.bairro());
        endereco.setCidade(dadosCep.cidade());
        endereco.setUf(dadosCep.uf());
        endereco.setNumero(dto.toEntity().getEndereco().getNumero());
        endereco.setComplemento(dto.toEntity().getEndereco().getComplemento());

        Cliente cliente = dto.toEntity();
        cliente.setEndereco(endereco);

        Cliente clienteEntity = repository.save(cliente);
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
