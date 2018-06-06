package com.marco.virtualstore.dtos;

import com.marco.virtualstore.domains.Produto;

import java.io.Serializable;

public class ProdutoDto implements Serializable {

    private static final long serialVersionUID = -19377242749757759L;

    private Long id;

    private String nome;

    private Double preco;

    public ProdutoDto() {
    }

    public ProdutoDto(Produto produto){
        id = produto.getId();
        nome = produto.getNome();
        preco = produto.getPreco();
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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
