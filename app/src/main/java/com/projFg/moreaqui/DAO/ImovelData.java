package com.projFg.moreaqui.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ImovelData extends SQLiteOpenHelper {
    private String nomeTabelaImoveis = "imoveis";
    private String scriptDelete = "DROP TABLE IF EXISTS "+ nomeTabelaImoveis;
    public ImovelData(Context ctx, String nomeBd,
                      int versaoBanco) {

        super(ctx, nomeBd, null, versaoBanco);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ nomeTabelaImoveis +
                " (_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "TYPE TEXT NOT NULL," +
                "SIZE TEXT NOT NULL," +
                "STATUS TEXT," +
                "PHONE TEXT NOT NULL" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(scriptDelete);
        onCreate(db);
    }
}