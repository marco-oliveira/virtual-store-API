package com.marco.virtualstore.domains;

import com.marco.virtualstore.domains.enums.EstadoPagamento;

import javax.persistence.Entity;

/**
 * Created by Marco Antônio on 20/05/2018
 */
@Entity
public class PagamentoComCartao extends Pagamento {

    private static final long serialVersionUID = 5466190406393694181L;

    private Integer numeroDeParcelas;

    public PagamentoComCartao() {}

    public PagamentoComCartao(Long id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
        super(id, estado, pedido);
        this.numeroDeParcelas = numeroDeParcelas;
    }

    public Integer getNumeroDeParcelas() {
        return numeroDeParcelas;
    }

    public void setNumeroDeParcelas(Integer numeroDeParcelas) {
        this.numeroDeParcelas = numeroDeParcelas;
    }
}
