package org.serratec.ecommerce.pataMagica.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "itens_pedido")
public class Item_Pedido {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private int quantidade;
	private Double preco_venda;
	private Double percentual_desconto;
	private Double valor_bruto;
	private Double valor_liquido;
	//private int pedido_id; colocar relacionamento
	//produto int produto_id; colocar relacionamento
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public Double getPreco_venda() {
		return preco_venda;
	}
	public void setPreco_venda(Double preco_venda) {
		this.preco_venda = preco_venda;
	}
	public Double getPercentual_desconto() {
		return percentual_desconto;
	}
	public void setPercentual_desconto(Double percentual_desconto) {
		this.percentual_desconto = percentual_desconto;
	}
	public Double getValor_bruto() {
		return valor_bruto;
	}
	public void setValor_bruto(Double valor_bruto) {
		this.valor_bruto = valor_bruto;
	}
	public Double getValor_liquido() {
		return valor_liquido;
	}
	public void setValor_liquido(Double valor_liquido) {
		this.valor_liquido = valor_liquido;
	}

}
