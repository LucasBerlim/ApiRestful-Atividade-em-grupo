package org.serratec.ecommerce.pataMagica.dto;

import org.serratec.ecommerce.pataMagica.model.ItemPedido;
import org.serratec.ecommerce.pataMagica.model.Pedido;
import org.serratec.ecommerce.pataMagica.model.Produto;

public class RelatorioItemDto {
	private Long idProduto;
    private String nomeProduto;
    private Double precoUnitarioProduto;
    private int qtdeItem;
    private Double percentualDescontoItem;
    private Double valorLiquidoItem;

    public RelatorioItemDto() {}

    public RelatorioItemDto(Long idProduto, String nomeProduto, Double precoUnitarioProduto, int qtdeItem, Double percentualDescontoItem, Double valorLiquidoItem) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.precoUnitarioProduto = precoUnitarioProduto;
        this.qtdeItem = qtdeItem;
        this.percentualDescontoItem = percentualDescontoItem;
        this.valorLiquidoItem = valorLiquidoItem;
    }

    public ItemPedido toEntity(Pedido pedido) {
        Produto produto = new Produto();
        produto.setId(this.idProduto);
        

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setProduto(produto);
        itemPedido.setQuantidade(this.qtdeItem);
        itemPedido.setPercentualDesconto(this.percentualDescontoItem);
        itemPedido.setValorLiquido(this.valorLiquidoItem);
        itemPedido.setValorBruto(this.precoUnitarioProduto * this.qtdeItem);
        itemPedido.setPedido(pedido);

        return itemPedido;
    }
    
    public static RelatorioItemDto toDto(ItemPedido itemPedido) {
        return new RelatorioItemDto(
            itemPedido.getProduto().getId(),
            itemPedido.getProduto().getNome(),
            itemPedido.getProduto().getValorUnitario(),
            itemPedido.getQuantidade(),
            itemPedido.getPercentualDesconto(),
            itemPedido.getValorLiquido()
        );
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
