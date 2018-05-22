package com.marco.virtualstore.resources;

import com.marco.virtualstore.domains.Cliente;
import com.marco.virtualstore.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
