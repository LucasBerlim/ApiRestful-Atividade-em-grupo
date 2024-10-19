package org.serratec.ecommerce.pataMagica.repository;

import java.util.List;

import org.serratec.ecommerce.pataMagica.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{

	@Query("SELECT p FROM Pedido p JOIN p.itensPedido ip WHERE p.id = :id")
	List<Pedido> gerarRelatorioPedido(Long id);

	/*@Query("SELECT p.id, p.dataPedido, p.valorTotal, " +
	           "ip.produto.id, ip.produto.nome, ip.produto.valorUnitario, ip.quantidade, " +
	           "ip.percentualDesconto, ip.valorLiquido " +
	           "FROM Pedido p JOIN p.itensPedido ip WHERE p.id = :id")
	List<Pedido> gerarRelatorioPedido(Long id);*/

} 
