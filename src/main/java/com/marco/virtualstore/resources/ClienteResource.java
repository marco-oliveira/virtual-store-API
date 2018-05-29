package com.marco.virtualstore.resources;

import com.marco.virtualstore.domains.Cliente;
import com.marco.virtualstore.dtos.ClienteDto;
import com.marco.virtualstore.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Marco Antônio on 19/05/2018
 */
@RestController
@RequestMapping("/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("{id}")
    public ResponseEntity<Cliente> find(@PathVariable Long id){
        Cliente cliente = this.clienteService.find(id);

        return ResponseEntity.ok(cliente);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody ClienteDto clienteDto, @PathVariable Long id){
        Cliente cliente = this.clienteService.fromDto(clienteDto);
        cliente.setId(id);
        this.clienteService.update(cliente);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.clienteService.delete(id);
        return ResponseEntity.noContent().build();

    }

    @GetMapping
    public ResponseEntity<List<ClienteDto>> findAll(){
        List<Cliente> clientes = this.clienteService.findAll();
        List<ClienteDto> listDto = clientes.stream().map(cliente -> new ClienteDto(cliente)).collect(Collectors.toList());
        return ResponseEntity.ok(listDto);
    }

    @GetMapping("page")
    public ResponseEntity<Page<ClienteDto>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "24") Integer size,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy
    ){
        Page<Cliente> clientes = this.clienteService.findPage(page, size, direction, orderBy);
        Page<ClienteDto> pageClientes = clientes.map(cliente -> new ClienteDto(cliente));
        return ResponseEntity.ok(pageClientes);
    }
}

