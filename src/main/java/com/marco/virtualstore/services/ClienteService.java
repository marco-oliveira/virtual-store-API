package com.marco.virtualstore.services;

import com.marco.virtualstore.domains.Cliente;
import com.marco.virtualstore.dtos.ClienteDto;
import com.marco.virtualstore.repositories.ClienteRepository;
import com.marco.virtualstore.services.exceptions.DataIntegrityException;
import com.marco.virtualstore.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by Marco Antônio on 19/05/2018
 */
@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente find(Long id) {

        Optional<Cliente> cliente = this.clienteRepository.findById(id);
        return  cliente.orElseThrow(() ->
                new ObjectNotFoundException("Objeto não Encontrado id: "+id+" Tipo: "+ Cliente.class.getName()));
    }

    public void update(Cliente cliente) {
        Cliente newCliente = find(cliente.getId());
        update(newCliente, cliente);
        this.clienteRepository.save(newCliente);
    }

    private void update(Cliente newCliente, Cliente cliente) {
        newCliente.setNome(cliente.getNome());
        newCliente.setEmail(cliente.getEmail());
    }

    public void delete(Long id) {
        find(id);
        try {
            this.clienteRepository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Cliente com dependencias cadastradas não pode ser apagado.");
        }
    }

    public List<Cliente> findAll(){
        return this.clienteRepository.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer size, String direction, String orderBy){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy);
        return this.clienteRepository.findAll(pageRequest);
    }

    public Cliente fromDto(ClienteDto clienteDto) {
        return new Cliente(clienteDto.getId(), clienteDto.getNome(), clienteDto.getEmail(),null, null);
    }
}
