package com.marco.virtualstore.dtos;

import com.marco.virtualstore.domains.Categoria;

/**
 * Created by Marco Antônio on 24/05/2018
 */
public class CategoriaDto {


    private Long id;

    private String nome;


    public CategoriaDto() {}

    public CategoriaDto(Categoria categoria){
        id = categoria.getId();
        nome = categoria.getNome();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
