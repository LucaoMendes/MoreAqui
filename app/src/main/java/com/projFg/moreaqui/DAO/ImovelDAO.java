package com.projFg.moreaqui.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.projFg.moreaqui.model.ImovelModel;

import java.util.ArrayList;

public class ImovelDAO {
    private SQLiteDatabase db;
    private ImovelData dataDb;
    private String tabelaImoveis = "imoveis";

    public ImovelDAO (Context ctx){
        dataDb = new ImovelData(ctx,"moreaqui",1);
    }

    public long inserirImovel(ImovelModel imovel){
        ContentValues im = new ContentValues();
        im.put("TYPE",imovel.tipoImovel);
        im.put("SIZE",imovel.tamanhoImovel);
        im.put("STATUS",imovel.emConstrucaoImovel);
        im.put("PHONE",imovel.telefoneImovel);
        db = dataDb.getWritableDatabase();
        long id = db.insert(tabelaImoveis,null,im);
        db.close();
        return id;
    }
    public ArrayList buscarImoveis(){
        ArrayList<ImovelModel> list = new ArrayList<ImovelModel>();
        String query = "SELECT * FROM "+ tabelaImoveis;
        db = dataDb.getReadableDatabase();
        try{
            Cursor cursor = db.rawQuery(query,null);
            try{
                if(cursor.moveToFirst()){
                    do{
                        ImovelModel im = new ImovelModel(
                                cursor.getInt(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                Boolean.parseBoolean(cursor.getString(3)));
                        list.add(im);
                    }while(cursor.moveToNext());
                }
            }finally {
                try{cursor.close();}catch(Exception e){
                    Log.v("DEBUG",e.toString());
                }
            }
        }finally {
            try{db.close();}catch(Exception e){
                Log.v("DEBUG",e.toString());
            }

        }
        return list;
    }


}
