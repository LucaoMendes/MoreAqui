package com.projFg.moreaqui.server;

import android.util.Log;
import com.projFg.moreaqui.config;
import com.projFg.moreaqui.model.Estate;
import java.io.Serializable;

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

public class CMD implements Command {
    public final Estate IM;
    public final int OP;
    public final long ID;

    //CRUD OPERATIONS - Int Recebido na classe CMD
    public final int CREATE_OP = 0;
    public final int READ_OP = 1;
    public final int UPDATE_OP = 2;
    public final int DELETE_OP = 3;

    public CMD(Estate im, int op, long id){
        this.IM = im;
        this.OP = op;
        this.ID = id;
    }

    @Override
    public void execute(DaoImpl d) {
        switch (OP){
            case CREATE_OP:
                create(d);
                break;

            case READ_OP:
                read(d);
                break;

            case UPDATE_OP:
                update(d);
                break;

            case DELETE_OP:
                delete(d);
                break;

            default:

                break;
        }
    }

    private void create(DaoImpl d){
        Log.v(config.DEBUG_SERVER,"Iniciando a inserção de dados ao servidor remoto");
        Log.v(config.DEBUG_SERVER,"ID: "+this.ID+" Informações: ("+ IM.toString() +")");
        d.add(ID,IM);
    }

    private Serializable read(DaoImpl d){
        return d.get(ID);
    }

    private void update(DaoImpl d){
        d.update(ID,IM);
    }

    private void delete(DaoImpl d){
        d.delete(ID);
    }
}
