package org.serratec.ecommerce.pataMagica.dto;

import java.util.List;

import org.serratec.ecommerce.pataMagica.model.Categoria;

public record CategoriaDto(
		Long id,
		String nome,
		String descricao
		//List<ProdutoDto> produtos
		) {
	
	public Categoria toEntity() {
		Categoria categoria = new Categoria();
		categoria.setId(this.id);
		categoria.setNome(this.nome);
		categoria.setDescricao(this.descricao);
		//categoria.setProdutos(this.produtos.stream().map(p -> p.toEntity()).toList());
		return categoria;
	}
	
	public static CategoriaDto toDto(Categoria categoria) {
        return new CategoriaDto(categoria.getId(), categoria.getNome(), 
        		categoria.getDescricao());
	} //, categoria.getProdutos().stream().map(p -> ProdutoDto.toDto(p)).toList()
}
