package com.marco.virtualstore.dtos;

import com.marco.virtualstore.domains.Cliente;
import com.marco.virtualstore.services.validations.ClienteUpdate;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * Created by Marco Antônio on 28/05/2018
 */
@ClienteUpdate
public class ClienteDto implements Serializable {

    private static final long serialVersionUID = -7375799058490653550L;

    private Long id;

    @NotEmpty(message = "O campo é Obrigatório.")
    @Length(min = 5, max = 100, message = "Deve ter entre 5 a 80 caracteres.")
    private String nome;

    @NotEmpty(message = "O campo é Obrigatório.")
    @Email(message = "Email inválido.")
    private String email;

    public ClienteDto() {
    }

    public ClienteDto(Cliente cliente){
        id = cliente.getId();
        nome = cliente.getNome();
        email = cliente.getEmail();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
