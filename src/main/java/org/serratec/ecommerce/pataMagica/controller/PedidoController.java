package org.serratec.ecommerce.pataMagica.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.ecommerce.pataMagica.dto.PedidoDto;
import org.serratec.ecommerce.pataMagica.dto.PedidoDtoCadastroPedido;
import org.serratec.ecommerce.pataMagica.dto.RelatorioPedidoDto;
import org.serratec.ecommerce.pataMagica.service.PedidoService;
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
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService service;

	@GetMapping
	@Operation(summary = "Retorna todos os pedidos.", description = "Dado um determinado id, será retornado os pedidos com as informações gerais do pedido e os itens contidos no pedido.")
	@ApiResponse(responseCode = "200", description = "Pedidos localizados")
	public List<PedidoDto> obterTodos() {
		return service.obterTodos();
	}

	@GetMapping("/{id}")
	@Operation(summary = "Retorna um pedido por id", description = "Dado um determinado id, será retornado o pedido com as informações gerais do pedido e os itens contidos no pedido.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "404", description = "Não foi encontrado o pedido pelo id informado. Verifique!"),
			@ApiResponse(responseCode = "200", description = "Pedido localizado") })
	public ResponseEntity<PedidoDto> obterPorId(@PathVariable Long id) {
		Optional<PedidoDto> dto = service.obterPorId(id);
		if (!dto.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dto.get());
	}

	@PostMapping
	@Operation(summary = "Cadastrar um novo pedido", description = "Criar um novo pedido e retornar os detalhes do pedido criado")
	@ApiResponse(responseCode = "200", description = "Pedido criado com sucesso")
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoDtoCadastroPedido cadastrarPedido(@RequestBody PedidoDtoCadastroPedido dto) {
		return service.salvarPedido(dto);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deletar um pedido", description = "Apagar um pedido de acordo com o id fornecido")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "404", description = "Não foi encontrado o pedido pelo id informado. Verifique!"),
			@ApiResponse(responseCode = "200", description = "Pedido deletado") })
	public ResponseEntity<Void> deletaPedido(@PathVariable Long id) {
		if (!service.apagarPedido(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}


	@PutMapping("/{id}")
	@Operation(summary = "Alterar um pedido", description = "Apagar um pedido de acordo com o id fornecido")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "404", description = "Não foi encontrado o pedido pelo id informado. Verifique!"),
			@ApiResponse(responseCode = "200", description = "Pedido alterado.") })
	public ResponseEntity<PedidoDtoCadastroPedido> alterarPedido(@PathVariable Long id,
			@RequestBody PedidoDtoCadastroPedido dto) {
		Optional<PedidoDtoCadastroPedido> pedidoAlterado = service.alterarPedido(id, dto);
		if (!pedidoAlterado.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(pedidoAlterado.get());
	}

	@GetMapping("/relatorio/{id}")
	@Operation(summary = "Gerar um relatório do pedido", description = "Gerar um relatório do pedido de acordo com o id fornecido")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "404", description = "Não foi encontrado o relatório pelo id informado. Verifique!"),
			@ApiResponse(responseCode = "200", description = "Relatório encontrado.") })
	public ResponseEntity<RelatorioPedidoDto> gerarRelatorioPedido(@PathVariable Long id) {
		RelatorioPedidoDto relatorio = service.gerarRelatorioPedido(id);
		return ResponseEntity.ok(relatorio);
	}
}