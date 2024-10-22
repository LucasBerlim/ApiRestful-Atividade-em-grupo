package org.serratec.ecommerce.pataMagica.service;

import java.util.List;
import java.util.Optional;
import org.serratec.ecommerce.pataMagica.dto.CategoriaDto;
import org.serratec.ecommerce.pataMagica.model.Categoria;
import org.serratec.ecommerce.pataMagica.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	public List<CategoriaDto> obterTodos() {
		return repository.findAll().stream().map(c -> CategoriaDto.toDto(c)).toList();
	}

	public Optional<CategoriaDto> obterPorId(Long id) {
		if (!repository.existsById(id)) {
			return Optional.empty();
		}
		return Optional.of(CategoriaDto.toDto(repository.findById(id).get()));
	}

	public CategoriaDto salvarCategoria(CategoriaDto dto) {
		Categoria produtoEntity = repository.save(dto.toEntity());
		return CategoriaDto.toDto(produtoEntity);
	}

	public boolean apagarCategoria(Long id) {
		if (!repository.existsById(id)) {
			return false;
		}
		repository.deleteById(id);
		return true;
	}

	public Optional<CategoriaDto> alterarCategoria(Long id, CategoriaDto dto) {
		if (!repository.existsById(id)) {
			return Optional.empty();
		}
		Categoria categoriaEntity = dto.toEntity();
		categoriaEntity.setId(id);
		repository.save(categoriaEntity);
		return Optional.of(CategoriaDto.toDto(categoriaEntity));
	}
}
