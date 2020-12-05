package com.projFg.moreaqui.model;

import java.io.Serializable;

/**
 * Grupo 11
 * GitHub:https://github.com/LucaoMendes/MoreAqui
 * Trello:https://trello.com/b/XstseyJW/moreaqui
 * Lucas Vinicius Silva Mendes - Mat. 201806442
 * João Gabriel da Silva - Mat. 201805070
 * Lucas Eduardo M de Amorim - Mat. 201708075
 * Marcos Vinicius Silva - Mat. 201900939
 * Igor Bezerra Borges de Lima - Mat. 202005035
 */
public class Estate implements Serializable {
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
    private long id;
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
    public  String toString() {
        String ans = "Imovel: "+ this.TYPE + " Tamanho: " + this.SIZE
                + "\n Contato: " + this.PHONE + "(" + (Boolean.parseBoolean(this.STATUS) ?"Em Construção":"Pronto") + ")";
        return ans;
    }

    public long getId(){
        return this.id;
    }
    public void setId(long id){
        this.id = id;
    }


}
