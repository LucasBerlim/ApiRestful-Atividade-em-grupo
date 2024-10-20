package org.serratec.ecommerce.pataMagica.service;

import java.util.List;
import java.util.Optional;

import org.serratec.ecommerce.pataMagica.dto.ItemPedidoDto;
import org.serratec.ecommerce.pataMagica.dto.ItemPedidoDtoCadastroPedido;
import org.serratec.ecommerce.pataMagica.dto.PedidoDto;
import org.serratec.ecommerce.pataMagica.dto.PedidoDtoCadastroPedido;
import org.serratec.ecommerce.pataMagica.dto.ProdutoDto;
import org.serratec.ecommerce.pataMagica.dto.RelatorioPedidoDto;
import org.serratec.ecommerce.pataMagica.model.ItemPedido;
import org.serratec.ecommerce.pataMagica.model.Pedido;
import org.serratec.ecommerce.pataMagica.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repository;
	@Autowired
	private ProdutoService produtoService;
	
	public List<PedidoDto> obterTodos(){
		return repository.findAll().stream().map(p -> PedidoDto.toDto(p)).toList();
	}
	
	public Optional<PedidoDto> obterPorId(Long id){
		if(!repository.existsById(id)) {
			return Optional.empty();
		}
		return Optional.of(PedidoDto.toDto(repository.findById(id).get()));
	}
	
	public PedidoDtoCadastroPedido calcularPedido(PedidoDtoCadastroPedido dto) {
		double valorTotal = 0;
		Pedido pedido = dto.toEntity();
		
		for (ItemPedidoDtoCadastroPedido ip : pedido.getItensPedido().stream().map(ip -> 
				ItemPedidoDtoCadastroPedido.toDto(ip)).toList()) {
			Optional<ProdutoDto> produto = produtoService.obterPorId(ip.getProdutoId());
			if(produto.isPresent()) {
				
				ip.setValorBruto(produto.get().valorUnitario() * ip.getQuantidade());
				ip.setValorLiquido(ip.getValorBruto() - ip.getPercentualDesconto());
				valorTotal += ip.getValorLiquido();
				
			}
		}
		System.out.println("Valor total:" + valorTotal);
		pedido.setValorTotal(valorTotal);
		return dto;
	}
	
	public PedidoDtoCadastroPedido salvarPedido(PedidoDtoCadastroPedido dto) {
		// talvez implementar a lógica da conta aqui, através do método calcularPedido:
		Pedido pedido = calcularPedido(dto).toEntity();
		
		Pedido pedidoEntity = repository.save(pedido);
		//Pedido pedidoEntity = repository.save(dto.toEntity());
		return PedidoDtoCadastroPedido.toDto(pedidoEntity);
		
		//Pedido pedidoEntity = repository.save(dto.toEntity());
		//return PedidoDtoCadastroPedido.toDto(pedidoEntity);
	}
	
	/*public PedidoDto salvarPedido(PedidoDto dto) {
		Pedido pedidoEntity = repository.save(dto.toEntity());
		return PedidoDto.toDto(pedidoEntity);
	}*/
	
	/*public boolean apagarPedido(Long id) {
		if(!repository.existsById(id)) {
			return false;
		}
		repository.deleteById(id);
		return true;
	}*/
	
	public boolean apagarPedido(Long id) {
		if(!repository.existsById(id)) {
			return false;
		}
		repository.deletarPorId(id);
		return true;
	}

	public Optional<PedidoDto> alterarPedido(Long id, PedidoDto dto){
		if(!repository.existsById(id)) {
			return Optional.empty();
		}
		Pedido pedidoEntity = dto.toEntity();
		pedidoEntity.setId(id);
		repository.save(pedidoEntity);
		return Optional.of(PedidoDto.toDto(pedidoEntity));
	}
	
	public RelatorioPedidoDto gerarRelatorioPedido(Long id) {
        Pedido pedido = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        return RelatorioPedidoDto.toDto(pedido);
    }
}
