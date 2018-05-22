package com.marco.virtualstore.repositories;

import com.marco.virtualstore.domains.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Marco Antônio on 21/05/2018
 */
@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
}
