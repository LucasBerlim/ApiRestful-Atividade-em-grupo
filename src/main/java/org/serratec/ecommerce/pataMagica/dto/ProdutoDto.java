package org.serratec.ecommerce.pataMagica.dto;

import org.serratec.ecommerce.pataMagica.model.Produto;

public record ProdutoDto(
		Long id,
		String nome,
		String qtdEstoque,
		String dataCadastro,
		Double valorUnitario,
		String imagem
		) {
	
	public Produto toEntity() {
		Produto produto = new Produto();
		produto.setId(this.id);
		produto.setNome(this.nome);
		produto.setQtdEstoque(this.qtdEstoque);
		produto.setDataCadastro(this.dataCadastro);
		produto.setValorUnitario(this.valorUnitario);
		produto.setImagem(this.imagem);
		return produto;
	}
	
	public static ProdutoDto toDto(Produto produto) {
        return new ProdutoDto(produto.getId(), produto.getNome(), produto.getQtdEstoque(),
        		produto.getDataCadastro(), produto.getValorUnitario(), produto.getImagem());
	}
}
