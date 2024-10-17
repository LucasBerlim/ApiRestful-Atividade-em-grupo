package org.serratec.ecommerce.pataMagica.dto;

import java.time.LocalDate;

import org.serratec.ecommerce.pataMagica.model.Cliente;
import org.serratec.ecommerce.pataMagica.model.Endereco;

public record ClienteDto(
		Long id,
		String email,
		String nomeCompleto,
		String cpf,
		String telefone,
		LocalDate dataNascimento,
		Endereco endereco
		) {

	
	public Cliente toEntity() {
		Cliente cliente = new Cliente();
		cliente.setId(this.id);
		cliente.setEmail(this.email);
		cliente.setNomeCompleto(this.nomeCompleto);
		cliente.setCpf(this.cpf);
		cliente.setTelefone(this.telefone);
		cliente.setDataNascimento(this.dataNascimento);
		cliente.setEndereco(this.endereco);
		return cliente;
	}
	
	public static ClienteDto toDto(Cliente cliente) {
        return new ClienteDto(cliente.getId(), cliente.getEmail(), cliente.getNomeCompleto(), 
        		cliente.getCpf(), cliente.getTelefone(), cliente.getDataNascimento(), 
        		cliente.getEndereco());
	}
}
