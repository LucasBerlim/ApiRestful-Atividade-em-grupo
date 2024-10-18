package org.serratec.ecommerce.pataMagica.dto;

import java.time.LocalDate;
import java.util.List;

import org.serratec.ecommerce.pataMagica.model.Cliente;
import org.serratec.ecommerce.pataMagica.model.Pedido;

public record PedidoDtoCadastroPedido(
		Long id,
		LocalDate dataPedido,
		LocalDate dataEntrega,
		LocalDate dataEnvio,
		boolean status,
		Double valorTotal,
		Long clienteId, //teste
		List<ItemPedidoDtoCadastroPedido> itensPedido
		) {
	
	public Pedido toEntity() {
		Pedido pedido = new Pedido();
		pedido.setId(this.id);
		pedido.setDataPedido(this.dataPedido);
		pedido.setDataEntrega(this.dataEntrega);
		pedido.setDataEnvio(this.dataEnvio);
		pedido.setStatus(this.status);
		pedido.setValorTotal(this.valorTotal);
		Cliente cliente = new Cliente();
		pedido.setCliente(cliente);
		pedido.getCliente().setId(this.clienteId); //teste
		pedido.setItensPedido(this.itensPedido.stream().map(ip -> ip.toEntity()).toList());
		return pedido;
	}
	
	public static PedidoDtoCadastroPedido toDto(Pedido pedido) {
		return new PedidoDtoCadastroPedido(
		        pedido.getId(),
		        pedido.getDataPedido(),
		        pedido.getDataEntrega(),
		        pedido.getDataEnvio(),
		        pedido.isStatus(),
		        pedido.getValorTotal(),
		        pedido.getCliente().getId(),
		        pedido.getItensPedido().stream().map(ip -> ItemPedidoDtoCadastroPedido.toDto(ip)).toList()
		    );
	}
}
