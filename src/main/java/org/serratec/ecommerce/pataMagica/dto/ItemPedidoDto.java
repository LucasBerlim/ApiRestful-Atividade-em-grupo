package org.serratec.ecommerce.pataMagica.dto;

import org.serratec.ecommerce.pataMagica.model.ItemPedido;
import org.serratec.ecommerce.pataMagica.model.Produto;

public class ItemPedidoDto {
		private Long id;
		private int quantidade;
		private Double precoVenda;
		private Double percentualDesconto;
		private Double valorBruto;
		private Double valorLiquido;
		private Long produtoId;
		
		public ItemPedidoDto() {}
		
	public ItemPedidoDto(Long id, int quantidade, Double precoVenda, Double percentualDesconto, Double valorBruto,
				Double valorLiquido, Long produtoId) {
			super();
			this.id = id;
			this.quantidade = quantidade;
			this.precoVenda = precoVenda;
			this.percentualDesconto = percentualDesconto;
			this.valorBruto = valorBruto;
			this.valorLiquido = valorLiquido;
			this.produtoId = produtoId;
		}

	public ItemPedido toEntity() {
		ItemPedido itemPedido = new ItemPedido();
		itemPedido.setId(this.id);
		itemPedido.setQuantidade(this.quantidade);
		itemPedido.setPrecoVenda(this.precoVenda);
		itemPedido.setPercentualDesconto(this.percentualDesconto);
		itemPedido.setValorBruto(this.valorBruto);
		itemPedido.setValorLiquido(this.valorLiquido);
		Produto produto = new Produto();
		itemPedido.setProduto(produto);
		itemPedido.getProduto().setId(this.produtoId);
		return itemPedido;
	}
	
	public static ItemPedidoDto toDto(ItemPedido itemPedido) {
        return new ItemPedidoDto(itemPedido.getId(), itemPedido.getQuantidade(), 
        		itemPedido.getPrecoVenda(), itemPedido.getPercentualDesconto(), 
        		itemPedido.getValorBruto(), itemPedido.getValorLiquido(),
        		itemPedido.getProduto().getId()
        		);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(Double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public Double getPercentualDesconto() {
		return percentualDesconto;
	}

	public void setPercentualDesconto(Double percentualDesconto) {
		this.percentualDesconto = percentualDesconto;
	}

	public Double getValorBruto() {
		return valorBruto;
	}

	public void setValorBruto(Double valorBruto) {
		this.valorBruto = valorBruto;
	}

	public Double getValorLiquido() {
		return valorLiquido;
	}

	public void setValorLiquido(Double valorLiquido) {
		this.valorLiquido = valorLiquido;
	}

	public Long getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(Long produtoId) {
		this.produtoId = produtoId;
	}
}
