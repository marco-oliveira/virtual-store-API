package com.marco.virtualstore.services;

import com.marco.virtualstore.domains.ItemPedido;
import com.marco.virtualstore.domains.PagamentoComBoleto;
import com.marco.virtualstore.domains.Pedido;
import com.marco.virtualstore.domains.enums.EstadoPagamento;
import com.marco.virtualstore.repositories.ItemPedidoRepository;
import com.marco.virtualstore.repositories.PagamentoRepository;
import com.marco.virtualstore.repositories.PedidoRepository;
import com.marco.virtualstore.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by Marco Antônio on 22/05/2018
 */
@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public Pedido find(Long id){

        Optional<Pedido> pedido = pedidoRepository.findById(id);

        return pedido.orElseThrow(() ->
                new ObjectNotFoundException("Não pode encontrar o objeto com id "+id+" Tipo: "+
                        Pedido.class.getName()));
    }


    public Pedido insert(Pedido pedido) {
        pedido.setId(null);
        pedido.setInstance(new Date());
        pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        pedido.getPagamento().setPedido(pedido);

        if (pedido.getPagamento() instanceof PagamentoComBoleto){
            PagamentoComBoleto pagamento = (PagamentoComBoleto) pedido.getPagamento();
            this.boletoService.preencherPagamentoComBoleto(pagamento, pedido.getInstance());
        }
        pedido = this.pedidoRepository.save(pedido);
        this.pagamentoRepository.save(pedido.getPagamento());
        Pedido finalPedido = pedido;
        pedido.getItens().forEach(itemPedido -> {
           itemPedido.setDesconto(0.0);
           itemPedido.setPreco(this.produtoService.find(itemPedido.getProduto().getId()).getPreco());
           itemPedido.setPedido(finalPedido);
       });
        this.itemPedidoRepository.saveAll(pedido.getItens());

        return pedido;
    }
}
