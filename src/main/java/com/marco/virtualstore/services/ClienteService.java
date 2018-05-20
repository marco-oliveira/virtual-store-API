package com.marco.virtualstore.services;

import com.marco.virtualstore.domains.Cliente;
import com.marco.virtualstore.repositories.ClienteRepository;
import com.marco.virtualstore.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Marco Antônio on 19/05/2018
 */
@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente buscarPorId(Long id) {

        Optional<Cliente> cliente = this.clienteRepository.findById(id);
        return  cliente.orElseThrow(() ->
                new ObjectNotFoundException("Objeto não Encontrado id: "+id+" Tipo: "+ Cliente.class.getName()));
    }
}
