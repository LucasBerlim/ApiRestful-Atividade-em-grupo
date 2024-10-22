package org.serratec.ecommerce.pataMagica.repository;

import org.serratec.ecommerce.pataMagica.model.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

	@Modifying
	@Transactional
	@Query("DELETE FROM ItemPedido ip WHERE ip.pedido.id = :pedidoId")
    void deleteByPedidoId(Long pedidoId);
}
