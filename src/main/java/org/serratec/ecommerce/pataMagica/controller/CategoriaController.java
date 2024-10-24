package org.serratec.ecommerce.pataMagica.controller;

import java.util.List;
import java.util.Optional;
import org.serratec.ecommerce.pataMagica.dto.CategoriaDto;
import org.serratec.ecommerce.pataMagica.service.CategoriaService;
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
import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService service;

	@GetMapping
	@Operation(summary = "Retorna todas as categorias.", description = "Dado um determinado id, será retornada as categorias com as informações gerais.")
	@ApiResponse(responseCode = "200", description = "Categorias localizadas")
	public List<CategoriaDto> obterTodos() {
		return service.obterTodos();
	}

	@GetMapping("/{id}")
	@Operation(summary = "Retorna uma categoria por id", description = "Dado um determinado id, será retornada uma categoria com as informações gerais da categoria.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "404", description = "Não foi encontrada a categoria pelo id informado. Verifique!"),
			@ApiResponse(responseCode = "200", description = "Categoria localizada") })
	public ResponseEntity<CategoriaDto> obterPorId(@PathVariable Long id) {
		Optional<CategoriaDto> dto = service.obterPorId(id);
		if (!dto.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dto.get());
	}

	@PostMapping
	@Operation(summary = "Cadastra uma nova categoria", description = "Criar uma nova categoria e retornar os detalhes da categoria criada")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "400", description = "Não foi encontrada a categoria pelo id informado. Verifique!"),
			@ApiResponse(responseCode = "200", description = "Categoria localizado.") })
	@ResponseStatus(HttpStatus.CREATED)
	public CategoriaDto cadastrarCategoria(@RequestBody @Valid CategoriaDto dto) {
		return service.salvarCategoria(dto);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deletar uma nova categoria", description = "Apagar uma categoria de acordo com o id fornecido")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "404", description = "Não foi encontrada a categoria pelo id informado. Verifique!"),
			@ApiResponse(responseCode = "200", description = "Categoria deletada") })
	public ResponseEntity<Void> deletaCategoria(@PathVariable Long id) {
		if (!service.apagarCategoria(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	@Operation(summary = "Alterar uma nova categoria", description = "Apagar uma categoria de acordo com o id fornecido")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "404", description = "Não foi encontrada a categoria pelo id informado. Verifique!"),
			@ApiResponse(responseCode = "200", description = "Categoria alterada") })
	public ResponseEntity<CategoriaDto> alterarCategoria(@PathVariable Long id, @RequestBody @Valid CategoriaDto dto) {
		Optional<CategoriaDto> categoriaAlterado = service.alterarCategoria(id, dto);
		if (!categoriaAlterado.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(categoriaAlterado.get());
	}
}
