package com.projFg.moreaqui.model;

public final class ImovelModel {
    private final int telefoneImovel;
    private final String tipoImovel;
    private final String tamanhoImovel;
    private final Boolean emConstrucaoImovel;

    public ImovelModel(int telefoneImovel,String tipoImovel,String tamanhoImovel,Boolean emConstrucaoImovel){
        this.telefoneImovel = telefoneImovel;
        this.tipoImovel = tipoImovel;
        this.tamanhoImovel = tamanhoImovel;
        this.emConstrucaoImovel = emConstrucaoImovel;
    }

}
