package com.marco.virtualstore.domains.enums;

/**
 * Created by Marco Antônio on 19/05/2018
 */
public enum Perfil {

    ADMIN(1, "ROLE_ADMIN"),
    CLIENTE(2, "ROLE_CLIENTE");

    private int cod;
    private String descricao;

    Perfil(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Perfil toEnum(Integer cod){
        if (cod == null){
            return null;
        }

        for (Perfil tipo: Perfil.values()) {
            if (cod.equals(tipo.getCod())){
                return tipo;
            }
        }
        throw new IllegalArgumentException("Código Inválido: "+cod);
    }
}
