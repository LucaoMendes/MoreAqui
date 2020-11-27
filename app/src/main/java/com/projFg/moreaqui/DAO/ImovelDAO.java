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

import com.projFg.moreaqui.model.ImovelModel;

import java.util.ArrayList;
import java.util.List;

public class ImovelDAO {
    private SQLiteDatabase db;
    private ImovelData dataDb;
    private String tabelaImoveis = "imoveis";

    public ImovelDAO (Context ctx){
        dataDb = new ImovelData(ctx,"moreaqui",1);
    }

    public long inserirImovel(ImovelModel imovel){
        ContentValues im = new ContentValues();
        im.put("PHONE",imovel.telefoneImovel);
        im.put("TYPE",imovel.tipoImovel);
        im.put("SIZE",imovel.tamanhoImovel);
        im.put("STATUS",imovel.emConstrucaoImovel.toString());

        //Log.v("DEBUG DB INSERT",imovel.emConstrucaoImovel.toString());
        db = dataDb.getWritableDatabase();
        long id = db.insert(tabelaImoveis,null,im);
        db.close();
        return id;
    }
    public List<ImovelModel> buscarImoveis(){
        List<ImovelModel> list = new ArrayList<ImovelModel>();
        db = dataDb.getWritableDatabase();
        String[] columns = new String[]{
                "_ID", "PHONE", "TYPE", "SIZE", "STATUS"};

        Cursor cursor = db.query(tabelaImoveis,columns,null,null,null,null,"_id");

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            ImovelModel im = new ImovelModel(
                    cursor.getLong(0),
                    cursor.getInt(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    Boolean.parseBoolean(cursor.getString(4))
            );

            //Log.v("DEBUG DB Busca",im.emConstrucaoImovel.toString());
            list.add(im);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return list;
    }


}
