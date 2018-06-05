package com.marco.virtualstore.services;

import com.marco.virtualstore.domains.Cidade;
import com.marco.virtualstore.domains.Cliente;
import com.marco.virtualstore.domains.Endereco;
import com.marco.virtualstore.domains.enums.TipoCliente;
import com.marco.virtualstore.dtos.ClienteDto;
import com.marco.virtualstore.dtos.NewClienteDto;
import com.marco.virtualstore.repositories.CidadeRepository;
import com.marco.virtualstore.repositories.ClienteRepository;
import com.marco.virtualstore.repositories.EnderecoRepository;
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
import java.util.OptionalLong;

/**
 * Created by Marco Antônio on 19/05/2018
 */
@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Cliente find(Long id) {

        Optional<Cliente> cliente = this.clienteRepository.findById(id);
        return  cliente.orElseThrow(() ->
                new ObjectNotFoundException("Objeto não Encontrado id: "+id+" Tipo: "+ Cliente.class.getName()));
    }

    @Transactional
    public Cliente insert(Cliente cliente) {
        cliente.setId(null);
        cliente = this.clienteRepository.save(cliente);
        this.enderecoRepository.saveAll(cliente.getEnderecos());
        return cliente;
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
            throw new DataIntegrityException("Cliente com pedidos, não podem ser apagados.");
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

    public Cliente fromDto(NewClienteDto newClienteDto) {

        Cliente cliente = new Cliente(null, newClienteDto.getNome(),
                newClienteDto.getEmail(), newClienteDto.getCpfOuCnpj(),
                TipoCliente.toEnum(newClienteDto.getTipo()));
        Cidade cidade = new Cidade(newClienteDto.getCidadeId(), null, null);
        Endereco endereco = new Endereco(null, newClienteDto.getLogradouro(),
                newClienteDto.getNumero(), newClienteDto.getComplemento(),
                newClienteDto.getBairro(), newClienteDto.getCep(), cidade, cliente);
        cliente.getEnderecos().add(endereco);
        cliente.getTelefones().add(newClienteDto.getTelefone1());
        if (newClienteDto.getTelefone2()!=null){
            cliente.getTelefones().add(newClienteDto.getTelefone2());
        }
        if (newClienteDto.getTelefone3()!= null){
            cliente.getTelefones().add(newClienteDto.getTelefone3());
        }

        return cliente;
    }


}
