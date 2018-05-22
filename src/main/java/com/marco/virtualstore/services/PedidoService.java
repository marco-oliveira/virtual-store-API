package com.marco.virtualstore.services;

import com.marco.virtualstore.domains.Pedido;
import com.marco.virtualstore.repositories.ItemPedidoRepository;
import com.marco.virtualstore.repositories.PedidoRepository;
import com.marco.virtualstore.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Marco Antônio on 22/05/2018
 */
@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido find(Long id){

        Optional<Pedido> pedido = pedidoRepository.findById(id);

        return pedido.orElseThrow(() ->
                new ObjectNotFoundException("Não pode encontrar o objeto com id "+id+" Tipo: "+
                        Pedido.class.getName()));
    }


}
