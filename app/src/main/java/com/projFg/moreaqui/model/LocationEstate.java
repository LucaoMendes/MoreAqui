package com.projFg.moreaqui.model;

/*
 * Grupo 11
 * Lucas Vinicius Silva Mendes - Mat. 201806442
 * João Gabriel da Silva - Mat. 201805070
 * Lucas Eduardo M de Amorim - Mat. 201708075
 * Marcos Vinicius Silva - Mat. 201900939
 * Igor Bezerra Borges de Lima - Mat. 202005035
 */

public final class LocationEstate extends Estate {
    private static final long serialVersionUID = 1L;

    /** The size of the estate: small, medium, large. */
    public final Double LATITUDE;

    /** True if the estate is under construction. */
    public final Double LONGITUDE;

    public LocationEstate(String type, String size, String phone, String inConstruction, double latitude, double longitude) {
        super(type, size, phone, inConstruction);
        this.LATITUDE = latitude;
        this.LONGITUDE = longitude;
    }

    @Override
    public final String toString() {
        String ans = "Imovel: "+ this.TYPE + " Tamanho: " + this.SIZE
                + "\n Contato: " + this.PHONE + "(" + (Boolean.parseBoolean(this.STATUS) ?"Em Construção":"Pronto") + ")"
                + "\n Latitude: "+ this.LATITUDE + "\n Longitude: "+ this.LONGITUDE;
        return ans;
    }
}
