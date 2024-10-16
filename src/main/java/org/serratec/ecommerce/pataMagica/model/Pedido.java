package org.serratec.ecommerce.pataMagica.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedidos")
public class Pedido {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate data_pedido;
	private LocalDate data_entrega;
	private LocalDate data_envio;
	private boolean status;
	private Double valor_total;
	//private int cliente_id; colocar relacionamento
	@ManyToOne
	private Cliente cliente;
	

	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getData_pedido() {
		return data_pedido;
	}
	public void setData_pedido(LocalDate data_pedido) {
		this.data_pedido = data_pedido;
	}
	public LocalDate getData_entrega() {
		return data_entrega;
	}
	public void setData_entrega(LocalDate data_entrega) {
		this.data_entrega = data_entrega;
	}
	public LocalDate getData_envio() {
		return data_envio;
	}
	public void setData_envio(LocalDate data_envio) {
		this.data_envio = data_envio;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Double getValor_total() {
		return valor_total;
	}
	public void setValor_total(Double valor_total) {
		this.valor_total = valor_total;
	}
	
}
