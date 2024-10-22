package org.serratec.ecommerce.pataMagica.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.ecommerce.pataMagica.dto.ProdutoDto;
import org.serratec.ecommerce.pataMagica.service.ProdutoService;
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
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService service;

	@GetMapping
	@Operation(summary = "Retorna todos os produtos.", description = "Dado um determinado id, será retornado os produtos com as informações gerais.")
	@ApiResponse(responseCode = "200", description = "Produto localizados")
	public List<ProdutoDto> obterTodos() {
		return service.obterTodos();
	}

	@GetMapping("/{id}")
	@Operation(summary = "Retorna um produto por id", description = "Dado um determinado id, será retornado um produto com as informações gerais do produto.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "404", description = "Não foi encontrado o produto pelo id informado. Verifique!"),
			@ApiResponse(responseCode = "200", description = "Produto localizado") })
	public ResponseEntity<ProdutoDto> obterPorId(@PathVariable Long id) {
		Optional<ProdutoDto> dto = service.obterPorId(id);
		if (!dto.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dto.get());
	}

	@PostMapping
	@Operation(summary = "Cadastra um novo produto", description = "Criar um novo produto e retornar os detalhes do produto criado")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "400", description = "Não foi encontrado o produto pelo id informado. Verifique!"),
			@ApiResponse(responseCode = "200", description = "Produto localizado.") })
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoDto cadastrarProduto(@RequestBody ProdutoDto dto) {
		return service.salvarProduto(dto);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deletar um produto", description = "Apagar um produto de acordo com o id fornecido")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "404", description = "Não foi encontrado o produto pelo id informado. Verifique!"),
			@ApiResponse(responseCode = "200", description = "Produto deletado") })
	public ResponseEntity<Void> deletaProduto(@PathVariable Long id) {
		if (!service.apagarProduto(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	@Operation(summary = "Alterar um produto", description = "Apagar um produto de acordo com o id fornecido")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "404", description = "Não foi encontrado o produto pelo id informado. Verifique!"),
			@ApiResponse(responseCode = "200", description = "Produto alterado") })
	public ResponseEntity<ProdutoDto> alterarProduto(@PathVariable Long id, @RequestBody ProdutoDto dto) {
		Optional<ProdutoDto> produtoAlterado = service.alterarProduto(id, dto);
		if (!produtoAlterado.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(produtoAlterado.get());
	}
}
