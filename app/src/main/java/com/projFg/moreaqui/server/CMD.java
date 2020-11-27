package com.projFg.moreaqui.server;

import android.util.Log;

import com.projFg.moreaqui.model.Estate;

import java.io.Serializable;

public class CMD implements Command {
    public final Estate IM;
    public final int OP;
    public final long ID;

    public CMD(Estate im, int op, long id){
        this.IM = im;
        this.OP = op;
        this.ID = id;
    }

    @Override
    public void execute(DaoImpl d) {
        if(OP == 1){
            Log.v("SERVER DEBUG:","Iniciando a inserção de dados ao servidor remoto");
            Log.v("SERVER DEBUG:","ID: "+this.ID+" Informações: ("+ IM.toString() +")");
            d.add(ID,IM);
        }
    }
}
