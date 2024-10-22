package org.serratec.ecommerce.pataMagica.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "clientes")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@Email(message = "Email deve ser válido")
	private String email;
	@NotBlank(message = "O nome é obrigatório")
	@Size(min = 2, max = 255, message = "Nome deve ter entre 2 e 255 caracteres")
	private String nomeCompleto;
	@NotBlank(message = "Cpf é obrigatório")
	@Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}|\\d{11}", message = "CPF inválido")
	private String cpf;
	@Pattern(regexp = "^(\\(?\\d{2}\\)?\\s?)?(\\d{4,5}[-\\s]?\\d{4})$", message = "O número de telefone deve ser válido.")
	private String telefone;
	@NotNull(message = "Data de nascimento é obrigatória")
	@Past(message = "Data de nascimento deve ser no passado")
	private LocalDate dataNascimento;
	@JsonIgnoreProperties
	@OneToOne(cascade = CascadeType.ALL)
	private Endereco endereco;
	@JsonManagedReference
	// @JsonIgnoreProperties
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Pedido> pedidos;

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		pedidos.forEach(p -> p.setCliente(this));
		this.pedidos = pedidos;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", email=" + email + ", nomeCompleto=" + nomeCompleto + ", cpf=" + cpf
				+ ", telefone=" + telefone + ", dataNascimento=" + dataNascimento + ", endereco=" + endereco
				+ ", pedidos=" + pedidos + "]";
	}
}
