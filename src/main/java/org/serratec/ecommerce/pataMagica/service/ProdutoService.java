package org.serratec.ecommerce.pataMagica.service;

import java.util.List;
import java.util.Optional;

import org.serratec.ecommerce.pataMagica.dto.ProdutoDto;
import org.serratec.ecommerce.pataMagica.model.Categoria;
import org.serratec.ecommerce.pataMagica.model.ErrorResponse;
import org.serratec.ecommerce.pataMagica.model.Produto;
import org.serratec.ecommerce.pataMagica.repository.CategoriaRepository;
import org.serratec.ecommerce.pataMagica.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;
	@Autowired
	private CategoriaRepository categoriaRepository;

	public List<ProdutoDto> obterTodos() {
		return repository.findAll().stream().map(p -> ProdutoDto.toDto(p)).toList();
	}

	public Optional<ProdutoDto> obterPorId(Long id) {
		if (!repository.existsById(id)) {
			return Optional.empty();
		}
		return Optional.of(ProdutoDto.toDto(repository.findById(id).get()));
	}
	
	public ResponseEntity<Object> salvarProduto(ProdutoDto dto) {
		Produto produtoDescricao = repository.findByDescricao(dto.getDescricao());
		if(produtoDescricao != null) {
			return ResponseEntity.badRequest().body(new ErrorResponse("Descrição já cadastrada", 400));
		}
		
		Produto produtoEntity = repository.save(dto.toEntity());
		return ResponseEntity.ok(ProdutoDto.toDto(produtoEntity));
	}

	public boolean apagarProduto(Long id) {
		if (!repository.existsById(id)) {
			return false;
		}
		repository.deleteById(id);
		return true;
	}

	public Optional<ProdutoDto> alterarProduto(Long id, ProdutoDto dto) {
	    if (!repository.existsById(id)) {
	        return Optional.empty();
	    }
	    Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
	        .orElseThrow(() -> new RuntimeException("A categoria não foi encontrada"));

	    Produto produtoEntity = dto.toEntity();
	    produtoEntity.setId(id);
	    produtoEntity.setCategoria(categoria);

	    repository.save(produtoEntity);
	    return Optional.of(ProdutoDto.toDto(produtoEntity));
	}
}
