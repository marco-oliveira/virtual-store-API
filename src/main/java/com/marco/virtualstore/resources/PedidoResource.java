package com.marco.virtualstore.resources;

import com.marco.virtualstore.domains.Pedido;
import com.marco.virtualstore.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Marco Antônio on 22/05/2018
 */
@RestController
@RequestMapping("/pedidos")
public class PedidoResource {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("{id}")
    public ResponseEntity<?> find(@PathVariable Long id){
        Pedido pedido = this.pedidoService.buscarPorId(id);
        return ResponseEntity.ok(pedido);
    }

}
