package org.serratec.ecommerce.pataMagica.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.ecommerce.pataMagica.dto.ClienteDto;
import org.serratec.ecommerce.pataMagica.service.ClienteService;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService service;

	@GetMapping
	@Operation(summary = "Retorna todos clientes.", description = "Dado um determinado id, será retornado os clientes com as informações gerais.")
	@ApiResponse(responseCode = "200", description = "Clientes localizados")
	public List<ClienteDto> obterTodos() {
		return service.obterTodos();
	}

	@GetMapping("/{id}")
	@Operation(summary = "Retorna um cliente por id", description = "Dado um determinado id, será retornado o cliente com as informações gerais.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "404", description = "Não foi encontrado o cliente pelo id informado. Verifique!"),
			@ApiResponse(responseCode = "200", description = "Cliente localizado.") })
	public ResponseEntity<ClienteDto> obterPorId(@PathVariable Long id) {
		Optional<ClienteDto> dto = service.obterPorId(id);
		if (!dto.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dto.get());
	}

	@PostMapping
	@Operation(summary = "Cadastra um novo cliente", description = "Criar um novo cliente e retornar os detalhes do cliente criado")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "400", description = "Formato incorreto. Verifique!"),
			@ApiResponse(responseCode = "201", description = "Cliente localizado.")
	})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> cadastrarCliente(@RequestBody ClienteDto dto) {
		//ClienteDto clienteDto = service.salvarCliente(dto);
		
		return service.salvarCliente(dto);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deletar um cliente", description = "Apagar um cliente de acordo com o id fornecido")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "404", description = "Não foi encontrado o cliente pelo id informado. Verifique!"),
			@ApiResponse(responseCode = "200", description = "Cliente deletado") })
	public ResponseEntity<Void> deletaCliente(@PathVariable Long id) {
		if (!service.apagarCliente(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	@Operation(summary = "Alterar um cliente", description = "Apagar um cliente de acordo com o id fornecido")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "404", description = "Não foi encontrado o cliente pelo id informado. Verifique!"),
			@ApiResponse(responseCode = "200", description = "Cliente alterado") })
	public ResponseEntity<ClienteDto> alterarCliente(@PathVariable Long id, @RequestBody ClienteDto dto) {
		Optional<ClienteDto> clienteAlterado = service.alterarCliente(id, dto);
		if (!clienteAlterado.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(clienteAlterado.get());
	}
}
