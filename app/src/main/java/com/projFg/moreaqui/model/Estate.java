package com.projFg.moreaqui.model;

/*
 * Grupo 11
 * Lucas Vinicius Silva Mendes
 * João Gabriel
 * Lucas Eduardo M de Amorim
 * Marcos Vinicius Silva
 * Igor Bezerra
 */

import java.io.Serializable;

public final class Estate implements Serializable {
    /** The serial version of this class. */
    private static final long serialVersionUID = 1734003038366261208L;

    /** The type of the state is not known. */
    public static final int UNKNOWN_TYPE = 0;

    /** The estate is a house, i.e., is not in a building with other aps. */
    public static final int HOUSE = 1;

    /** The estate is an apartment. */
    public static final int APARTMENT = 2;

    /** The estate is a commercial room. */
    public static final int SHOP = 3;

    /** The size of the state is not known. */
    public static final int UNKNOWN_SIZE = 0;

    /** The estate is small, i.e., it has one or two rooms. */
    public static final int SMALL = 1;

    /** The estate is medium sized, having three to four rooms. */
    public static final int MEDIUM = 2;

    /** The estate is large, i.e., it has more than four rooms. */
    public static final int LARGE = 3;
    public final String PHONE;
    public final String TYPE;
    public final String SIZE;
    public final String STATUS;

    public Estate(String phone, String type, String size, String inConstruction){
        this.PHONE = phone;
        this.TYPE = type;
        this.SIZE = size;
        this.STATUS = inConstruction;
    }

    @Override
    public final String toString() {
        String ans = "Imovel: "+ this.TYPE + " Tamanho: " + this.SIZE
                + "\n Contato: " + this.PHONE + "(" + (Boolean.parseBoolean(this.STATUS) ?"Em Construção":"Pronto") + ")";
        return ans;
    }


}
