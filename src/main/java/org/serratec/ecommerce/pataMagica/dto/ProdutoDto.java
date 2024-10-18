package org.serratec.ecommerce.pataMagica.dto;

import java.time.LocalDate;

import org.serratec.ecommerce.pataMagica.model.Categoria;
import org.serratec.ecommerce.pataMagica.model.Produto;

public record ProdutoDto(
		Long id,
		String nome,
		int qtdEstoque,
		LocalDate dataCadastro,
		Double valorUnitario,
		String imagem,
		CategoriaDto categoria
		//List<ItemPedidoDto> itensPedido
		) {
	
	public Produto toEntity() {
		Produto produto = new Produto();
		produto.setId(this.id);
		produto.setNome(this.nome);
		produto.setQtdEstoque(this.qtdEstoque);
		produto.setDataCadastro(this.dataCadastro);
		produto.setValorUnitario(this.valorUnitario);
		produto.setImagem(this.imagem);
		produto.setCategoria(this.categoria.toEntity());
		//produto.setItensPedido(this.itensPedido.stream().map(ip -> ip.toEntity()).toList());
		return produto;
	}
	
	public static ProdutoDto toDto(Produto produto) {
        return new ProdutoDto(produto.getId(), produto.getNome(), produto.getQtdEstoque(),
        		produto.getDataCadastro(), produto.getValorUnitario(), produto.getImagem(),
        		CategoriaDto.toDto(produto.getCategoria())
        		);
	} // , produto.getItensPedido().stream().map(ip -> ItemPedidoDto.toDto(ip)).toList()
}
