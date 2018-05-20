package com.marco.virtualstore.domains.enums;

/**
 * Created by Marco Antônio on 20/05/2018
 */
public enum EstadoPagamento {

    PENDENTE(1, "Pendente"),
    QUITADO(2, "Quitado"),
    CANCELADO(3, "Cancelado");

    private Integer cod;
    private String descricao;

    EstadoPagamento(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public Integer getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static EstadoPagamento toEnum(Integer cod){
        if (cod == null){
            return null;
        }

        for(EstadoPagamento estado : EstadoPagamento.values()){
            if (cod.equals(estado.getCod())){
                return estado;
            }
        }
        throw new IllegalArgumentException("Código Inválido "+cod);
    }
}
