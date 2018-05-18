package com.marco.virtualstore.repositories;

import com.marco.virtualstore.domains.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Marco Antônio on 18/05/2018
 */
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
