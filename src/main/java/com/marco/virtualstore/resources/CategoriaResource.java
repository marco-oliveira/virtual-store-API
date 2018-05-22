package com.marco.virtualstore.resources;

import com.marco.virtualstore.domains.Categoria;
import com.marco.virtualstore.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * Created by Marco Antônio on 18/05/2018
 */

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("{id}")
    public ResponseEntity<Categoria> find(@PathVariable Long id){

        Categoria categoria = this.categoriaService.find(id);
        return ResponseEntity.ok(categoria);
    }

    /**
     * Não tem o corpo como resposta, apenas a URI no cabeçalho --> Boa prática REST
     *
     * @param categoria
     * @return 201
     */
    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Categoria categoria){
        categoria = this.categoriaService.insert(categoria);
        //Boa prática uri no cabeçalho da resposta 201
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    /**
     * Atualiza Categoria
     *
     * @param id
     * @param categoria
     * @return 204
     */
    @PutMapping("{id}")
    public ResponseEntity<Void> update(@RequestBody Categoria categoria, @PathVariable Long id){
        categoria.setId(id);

        this.categoriaService.update(categoria);

        return ResponseEntity.noContent().build();
    }

}
