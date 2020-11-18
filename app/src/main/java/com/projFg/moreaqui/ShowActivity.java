package com.projFg.moreaqui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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


        lista = new ArrayList<>();
        list_imoveis = findViewById(R.id.list_imoveis);
        try{
            lista = imovelDAO.buscarImoveis();
            List<String> ImoveisRecebidos = new ArrayList<>();

            //Log.v("DEBUG BD",lista.get(1).emConstrucaoImovel.toString());

            for (ImovelModel im:lista) {
                String status = im.emConstrucaoImovel?"Em construção":"Pronto";
                ImoveisRecebidos.add("Imovel:"+im.tipoImovel+", Tamanho:"+im.tamanhoImovel+ ", Contato:"+im.telefoneImovel +", Status:"+ status);
            }
            adapter = new ArrayAdapter<>(ShowActivity.this,android.R.layout.simple_list_item_1, ImoveisRecebidos);
            list_imoveis.setAdapter(adapter);
        }catch(Exception e){
            Log.v("DEBUG",e.toString());
        }

    }
}