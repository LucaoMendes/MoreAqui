package com.projFg.moreaqui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.snackbar.Snackbar;
import com.projFg.moreaqui.DAO.ImovelDAO;
import com.projFg.moreaqui.model.ImovelModel;

import java.util.ArrayList;
import java.util.List;

public class ShowActivity extends AppCompatActivity {
    ListView list_imoveis;
    List<ImovelModel> lista;
    ArrayAdapter<String> adapter;

    ImovelDAO imovelDAO = new ImovelDAO(this);
    @Override
    public void onBackPressed(){
        startActivity(new Intent(this, MoreAqui2Activity.class));
        finishAffinity();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        Intent i = getIntent();
        View v = findViewById(android.R.id.content);
        if(i.getBooleanExtra("insert",false)){
            Snackbar.make(v,R.string.txt_infoInserida,Snackbar.LENGTH_SHORT).show();
        }

        lista = new ArrayList<>();
        list_imoveis = findViewById(R.id.list_imoveis);
        try{
            lista = imovelDAO.buscarImoveis();
            List<String> ImoveisRecebidos = new ArrayList<>();

            //Log.v("DEBUG BD",lista.get(1).emConstrucaoImovel.toString());

            for (ImovelModel im:lista) {
                String status = im.emConstrucaoImovel?getString(R.string.txt_construcao):getString(R.string.txt_pronto);
                ImoveisRecebidos.add(
                        getString(R.string.txt_imovel)+":"+im.tipoImovel+","+
                        getString(R.string.txt_tamanho)+" "+im.tamanhoImovel+ ", "+
                        getString(R.string.txt_telefone)+": "+im.telefoneImovel + ", "+
                        getString(R.string.txt_status)+": "+status);
            }
            adapter = new ArrayAdapter<>(ShowActivity.this,android.R.layout.simple_list_item_1, ImoveisRecebidos);
            list_imoveis.setAdapter(adapter);
        }catch(Exception e){
            Log.v("DEBUG",e.toString());
        }

    }
}