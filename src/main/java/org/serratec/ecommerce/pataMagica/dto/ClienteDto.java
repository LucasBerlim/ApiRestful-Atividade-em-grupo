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
		Endereco endereco
		//List<PedidoDto> pedidos
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
		//cliente.setPedidos(this.pedidos.stream().map(p -> p.toEntity()).toList());
		return cliente;
	}
	
	public static ClienteDto toDto(Cliente cliente) {
        return new ClienteDto(cliente.getId(), cliente.getEmail(), cliente.getNomeCompleto(), 
        		cliente.getCpf(), cliente.getTelefone(), cliente.getDataNascimento(), 
        		cliente.getEndereco());
	} //, cliente.getPedidos().stream().map(p -> PedidoDto.toDto(p)).toList()
}
