package org.serratec.ecommerce.pataMagica.dto;

import org.serratec.ecommerce.pataMagica.model.ItemPedido;
import org.serratec.ecommerce.pataMagica.model.Pedido;
import org.serratec.ecommerce.pataMagica.model.Produto;

public record ItemPedidoDto(
		Long id,
		int quantidade,
		Double precoVenda,
		Double percentualDesconto,
		Double valorBruto,
		Double valorLiquido,
		Produto produto,
		Pedido pedido
		) {
	
	public ItemPedido toEntity() {
		ItemPedido itemPedido = new ItemPedido();
		itemPedido.setId(this.id);
		itemPedido.setQuantidade(this.quantidade);
		itemPedido.setPrecoVenda(this.precoVenda);
		itemPedido.setPercentualDesconto(this.percentualDesconto);
		itemPedido.setValorBruto(this.valorBruto);
		itemPedido.setValorLiquido(this.valorLiquido);
		itemPedido.setProduto(this.produto);
		itemPedido.setPedido(this.pedido);
		return itemPedido;
	}
	
	public static ItemPedidoDto toDto(ItemPedido itemPedido) {
        return new ItemPedidoDto(itemPedido.getId(), itemPedido.getQuantidade(), 
        		itemPedido.getPrecoVenda(), itemPedido.getPercentualDesconto(), 
        		itemPedido.getValorBruto(), itemPedido.getValorLiquido(),
        		itemPedido.getProduto(), itemPedido.getPedido());
	}
}
