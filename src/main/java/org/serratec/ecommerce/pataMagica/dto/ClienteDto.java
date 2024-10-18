package org.serratec.ecommerce.pataMagica.dto;

import java.time.LocalDate;
import java.util.List;

import org.serratec.ecommerce.pataMagica.model.Cliente;
import org.serratec.ecommerce.pataMagica.model.Endereco;

public record ClienteDto(
		Long id,
		String email,
		String nomeCompleto,
		String cpf,
		String telefone,
		LocalDate dataNascimento,
		EnderecoDto endereco
		) {

	public Cliente toEntity() {
		Cliente cliente = new Cliente();
		cliente.setId(this.id);
		cliente.setEmail(this.email);
		cliente.setNomeCompleto(this.nomeCompleto);
		cliente.setCpf(this.cpf);
		cliente.setTelefone(this.telefone);
		cliente.setDataNascimento(this.dataNascimento);
		cliente.setEndereco(this.endereco.toEntity());
		return cliente;
	}
	
	public static ClienteDto toDto(Cliente cliente) {
        return new ClienteDto(cliente.getId(), cliente.getEmail(), cliente.getNomeCompleto(), 
        		cliente.getCpf(), cliente.getTelefone(), cliente.getDataNascimento(), 
        		EnderecoDto.toDto(cliente.getEndereco()));
	} 
}
