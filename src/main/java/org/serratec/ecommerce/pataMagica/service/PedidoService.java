package org.serratec.ecommerce.pataMagica.service;

import java.util.List;

import org.serratec.ecommerce.pataMagica.dto.PedidoDto;
import org.serratec.ecommerce.pataMagica.model.Pedido;
import org.serratec.ecommerce.pataMagica.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repository;
	
	public List<PedidoDto> obterTodos(){
		return repository.findAll().stream().map(p -> PedidoDto.toDto(p)).toList();
	}
	
	public PedidoDto salvarPedido(PedidoDto dto) {
		Pedido pedidoEntity = repository.save(dto.toEntity());
		return PedidoDto.toDto(pedidoEntity);
		//return PedidoDto.toDto(repositorio.save(dto.toEntity()));
	}
}
