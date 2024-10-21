package org.serratec.ecommerce.pataMagica.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "produtos")
public class Produto {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private int qtdEstoque;
	private LocalDate dataCadastro;
	private Double valorUnitario;
	private String imagem;
	@JsonBackReference
	//@JsonManagedReference
	@OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<ItemPedido> itensPedido;
	@JsonManagedReference
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private Categoria categoria;
	
	
	public List<ItemPedido> getItensPedido() {
		return itensPedido;
	}
	public void setItensPedido(List<ItemPedido> itensPedido) {
		itensPedido.forEach(ip -> ip.setProduto(this));
		this.itensPedido = itensPedido;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getQtdEstoque() {
		return qtdEstoque;
	}
	public void setQtdEstoque(int qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}
	public LocalDate getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public Double getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	
	@Override
	public String toString() {
		return "Código produto =" + id + ", Nome Produto =" + nome;
	}
}
