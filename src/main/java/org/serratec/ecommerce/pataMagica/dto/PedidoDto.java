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
		Double valorTotal
		// tentando implementar cliente e lista de itens
		//ClienteDto cliente,
		//List<ItemPedidoDto> itensPedido
		) {
	
	public Pedido toEntity() {
		Pedido pedido = new Pedido();
		pedido.setId(this.id);
		pedido.setDataPedido(this.dataPedido);
		pedido.setDataEntrega(this.dataEntrega);
		pedido.setDataEnvio(this.dataEnvio);
		pedido.setStatus(this.status);
		pedido.setValorTotal(this.valorTotal);
		//pedido.setCliente(this.cliente.toEntity());
		//pedido.getCliente().setId(this.clienteId);
		//pedido.setItensPedido(this.itensPedido.stream().map(ip -> ip.toEntity()).toList());
		return pedido;
	}
	
	public static PedidoDto toDto(Pedido pedido) {
        ClienteDto clienteDto = (pedido.getCliente() != null) ? ClienteDto.toDto(pedido.getCliente()) : null;
		return new PedidoDto(
		        pedido.getId(),
		        pedido.getDataPedido(),
		        pedido.getDataEntrega(),
		        pedido.getDataEnvio(),
		        pedido.isStatus(),
		        pedido.getValorTotal()
		        //clienteDto,
		        //pedido.getItensPedido().stream().map(ip -> ItemPedidoDto.toDto(ip)).toList()
		    );
	}
}
