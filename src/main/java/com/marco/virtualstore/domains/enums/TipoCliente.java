package com.marco.virtualstore.domains.enums;

/**
 * Created by Marco Antônio on 19/05/2018
 */
public enum TipoCliente {

    PESSOAFISICA(1, "Pessoa Física"),
    PESSOAJURIDICA(2, "Pessoa Jurídica");

    private int cod;
    private String descricao;

    TipoCliente(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoCliente toEnum(Integer cod){
        if (cod == null){
            return null;
        }

        for (TipoCliente tipo: TipoCliente.values()) {
            if (cod.equals(tipo.getCod())){
                return tipo;
            }
        }
        throw new IllegalArgumentException("Código Inválido: "+cod);
    }
}
