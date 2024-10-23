package org.serratec.ecommerce.pataMagica.service;

import java.util.List;
import java.util.Optional;

import org.serratec.ecommerce.pataMagica.dto.ItemPedidoDtoCadastroPedido;
import org.serratec.ecommerce.pataMagica.dto.PedidoDto;
import org.serratec.ecommerce.pataMagica.dto.PedidoDtoCadastroPedido;
import org.serratec.ecommerce.pataMagica.dto.ProdutoDto;
import org.serratec.ecommerce.pataMagica.dto.RelatorioPedidoDto;
import org.serratec.ecommerce.pataMagica.model.Cliente;
import org.serratec.ecommerce.pataMagica.model.Pedido;
import org.serratec.ecommerce.pataMagica.repository.ClienteRepository;
import org.serratec.ecommerce.pataMagica.repository.ItemPedidoRepository;
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
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	@Autowired
	private ClienteRepository clienteRepository;

	public List<PedidoDto> obterTodos() {
		return repository.findAll().stream().map(p -> PedidoDto.toDto(p)).toList();
	}

	public Optional<PedidoDto> obterPorId(Long id) {
		if (!repository.existsById(id)) {
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
		for (ItemPedidoDtoCadastroPedido ip : pedido.getItensPedido().stream()
				.map(ip -> ItemPedidoDtoCadastroPedido.toDto(ip)).toList()) {
			Optional<ProdutoDto> produto = produtoService.obterPorId(ip.getProdutoId());
			if (produto.isPresent()) {

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
		
		Long clienteId = dto.getClienteId();
		Optional<Cliente> cliente = clienteRepository.findById(clienteId);
		String email = cliente.get().getEmail();
		
		enviarRelatorioPorEmail(pedidoCompleto, email);
		return PedidoDtoCadastroPedido.toDto(pedidoCompleto);
	}

	private void enviarRelatorioPorEmail(Pedido pedidoCompleto, String email) {
		RelatorioPedidoDto relatorio = RelatorioPedidoDto.toDto(pedidoCompleto);
		
		try {
			emailService.enviarRelatorioPedido(relatorio, email);
		} catch (MessagingException e) {
			throw new RuntimeException("Erro ao enviar email", e);
		}
	}

	public boolean apagarPedido(Long id) {
		if (!repository.existsById(id)) {
			return false;
		}
		repository.deletarPorId(id);
		return true;
	}

	public Optional<PedidoDtoCadastroPedido> alterarPedido(Long id, PedidoDtoCadastroPedido dto) {
		if (!repository.existsById(id)) {
			return Optional.empty();
		}
		itemPedidoRepository.deleteByPedidoId(id);

		Pedido pedidoEntity = calcularPedido(dto).toEntity(produtoRepository);
		pedidoEntity.setId(id);
		repository.save(pedidoEntity);

		return Optional.of(PedidoDtoCadastroPedido.toDto(pedidoEntity));
	}

	public RelatorioPedidoDto gerarRelatorioPedido(Long id) {
		Pedido pedido = repository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

		return RelatorioPedidoDto.toDto(pedido);
	}
}
