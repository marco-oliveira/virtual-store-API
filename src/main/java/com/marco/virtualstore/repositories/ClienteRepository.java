package com.marco.virtualstore.repositories;

import com.marco.virtualstore.domains.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Marco Antônio on 19/05/2018
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
