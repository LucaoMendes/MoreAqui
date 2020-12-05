package com.projFg.moreaqui.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.projFg.moreaqui.config;


/**
 * Grupo 11
 * GitHub:https://github.com/LucaoMendes/MoreAqui
 * Trello:https://trello.com/b/XstseyJW/moreaqui
 * Lucas Vinicius Silva Mendes - Mat. 201806442
 * João Gabriel da Silva - Mat. 201805070
 * Lucas Eduardo M de Amorim - Mat. 201708075
 * Marcos Vinicius Silva - Mat. 201900939
 * Igor Bezerra Borges de Lima - Mat. 202005035
 */

public class ImovelData extends SQLiteOpenHelper {
    private String nomeTabelaImoveis = config.TABELA_IMOVEIS;
    private String scriptDelete = "DROP TABLE IF EXISTS "+ nomeTabelaImoveis;
    public ImovelData(Context ctx, String nomeBd,
                      int versaoBanco) {

        super(ctx, nomeBd, null, versaoBanco);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ nomeTabelaImoveis +
                " (_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "PHONE TEXT NOT NULL," +
                "TYPE TEXT NOT NULL," +
                "SIZE TEXT NOT NULL," +
                "STATUS TEXT NOT NULL," +
                "LATITUDE DOUBLE NOT NULL,"+
                "LONGITUDE DOUBLE NOT NULL"+

                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(scriptDelete);
        onCreate(db);
    }
}
