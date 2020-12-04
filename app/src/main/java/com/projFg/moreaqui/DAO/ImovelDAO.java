package com.projFg.moreaqui.DAO;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

/*
 * Grupo 11
 * Lucas Vinicius Silva Mendes
 * João Gabriel
 * Lucas Eduardo M de Amorim
 * Marcos Vinicius Silva
 * Igor Bezerra
 */

import androidx.core.app.ActivityCompat;

import com.projFg.moreaqui.config;
import com.projFg.moreaqui.model.LocationEstate;
import com.projFg.moreaqui.model.LocationEstate;
import com.projFg.moreaqui.server.CMD;
import com.projFg.moreaqui.server.DaoImpl;
import com.projFg.moreaqui.server.Invoker;

import java.util.ArrayList;
import java.util.List;

import static androidx.core.content.ContextCompat.getSystemService;

public class ImovelDAO {
    private SQLiteDatabase db;
    private ImovelData dataDb;
    private String tabelaImoveis = "imoveis";

    //CRUD OPERATIONS - Int enviada a classe CMD
    public final int CREATE_OP = config.CREATE_OP;

    //Operações ainda não implementadas ( REMOTO )
    public final int READ_OP = config.READ_OP;
    public final int UPDATE_OP = config.UPDATE_OP;
    public final int DELETE_OP = config.DELETE_OP;


    public ImovelDAO(Context ctx) {
        dataDb = new ImovelData(ctx, config.NOME_DB, config.VERSAO_DB);
    }

    //Funções no banco de dados LOCAL

    public long inserirImovel(LocationEstate imovel) {
        ContentValues im = new ContentValues();
        im.put("PHONE", imovel.PHONE);
        im.put("TYPE", imovel.TYPE);
        im.put("SIZE", imovel.SIZE);
        im.put("STATUS", imovel.STATUS);
        im.put("LATITUDE",imovel.LATITUDE);
        im.put("LONGITUDE",imovel.LONGITUDE);

        //Log.v("DEBUG DB INSERT",imovel.emConstrucaoImovel.toString());
        db = dataDb.getWritableDatabase();
        long id = db.insert(tabelaImoveis, null, im);
        db.close();
        return id;
    }

    public List<LocationEstate> buscarImoveis() {
        List<LocationEstate> list = new ArrayList<>();
        db = dataDb.getWritableDatabase();
        String[] columns = config.COLUMNS;

        Cursor cursor = db.query(tabelaImoveis, columns, null, null, null, null, "_id");
        Log.v(config.DEBUG_DB_BUSCA,"CURSOR: "+ cursor.getCount());

        if(cursor.getCount() == 0){
            return null;
        }
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            LocationEstate im = new LocationEstate(

                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getDouble(5),
                    cursor.getDouble(6)
            );
            im.setId(cursor.getLong(0));

            //Log.v(config.DEBUG_DB_BUSCA",im.emConstrucaoImovel.toString());
            list.add(im);
            Log.v(config.DEBUG_DB_BUSCA, config.DEBUG_SEP);
            Log.v(config.DEBUG_DB_BUSCA, "ID: " + im.getId());
            Log.v(config.DEBUG_DB_BUSCA, "Telefone: " + im.PHONE);
            Log.v(config.DEBUG_DB_BUSCA, "Imovel: " + im.TYPE);
            Log.v(config.DEBUG_DB_BUSCA, "Tamanho: " + im.SIZE);
            Log.v(config.DEBUG_DB_BUSCA, "Em Construção: " + im.STATUS);
            Log.v(config.DEBUG_DB_BUSCA, "Latitude: "+ im.LATITUDE);
            Log.v(config.DEBUG_DB_BUSCA, "Longitude: "+ im.LONGITUDE);
            Log.v(config.DEBUG_DB_BUSCA, config.DEBUG_SEP);

            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return list;
    }

    public void removerImovel(long id) {
        String where = config.COLUMNS[0] + " = " + id;
        db = dataDb.getReadableDatabase();
        db.delete(tabelaImoveis, where, null);
        db.close();
    }


    //Funções do banco de dados REMOTO
    public void gravarImoveis() {
        List<LocationEstate> list;
        list = this.buscarImoveis();
        DaoImpl daoImpl = new DaoImpl();
        Invoker invok = new Invoker(config.HOST, config.PORT);
        for (LocationEstate im : list) {
            long id = im.getId();
            Log.v(config.DEBUG_SERVER, "ID:" + id);
            CMD cmd = new CMD(im, CREATE_OP, id);
            invok.invoke(daoImpl, cmd);
            removerImovel(id);
        }

    }





}
