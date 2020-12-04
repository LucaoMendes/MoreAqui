package com.projFg.moreaqui;

/*
 * Grupo 11
 * Lucas Vinicius Silva Mendes - Mat. 201806442
 * João Gabriel da Silva - Mat. 201805070
 * Lucas Eduardo M de Amorim - Mat. 201708075
 * Marcos Vinicius Silva - Mat. 201900939
 * Igor Bezerra Borges de Lima - Mat. 202005035
 */

public interface config {
    public final String DEBUG = "DEBUG";
    public final String DEBUG_INSERTACT = "DEBUG Insert Activity";
    public final String DEBUG_SHOWACT = "DEBUG Show Activity";
    public final String DEBUG_MAINACT = "DEBUG More aqui Activity";
    public final String DEBUG_DB = "DEBUG DB";
    public final String DEBUG_SERVER = "DEBUG SERVER";
    public final String DEBUG_DB_BUSCA = "DEBUG DB Busca";
    public final String DEBUG_MENU = "DEBUG Menu principal";
    public final String DEBUG_MENU_IMOVEL = "DEBUG Menu Imovel";
    public final String DEBUG_SEP = "===============================";
    public final String DEBUG_EXCEPTION = "DEBUG EXCEPTION";
    public final String DEBUG_GPS = "DEBUG GPS";

    //OPERAÇÕES NO CRUD

    public final int CREATE_OP = 0;
    public final int READ_OP = 1;
    public final int UPDATE_OP = 2;
    public final int DELETE_OP = 3;

    //CONFIGURAÇÕES DB

    public final String TABELA_IMOVEIS = "imoveis";
    public final String NOME_DB = "moreAqui";
    public final int VERSAO_DB = 2;
    public final String[] COLUMNS = new String[]{
            "_ID", "PHONE", "TYPE", "SIZE", "STATUS", "LATITUDE", "LONGITUDE"};

    //CONFIGURAÇÕES SERVIDOR REMOTO
    public final String HOST = "192.168.43.115";
    public final int PORT = 4444;
}
