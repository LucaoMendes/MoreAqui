package com.projFg.moreaqui.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/*
 * Grupo 11
 * Lucas Vinicius Silva Mendes
 * João Gabriel
 * Lucas Eduardo M de Amorim
 * Marcos Vinicius Silva
 * Igor Bezerra
 */

import com.projFg.moreaqui.config;
import com.projFg.moreaqui.model.Estate;
import com.projFg.moreaqui.server.CMD;
import com.projFg.moreaqui.server.DaoImpl;
import com.projFg.moreaqui.server.Invoker;

import java.util.ArrayList;
import java.util.List;

public class ImovelDAO {
    private SQLiteDatabase db;
    private ImovelData dataDb;
    private String tabelaImoveis = "imoveis";

    //CRUD OPERATIONS - Int enviada a classe CMD
    public final int CREATE_OP = config.CREATE_OP;
    public final int READ_OP = config.READ_OP;
    public final int UPDATE_OP = config.UPDATE_OP;
    public final int DELETE_OP = config.DELETE_OP;


    public ImovelDAO (Context ctx){
        dataDb = new ImovelData(ctx,config.NOME_DB,config.VERSAO_DB);
    }

    public long inserirImovel(Estate imovel){
        ContentValues im = new ContentValues();
        im.put("PHONE",imovel.PHONE);
        im.put("TYPE",imovel.TYPE);
        im.put("SIZE",imovel.SIZE);
        im.put("STATUS",imovel.STATUS.toString());

        //Log.v("DEBUG DB INSERT",imovel.emConstrucaoImovel.toString());
        db = dataDb.getWritableDatabase();
        long id = db.insert(tabelaImoveis,null,im);
        db.close();
        return id;
    }
    public List<Estate> buscarImoveis(){
        List<Estate> list = new ArrayList<>();
        db = dataDb.getWritableDatabase();
        String[] columns = config.COLUMNS;

        Cursor cursor = db.query(tabelaImoveis,columns,null,null,null,null,"_id");

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Estate im = new Estate(

                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4)
            );
            im.setId(cursor.getLong(0));

            //Log.v(config.DEBUG_DB_BUSCA",im.emConstrucaoImovel.toString());
            list.add(im);
            Log.v(config.DEBUG_DB_BUSCA,config.DEBUG_SEP);
            Log.v(config.DEBUG_DB_BUSCA,"ID: "+ im.getId());
            Log.v(config.DEBUG_DB_BUSCA, "Telefone: "+ im.PHONE);
            Log.v(config.DEBUG_DB_BUSCA,"Imovel: " + im.TYPE);
            Log.v(config.DEBUG_DB_BUSCA,"Tamanho: "+ im.SIZE);
            Log.v(config.DEBUG_DB_BUSCA,"Em Construção: "+ im.STATUS);
            Log.v(config.DEBUG_DB_BUSCA,config.DEBUG_SEP);

            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return list;
    }

    public void removerImovel(long id){
        String where = config.COLUMNS[0] +" = "  + id;
        db = dataDb.getReadableDatabase();
        db.delete(tabelaImoveis,where,null);
        db.close();
    }

    public void gravarImoveis(){
        List<Estate> list = new ArrayList<>();
        list = this.buscarImoveis();
        DaoImpl daoImpl = new DaoImpl();
        Invoker invok = new Invoker(config.HOST,config.PORT);
        for (Estate im:list) {
            long id = im.getId();
            Log.v(config.DEBUG_SERVER,"ID:"+id);
            CMD cmd = new CMD(im,CREATE_OP,id);
            invok.invoke(daoImpl,cmd);
            removerImovel(id);
        }

    }


}
