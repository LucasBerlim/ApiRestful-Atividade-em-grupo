package org.serratec.ecommerce.pataMagica.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.serratec.ecommerce.pataMagica.model.ItemPedido;
import org.serratec.ecommerce.pataMagica.model.Pedido;

public class RelatorioPedidoDto {
	private Long idPedido;
	private LocalDate dataPedido;
	private Double valorTotal;
	private List<RelatorioItemDto> itensPedido;

	public RelatorioPedidoDto() {
	}

	public RelatorioPedidoDto(Long idPedido, LocalDate dataPedido, Double valorTotal,
			List<RelatorioItemDto> itensPedido) {
		super();
		this.idPedido = idPedido;
		this.dataPedido = dataPedido;
		this.valorTotal = valorTotal;
		this.itensPedido = itensPedido;
	}

	public Pedido toEntity() {
		Pedido pedido = new Pedido();
		pedido.setId(this.idPedido);
		pedido.setDataPedido(this.dataPedido);
		pedido.setValorTotal(this.valorTotal);

		List<ItemPedido> itens = this.itensPedido.stream().map(itemDto -> itemDto.toEntity(pedido))
				.collect(Collectors.toList());

		pedido.setItensPedido(itens);
		return pedido;
	}

	public static RelatorioPedidoDto toDto(Pedido pedido) {
		List<RelatorioItemDto> itensDto = pedido.getItensPedido().stream()
				.map(itemPedido -> new RelatorioItemDto(itemPedido.getProduto().getId(),
						itemPedido.getProduto().getNome(), itemPedido.getProduto().getValorUnitario(),
						itemPedido.getQuantidade(), itemPedido.getPercentualDesconto(), itemPedido.getValorLiquido()))
				.collect(Collectors.toList());

		return new RelatorioPedidoDto(pedido.getId(), pedido.getDataPedido(), pedido.getValorTotal(), itensDto);
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

	public List<RelatorioItemDto> getItensPedido() {
		return itensPedido;
	}

	public void setItensPedido(List<RelatorioItemDto> itensPedido) {
		this.itensPedido = itensPedido;
	}

	@Override
	public String toString() {
		return "RelatorioPedidoDto [idPedido=" + idPedido + ", dataPedido=" + dataPedido + ", valorTotal=" + valorTotal
				+ ", itensPedido=" + itensPedido + "]";
	}
	
	
}
