package org.serratec.ecommerce.pataMagica.dto;

import java.time.LocalDate;
import java.util.List;

import org.serratec.ecommerce.pataMagica.model.Pedido;

public record PedidoDto(
		Long id,
		LocalDate dataPedido,
		LocalDate dataEntrega,
		LocalDate dataEnvio,
		boolean status,
		Double valorTotal,
		List<ItemPedidoDto> itensPedido
		) {
	
	public Pedido toEntity() {
		Pedido pedido = new Pedido();
		pedido.setId(this.id);
		pedido.setDataPedido(this.dataPedido);
		pedido.setDataEntrega(this.dataEntrega);
		pedido.setDataEnvio(this.dataEnvio);
		pedido.setStatus(this.status);
		pedido.setValorTotal(this.valorTotal);
		pedido.setItensPedido(this.itensPedido.stream().map(ip -> ip.toEntity()).toList());
		return pedido;
	}
	
	public static PedidoDto toDto(Pedido pedido) {
		return new PedidoDto(
		        pedido.getId(),
		        pedido.getDataPedido(),
		        pedido.getDataEntrega(),
		        pedido.getDataEnvio(),
		        pedido.isStatus(),
		        pedido.getValorTotal(),
		        pedido.getItensPedido().stream().map(ip -> ItemPedidoDto.toDto(ip)).toList()
		    );
	}
}
