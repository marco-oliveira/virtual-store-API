package com.marco.virtualstore.repositories;

import com.marco.virtualstore.domains.Categoria;
import com.marco.virtualstore.domains.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Marco Antônio on 18/05/2018
 */
@Repository
@Transactional(readOnly = true)
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    //Utilizando JPQL
    @Query("SELECT DISTINCT produto FROM Produto produto INNER JOIN produto.categorias cat WHERE produto.nome LIKE %:nome% AND cat IN :categorias")
    Page<Produto> search(@Param("nome") String nome,
                         @Param("categorias") List<Categoria> categorias,
                         Pageable pageable);


    //Utilizando método do spring data
    Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome, List<Categoria> categorias, Pageable pageable);
}
