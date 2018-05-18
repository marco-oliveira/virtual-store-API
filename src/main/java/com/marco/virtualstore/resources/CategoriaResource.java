package com.marco.virtualstore.resources;

import com.marco.virtualstore.domains.Categoria;
import com.marco.virtualstore.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Marco Antônio on 18/05/2018
 */

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("{id}")
    public ResponseEntity<?> find(@PathVariable Long id){

        Categoria categoria = this.categoriaService.buscaPorId(id);
        return ResponseEntity.ok(categoria);
    }

}
