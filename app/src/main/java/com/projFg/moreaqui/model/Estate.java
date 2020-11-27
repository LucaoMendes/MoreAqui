package com.projFg.moreaqui.model;

/*
 * Grupo 11
 * Lucas Vinicius Silva Mendes
 * João Gabriel
 * Lucas Eduardo M de Amorim
 * Marcos Vinicius Silva
 * Igor Bezerra
 */

public final class Estate {
    public final int PHONE;
    public final String TYPE;
    public final String SIZE;
    public final Boolean STATUS;

    public Estate(int phone, String type, String size, Boolean inConstruction){
        this.PHONE = phone;
        this.TYPE = type;
        this.SIZE = size;
        this.STATUS = inConstruction;
    }

    @Override
    public final String toString() {
        String ans = "Imovel: "+ this.TYPE + " Tamanho: " + this.SIZE
                + "\n Contato: " + this.PHONE + "(" + (this.STATUS ?"Em Construção":"Pronto") + ")";
        return ans;
    }


}
