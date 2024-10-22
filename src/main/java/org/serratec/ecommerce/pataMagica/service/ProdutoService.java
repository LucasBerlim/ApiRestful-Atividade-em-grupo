package org.serratec.ecommerce.pataMagica.service;

import java.util.List;
import java.util.Optional;

import org.serratec.ecommerce.pataMagica.dto.ProdutoDto;
import org.serratec.ecommerce.pataMagica.model.Produto;
import org.serratec.ecommerce.pataMagica.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;

	public List<ProdutoDto> obterTodos() {
		return repository.findAll().stream().map(p -> ProdutoDto.toDto(p)).toList();
	}

	public Optional<ProdutoDto> obterPorId(Long id) {
		if (!repository.existsById(id)) {
			return Optional.empty();
		}
		return Optional.of(ProdutoDto.toDto(repository.findById(id).get()));
	}

	public ProdutoDto salvarProduto(ProdutoDto dto) {
		Produto produtoEntity = repository.save(dto.toEntity());
		return ProdutoDto.toDto(produtoEntity);
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
		Produto produtoEntity = dto.toEntity();
		produtoEntity.setId(id);
		repository.save(produtoEntity);
		return Optional.of(ProdutoDto.toDto(produtoEntity));
	}
}
