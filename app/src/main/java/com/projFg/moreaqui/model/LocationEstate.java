package com.projFg.moreaqui.model;

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
