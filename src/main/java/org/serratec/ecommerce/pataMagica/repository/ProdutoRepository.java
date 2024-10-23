package org.serratec.ecommerce.pataMagica.repository;

import org.serratec.ecommerce.pataMagica.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	Produto findByDescricao(String descricao);
}
