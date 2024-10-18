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
		private Produto produto;
		//ProdutoDto produto
		
		public ItemPedidoDto() {
			
		}
		
	public ItemPedidoDto(Long id, int quantidade, Double precoVenda, Double percentualDesconto, Double valorBruto,
				Double valorLiquido, Produto produto) {
			super();
			this.id = id;
			this.quantidade = quantidade;
			this.precoVenda = precoVenda;
			this.percentualDesconto = percentualDesconto;
			this.valorBruto = valorBruto;
			this.valorLiquido = valorLiquido;
			this.produto = produto;
		}

	public ItemPedido toEntity() {
		ItemPedido itemPedido = new ItemPedido();
		itemPedido.setId(this.id);
		itemPedido.setQuantidade(this.quantidade);
		itemPedido.setPrecoVenda(this.precoVenda);
		itemPedido.setPercentualDesconto(this.percentualDesconto);
		itemPedido.setValorBruto(this.valorBruto);
		itemPedido.setValorLiquido(this.valorLiquido);
		itemPedido.setProduto(this.produto);
		//itemPedido.setProduto(this.produto.toEntity());
		return itemPedido;
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

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public static ItemPedidoDto toDto(ItemPedido itemPedido) {
        return new ItemPedidoDto(itemPedido.getId(), itemPedido.getQuantidade(), 
        		itemPedido.getPrecoVenda(), itemPedido.getPercentualDesconto(), 
        		itemPedido.getValorBruto(), itemPedido.getValorLiquido(),
        		itemPedido.getProduto()
        		//ProdutoDto.toDto(itemPedido.getProduto())
        		);
	}
}
