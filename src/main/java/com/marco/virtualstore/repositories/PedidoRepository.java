package com.marco.virtualstore.repositories;

import com.marco.virtualstore.domains.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Marco Antônio on 20/05/2018
 */

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
