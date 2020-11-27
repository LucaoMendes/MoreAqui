package com.projFg.moreaqui.model;

/*
 * Grupo 11
 * Lucas Vinicius Silva Mendes
 * João Gabriel
 * Lucas Eduardo M de Amorim
 * Marcos Vinicius Silva
 * Igor Bezerra
 */

public final class ImovelModel {
    public final Long idImovel;
    public final int telefoneImovel;
    public final String tipoImovel;
    public final String tamanhoImovel;
    public final Boolean emConstrucaoImovel;

    public ImovelModel(Long idImovel, int telefoneImovel, String tipoImovel, String tamanhoImovel, Boolean emConstrucaoImovel){
        this.idImovel = idImovel;
        this.telefoneImovel = telefoneImovel;
        this.tipoImovel = tipoImovel;
        this.tamanhoImovel = tamanhoImovel;
        this.emConstrucaoImovel = emConstrucaoImovel;
    }

    @Override
    public final String toString() {
        String ans = "Imovel: " + this.tipoImovel + " Tamanho: " + this.tamanhoImovel
                + "\n Contato: " + this.telefoneImovel + "(" + (this.emConstrucaoImovel?"Em Construção":"Pronto") + ")";
        return ans;
    }


}
