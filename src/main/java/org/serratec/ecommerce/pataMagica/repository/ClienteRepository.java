package org.serratec.ecommerce.pataMagica.repository;

import org.serratec.ecommerce.pataMagica.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
