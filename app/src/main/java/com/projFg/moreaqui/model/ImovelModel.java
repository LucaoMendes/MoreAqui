package com.projFg.moreaqui.model;

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


}
