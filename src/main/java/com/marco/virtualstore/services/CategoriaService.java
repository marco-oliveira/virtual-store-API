package com.marco.virtualstore.services;

import com.marco.virtualstore.domains.Categoria;
import com.marco.virtualstore.repositories.CategoriaRepository;
import com.marco.virtualstore.services.exceptions.DataIntegrityException;
import com.marco.virtualstore.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by Marco Antônio on 18/05/2018
 */

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria find(Long id) {
        Optional<Categoria> categoria = this.categoriaRepository.findById(id);
        return categoria.orElseThrow(() ->
                new ObjectNotFoundException("Objeto não encontrado! Id: "+id+", Tipo: "+Categoria.class.getName()));
    }

    public Categoria insert(Categoria categoria) {
        categoria.setId(null); //Garantir que sera salva uma categoria nova
        return this.categoriaRepository.save(categoria);

    }

    public Categoria update(Categoria categoria) {
        find(categoria.getId()); //se não existir lança exception
        return this.categoriaRepository.save(categoria);
    }

    public void delete(Long id) {
        find(id);
        try {
            this.categoriaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Categoria com produto não pode ser apagada.");
        }
    }

    public List<Categoria> findAll() {

        return this.categoriaRepository.findAll();
    }

    public Page<Categoria> findPage(Integer page, Integer size, String direction, String orderBy ){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy);
        return this.categoriaRepository.findAll(pageRequest);
    }
}

