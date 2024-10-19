package org.serratec.ecommerce.pataMagica.repository;

import java.util.List;

import org.serratec.ecommerce.pataMagica.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{

	@Query("SELECT p FROM Pedido p JOIN p.itensPedido ip WHERE p.id = :id")
	List<Pedido> gerarRelatorioPedido(Long id);
	
	@Modifying
	@Transactional
	@Query("DELETE FROM Pedido p WHERE p.id = :id")
	void deletarPorId(Long id);
} 
