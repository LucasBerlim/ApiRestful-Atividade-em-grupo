package org.serratec.ecommerce.pataMagica.dto;

import java.time.LocalDate;

import org.serratec.ecommerce.pataMagica.model.Categoria;
import org.serratec.ecommerce.pataMagica.model.Produto;

public class ProdutoDto {
	private Long id;
	private String nome;
	private String descricao;
	private int qtdEstoque;
	private LocalDate dataCadastro;
	private Double valorUnitario;
	private String imagem;
	private Long categoriaId;

	public ProdutoDto() {
	}

	public ProdutoDto(Long id, String nome, String descricao, int qtdEstoque, LocalDate dataCadastro,
			Double valorUnitario, String imagem, Long categoriaId) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.qtdEstoque = qtdEstoque;
		this.dataCadastro = dataCadastro;
		this.valorUnitario = valorUnitario;
		this.imagem = imagem;
		this.categoriaId = categoriaId;
	}

	public Produto toEntity() {
		Produto produto = new Produto();
		produto.setId(this.id);
		produto.setNome(this.nome);
		produto.setDescricao(this.descricao);
		produto.setQtdEstoque(this.qtdEstoque);
		produto.setDataCadastro(this.dataCadastro);
		produto.setValorUnitario(this.valorUnitario);
		produto.setImagem(this.imagem);
		Categoria categoria = new Categoria();
		produto.setCategoria(categoria);
		produto.getCategoria().setId(this.categoriaId);
		return produto;
	}

	public static ProdutoDto toDto(Produto produto) {
		return new ProdutoDto(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getQtdEstoque(),
				produto.getDataCadastro(), produto.getValorUnitario(), produto.getImagem(),
				produto.getCategoria().getId());
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

	public Long getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}
}
