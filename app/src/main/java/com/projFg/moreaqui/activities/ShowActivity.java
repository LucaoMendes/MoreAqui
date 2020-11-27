package com.projFg.moreaqui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.projFg.moreaqui.DAO.ImovelDAO;
import com.projFg.moreaqui.R;
import com.projFg.moreaqui.fragments.MenuFragment;
import com.projFg.moreaqui.fragments.MenuImovelFragment;
import com.projFg.moreaqui.model.Estate;

import java.util.ArrayList;
import java.util.List;


/*
 * Grupo 11
 * Lucas Vinicius Silva Mendes
 * João Gabriel
 * Lucas Eduardo M de Amorim
 * Marcos Vinicius Silva
 * Igor Bezerra
 */


public class ShowActivity extends AppCompatActivity {
    /*
    * Inicio Fase 2
    */


    //Inicialização de variaveis
    ListView list_imoveis;
    List<Estate> lista;
    ArrayAdapter<String> adapter;
    FloatingActionButton fabAdicionar;
    BottomAppBar menu,menuImovel;

    ImovelDAO imovelDAO = new ImovelDAO(this);

    //Botão de retorno voltar a activity anterior
    @Override
    public void onBackPressed(){
        startActivity(new Intent(this, MoreAqui3Activity.class));
        finishAffinity();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        fabAdicionar = (FloatingActionButton) findViewById(R.id.fabAdicionar);
        list_imoveis = (ListView)findViewById(R.id.list_imoveis);
        menu = findViewById(R.id.menuBottomShow);

        setSupportActionBar(menu);

        //Menu parte debaixo

        menu.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final MenuFragment bottomSheetAppBarFragment = new MenuFragment();
                bottomSheetAppBarFragment.show(getSupportFragmentManager(), bottomSheetAppBarFragment.getTag());

            }
        });


        //OnClickListeners
        list_imoveis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = list_imoveis.getItemAtPosition(position);
                String str = (String)o;
                //Snackbar.make(view,str,Snackbar.LENGTH_SHORT).show();
                final MenuImovelFragment bottomSheetAppBarFragment = new MenuImovelFragment();
                bottomSheetAppBarFragment.show(getSupportFragmentManager(), bottomSheetAppBarFragment.getTag());

            }
        });

         fabAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), InsertActivity.class));
                finishAffinity();
            }
        });
        //Verificação de booleana externa, se positivo alguma informação foi validada anteriormente
        Intent i = getIntent();
        View v = findViewById(android.R.id.content);
        if(i.getBooleanExtra("insert",false)){
            Snackbar.make(v,R.string.txt_infoInserida,Snackbar.LENGTH_SHORT).show();
        }

        lista = new ArrayList<>();

        try{
            lista = imovelDAO.buscarImoveis();
            List<String> ImoveisRecebidos = new ArrayList<>();

            //Log.v("DEBUG BD",lista.get(1).emConstrucaoImovel.toString());

            //Criação da lista
            for (Estate im:lista) {
                String status =  Boolean.parseBoolean(im.STATUS) ?getString(R.string.txt_construcao):getString(R.string.txt_pronto);
                ImoveisRecebidos.add(im.toString());
            }
            adapter = new ArrayAdapter<>(ShowActivity.this,android.R.layout.simple_list_item_1, ImoveisRecebidos);
            list_imoveis.setAdapter(adapter);
        }catch(Exception e){
            Log.v("DEBUG",e.toString());
        }

    }
}