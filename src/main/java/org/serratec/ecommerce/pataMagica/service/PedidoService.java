package org.serratec.ecommerce.pataMagica.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sound.midi.Soundbank;

import org.serratec.ecommerce.pataMagica.dto.ItemPedidoDtoCadastroPedido;
import org.serratec.ecommerce.pataMagica.dto.PedidoDto;
import org.serratec.ecommerce.pataMagica.dto.PedidoDtoCadastroPedido;
import org.serratec.ecommerce.pataMagica.dto.ProdutoDto;
import org.serratec.ecommerce.pataMagica.dto.RelatorioPedidoDto;
import org.serratec.ecommerce.pataMagica.model.ItemPedido;
import org.serratec.ecommerce.pataMagica.model.Pedido;
import org.serratec.ecommerce.pataMagica.repository.PedidoRepository;
import org.serratec.ecommerce.pataMagica.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repository;
	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private EmailService emailService;
	@Autowired ProdutoRepository produtoRepository;
	
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
		double valorBruto = 0;
		double valorLiquido = 0;
		Pedido pedido = dto.toEntity(produtoRepository);
		
		int i = 0;
		for (ItemPedidoDtoCadastroPedido ip : pedido.getItensPedido().stream().map(ip -> 
				ItemPedidoDtoCadastroPedido.toDto(ip)).toList()) {
			Optional<ProdutoDto> produto = produtoService.obterPorId(ip.getProdutoId());
			if(produto.isPresent()) {
				
				ip.setValorBruto(produto.get().getValorUnitario() * ip.getQuantidade());
			    valorBruto = ip.getValorBruto();
			    double percentualDesconto = ip.getPercentualDesconto() / 100.0;
			    double valorDesconto = valorBruto * percentualDesconto;
				ip.setValorLiquido(ip.getValorBruto() - valorDesconto);
				valorLiquido = ip.getValorLiquido();
				valorTotal += valorLiquido;
				
			
				
				dto.getItensPedido().get(i).setValorBruto(valorBruto);
				dto.getItensPedido().get(i).setValorLiquido(valorLiquido);
				dto.getItensPedido().get(i).setPrecoVenda(produto.get().getValorUnitario());
				i++;
			}
		}
		dto.setValorTotal(valorTotal);
		pedido.setValorTotal(valorTotal);
		return dto;
	}
	
	public PedidoDtoCadastroPedido salvarPedido(PedidoDtoCadastroPedido dto) {
	    Pedido pedido = calcularPedido(dto).toEntity(produtoRepository);
	    Pedido pedidoEntity = repository.save(pedido);

	    Pedido pedidoCompleto = repository.findById(pedidoEntity.getId())
	        .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

	    enviarRelatorioPorEmail(pedidoCompleto);
	    return PedidoDtoCadastroPedido.toDto(pedidoCompleto);
	}

	private void enviarRelatorioPorEmail(Pedido pedidoCompleto) {
	    RelatorioPedidoDto relatorio = RelatorioPedidoDto.toDto(pedidoCompleto);
	    try {
	        emailService.enviarRelatorioPedido(relatorio, "berlimtere@gmail.com");
	    } catch (MessagingException e) {
	        throw new RuntimeException("Erro ao enviar email", e);
	    }
	}
	
	public boolean apagarPedido(Long id) {
		if(!repository.existsById(id)) {
			return false;
		}
		repository.deletarPorId(id);
		return true;
	}
	
	public Optional<PedidoDtoCadastroPedido> alterarPedido(Long id, PedidoDtoCadastroPedido dto){
		if(!repository.existsById(id)) {
			return Optional.empty();
		}
		
		//List<ItemPedidoDtoCadastroPedido> itens = dto.getItensPedido();
		PedidoDtoCadastroPedido novoDto = dto;
		
		Pedido pedidoEntity = calcularPedido(novoDto).toEntity(produtoRepository);
		pedidoEntity.setId(id);
		repository.save(pedidoEntity);
		return Optional.of(PedidoDtoCadastroPedido.toDto(pedidoEntity));
	}
	
	public RelatorioPedidoDto gerarRelatorioPedido(Long id) {
        Pedido pedido = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        
        /*if (enviaEmail) {
        	emailService.enviarEmail("berlimtere@gmail.com", "Novo pedido", RelatorioPedidoDto.toDto(pedido).toString());
        	//emailService.enviarRelatorioPedido(relatorio, "berlimtere@gmail.com");
        }*/
        
        
        return RelatorioPedidoDto.toDto(pedido);
    }
}
