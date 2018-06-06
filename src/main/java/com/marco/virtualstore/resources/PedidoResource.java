package com.marco.virtualstore.resources;

import com.marco.virtualstore.domains.Pedido;
import com.marco.virtualstore.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

/**
 * Created by Marco Antônio on 22/05/2018
 */
@RestController
@RequestMapping("/pedidos")
public class PedidoResource {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("{id}")
    public ResponseEntity<Pedido> find(@PathVariable Long id){
        Pedido pedido = this.pedidoService.find(id);
        return ResponseEntity.ok(pedido);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody Pedido pedido){
        pedido = this.pedidoService.insert(pedido);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("{id}")
                .buildAndExpand(pedido.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
