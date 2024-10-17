package org.serratec.ecommerce.pataMagica.dto;

import java.time.LocalDate;

import org.serratec.ecommerce.pataMagica.model.Pedido;

public record PedidoDto(
		Long id,
		LocalDate dataPedido,
		LocalDate dataEntrega,
		LocalDate dataEnvio,
		boolean status,
		Double valorTotal
		// tentando implementar cliente e lista de itens
		//ClienteDto cliente
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
		return pedido;
	}
	
	public static PedidoDto toDto(Pedido pedido) {
        return new PedidoDto(pedido.getId(), pedido.getDataPedido(), pedido.getDataEntrega(),
        		pedido.getDataEnvio(), pedido.isStatus(), pedido.getValorTotal());
	//ClienteDto.toDto(pedido.getCliente())
	} 
}
