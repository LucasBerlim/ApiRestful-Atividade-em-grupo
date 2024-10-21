package org.serratec.ecommerce.pataMagica.dto;

import java.time.LocalDate;
import java.util.List;

import org.serratec.ecommerce.pataMagica.model.Cliente;
import org.serratec.ecommerce.pataMagica.model.Pedido;
import org.serratec.ecommerce.pataMagica.model.Produto;
import org.serratec.ecommerce.pataMagica.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;

public class PedidoDtoCadastroPedido {
	@Autowired
	ProdutoService produtoService;

	private Long id;
	private LocalDate dataPedido;
	private LocalDate dataEntrega;
	private LocalDate dataEnvio;
	private boolean status;
	private Double valorTotal;
	private Long clienteId;
	private List<ItemPedidoDtoCadastroPedido> itensPedido;

	public PedidoDtoCadastroPedido() {
	}

	public PedidoDtoCadastroPedido(Long id, LocalDate dataPedido, LocalDate dataEntrega, LocalDate dataEnvio,
			boolean status, Double valorTotal, Long clienteId, List<ItemPedidoDtoCadastroPedido> itensPedido) {
		super();
		this.id = id;
		this.dataPedido = dataPedido;
		this.dataEntrega = dataEntrega;
		this.dataEnvio = dataEnvio;
		this.status = status;
		this.valorTotal = valorTotal;
		this.clienteId = clienteId;
		this.itensPedido = itensPedido;
	}

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
		pedido.getCliente().setId(this.clienteId);
		pedido.setItensPedido(this.itensPedido.stream().map(ip -> ip.toEntity()).toList());
		return pedido;
	}

	public static PedidoDtoCadastroPedido toDto(Pedido pedido) {

		return new PedidoDtoCadastroPedido(pedido.getId(), pedido.getDataPedido(), pedido.getDataEntrega(),
				pedido.getDataEnvio(), pedido.isStatus(), pedido.getValorTotal(), pedido.getCliente().getId(),
				pedido.getItensPedido().stream().map(ip -> ItemPedidoDtoCadastroPedido.toDto(ip)).toList());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}

	public LocalDate getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public LocalDate getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(LocalDate dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public List<ItemPedidoDtoCadastroPedido> getItensPedido() {
		return itensPedido;
	}

	public void setItensPedido(List<ItemPedidoDtoCadastroPedido> itensPedido) {
		this.itensPedido = itensPedido;
	}
}
