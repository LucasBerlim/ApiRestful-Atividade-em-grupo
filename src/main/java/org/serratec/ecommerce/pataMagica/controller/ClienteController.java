package org.serratec.ecommerce.pataMagica.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.ecommerce.pataMagica.dto.ClienteDto;
import org.serratec.ecommerce.pataMagica.model.Cliente;
import org.serratec.ecommerce.pataMagica.model.DadosCep;
import org.serratec.ecommerce.pataMagica.model.Endereco;
import org.serratec.ecommerce.pataMagica.service.ClienteService;
import org.serratec.ecommerce.pataMagica.service.ConsumoApiCep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService service;
	
	@GetMapping
	public List<ClienteDto> obterTodos() {
		return service.obterTodos();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteDto> obterPorId(@PathVariable Long id) {
		Optional<ClienteDto> dto = service.obterPorId(id);
		if (!dto.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dto.get());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteDto cadastrarCliente(@RequestBody ClienteDto dto) {
		
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
		
		return service.salvarCliente(ClienteDto.toDto(cliente));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletaCliente(@PathVariable Long id){
		if(!service.apagarCliente(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ClienteDto> alterarCliente(@PathVariable Long id, @RequestBody ClienteDto dto){
		Optional<ClienteDto> clienteAlterado = service.alterarCliente(id, dto);
		if (!clienteAlterado.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(clienteAlterado.get());
	}
}
