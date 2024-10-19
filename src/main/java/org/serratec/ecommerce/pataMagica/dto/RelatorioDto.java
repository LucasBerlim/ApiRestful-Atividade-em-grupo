package org.serratec.ecommerce.pataMagica.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.serratec.ecommerce.pataMagica.model.ItemPedido;
import org.serratec.ecommerce.pataMagica.model.Pedido;
import org.serratec.ecommerce.pataMagica.model.Produto;

public class RelatorioDto {
		private Long idPedido;
		private LocalDate dataPedido;
		private Double valorTotal;
		private Long idProduto;
		private String nomeProduto;
		private Double precoUnitarioProduto;
		private int qtdeItem;
		private Double percentualDescontoItem;
		private Double valorLiquidoItem;
		
		public RelatorioDto() {
			
		}

		public RelatorioDto(Long idPedido, LocalDate dataPedido, Double valorTotal, Long idProduto, String nomeProduto,
				Double precoUnitarioProduto, int qtdeItem, Double percentualDescontoItem, Double valorLiquidoItem) {
			super();
			this.idPedido = idPedido;
			this.dataPedido = dataPedido;
			this.valorTotal = valorTotal;
			this.idProduto = idProduto;
			this.nomeProduto = nomeProduto;
			this.precoUnitarioProduto = precoUnitarioProduto;
			this.qtdeItem = qtdeItem;
			this.percentualDescontoItem = percentualDescontoItem;
			this.valorLiquidoItem = valorLiquidoItem;
		}
		
		
		/*public static List<RelatorioDto> toDto(Pedido pedido) {
		    List<RelatorioDto> relatorios = new ArrayList<>();
		    for (ItemPedido itemPedido : pedido.getItensPedido()) {
		        RelatorioDto relatorioDto = new RelatorioDto(
		            pedido.getId(),
		            pedido.getDataPedido(),
		            pedido.getValorTotal(),
		            itemPedido.getProduto().getId(),
		            itemPedido.getProduto().getNome(),
		            itemPedido.getProduto().getValorUnitario(),
		            itemPedido.getQuantidade(),
		            itemPedido.getPercentualDesconto(),
		            itemPedido.getValorLiquido()
		        );
		        relatorios.add(relatorioDto);
		    }
		    return relatorios;
		}

		
		public Pedido toEntity() {
		    Pedido pedido = new Pedido();
		    pedido.setId(this.idPedido);
		    pedido.setDataPedido(this.dataPedido);
		    pedido.setValorTotal(this.valorTotal);

		    Produto produto = new Produto();
		    produto.setId(this.idProduto);
		    produto.setNome(this.nomeProduto);
		    produto.setValorUnitario(this.precoUnitarioProduto);

		    ItemPedido itemPedido = new ItemPedido();
		    itemPedido.setQuantidade(this.qtdeItem);
		    itemPedido.setPercentualDesconto(this.percentualDescontoItem);
		    itemPedido.setValorLiquido(this.valorLiquidoItem);
		    itemPedido.setProduto(produto);
		    itemPedido.setPedido(pedido);

		    pedido.setItensPedido(Collections.singletonList(itemPedido)); // Simplificado para um único item

		    return pedido;
		}*/
//
		
		public Pedido toEntity() {
			Pedido pedido = new Pedido();
			pedido.setId(this.idPedido);
			pedido.setDataPedido(this.dataPedido);
			pedido.setValorTotal(this.valorTotal);
			Produto produto = new Produto();
			produto.setId(this.idProduto);
			produto.setNome(this.nomeProduto);
			produto.setValorUnitario(this.precoUnitarioProduto);
			//produto.setItensPedido();
			
			/*List<ItemPedido> itens = pedido.getItensPedido();
			for (ItemPedido ip : itens) {
				ip.setQuantidade(this.qtdeItem);
				ip.setPercentualDesconto(this.percentualDescontoItem);
				ip.setValorLiquido(this.valorLiquidoItem);
				ip.setPedido(pedido);
			}*/
			
			
			ItemPedido itemPedido = new ItemPedido();
			itemPedido.setQuantidade(this.qtdeItem);
			itemPedido.setPercentualDesconto(this.percentualDescontoItem);
			itemPedido.setValorLiquido(this.valorLiquidoItem);
			itemPedido.setPedido(pedido);
			return pedido;
		}
		
		public static RelatorioDto toDto(Pedido pedido) {
			ItemPedido itemPedido = pedido.getItensPedido().get(0); // trouxe só o primeiro
			
			return new RelatorioDto(pedido.getId(), pedido.getDataPedido(), pedido.getValorTotal(),
					itemPedido.getProduto().getId(),
			        itemPedido.getProduto().getNome(),
			        itemPedido.getProduto().getValorUnitario(),
			        itemPedido.getQuantidade(),
			        itemPedido.getPercentualDesconto(),
			        itemPedido.getValorLiquido()
					);
		}		
		
		public Long getIdPedido() {
			return idPedido;
		}
		public void setIdPedido(Long idPedido) {
			this.idPedido = idPedido;
		}
		public LocalDate getDataPedido() {
			return dataPedido;
		}
		public void setDataPedido(LocalDate dataPedido) {
			this.dataPedido = dataPedido;
		}
		public Double getValorTotal() {
			return valorTotal;
		}
		public void setValorTotal(Double valorTotal) {
			this.valorTotal = valorTotal;
		}
		public Long getIdProduto() {
			return idProduto;
		}
		public void setIdProduto(Long idProduto) {
			this.idProduto = idProduto;
		}
		public String getNomeProduto() {
			return nomeProduto;
		}
		public void setNomeProduto(String nomeProduto) {
			this.nomeProduto = nomeProduto;
		}
		public Double getPrecoUnitarioProduto() {
			return precoUnitarioProduto;
		}
		public void setPrecoUnitarioProduto(Double precoUnitarioProduto) {
			this.precoUnitarioProduto = precoUnitarioProduto;
		}
		public int getQtdeItem() {
			return qtdeItem;
		}
		public void setQtdeItem(int qtdeItem) {
			this.qtdeItem = qtdeItem;
		}
		public Double getPercentualDescontoItem() {
			return percentualDescontoItem;
		}
		public void setPercentualDescontoItem(Double percentualDescontoItem) {
			this.percentualDescontoItem = percentualDescontoItem;
		}
		public Double getValorLiquidoItem() {
			return valorLiquidoItem;
		}
		public void setValorLiquidoItem(Double valorLiquidoItem) {
			this.valorLiquidoItem = valorLiquidoItem;
		}
		
		
		
		
	
}
