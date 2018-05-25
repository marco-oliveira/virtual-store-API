package com.marco.virtualstore.dtos;

import com.marco.virtualstore.domains.Categoria;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

/**
 * Created by Marco Antônio on 24/05/2018
 */
public class CategoriaDto {

    private Long id;

    @NotEmpty(message = "O campo é Obrigatório.")
    @Length(min = 5, max = 80, message = "Deve ter entre 5 a 80 caracteres.")
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
