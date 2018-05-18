package com.marco.virtualstore.services;

import com.marco.virtualstore.domains.Categoria;
import com.marco.virtualstore.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Marco Antônio on 18/05/2018
 */

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria buscaPorId(Long id) {
        Optional<Categoria> categoria = this.categoriaRepository.findById(id);
        return categoria.orElse(null);
    }
}
