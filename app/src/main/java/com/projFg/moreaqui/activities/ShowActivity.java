package com.projFg.moreaqui.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.projFg.moreaqui.DAO.ImovelDAO;
import com.projFg.moreaqui.R;
import com.projFg.moreaqui.activities.adapter.ListAdapter;
import com.projFg.moreaqui.config;
import com.projFg.moreaqui.fragments.MenuFragment;
import com.projFg.moreaqui.fragments.MenuImovelFragment;
import com.projFg.moreaqui.model.LocationEstate;
import java.util.ArrayList;
import java.util.List;


/*
 * Grupo 11
 * Lucas Vinicius Silva Mendes - Mat. 201806442
 * João Gabriel da Silva - Mat. 201805070
 * Lucas Eduardo M de Amorim - Mat. 201708075
 * Marcos Vinicius Silva - Mat. 201900939
 * Igor Bezerra Borges de Lima - Mat. 202005035
 */


public class ShowActivity extends AppCompatActivity {
    /*
    * Inicio Fase 2
    */


    //Inicialização de variaveis
    ListView list_imoveis;
    List<LocationEstate> ImoveisRecebidos;
    ListAdapter adapter;
    FloatingActionButton fabAdicionar;
    BottomAppBar menu,menuImovel;

    ImovelDAO imovelDAO = new ImovelDAO(this);

    //Botão de retorno voltar a activity anterior
    @Override
    public void onBackPressed(){
        startActivity(new Intent(this, MoreAqui5Activity.class));
        finishAffinity();
    }

    @Override
    protected void onCreate(Bundle savedInstancLocationEstate) {
        super.onCreate(savedInstancLocationEstate);
        setContentView(R.layout.activity_show);

        fabAdicionar = findViewById(R.id.fabAdicionar);
        list_imoveis = findViewById(R.id.list_imoveis);
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
                final MenuImovelFragment bottomSheetAppBarFragment = new MenuImovelFragment();
                Bundle bundle = new Bundle();

                ImovelDAO imDao = new ImovelDAO(view.getContext());
                List<LocationEstate> imoveis = imDao.buscarImoveis();
                long idImovel = imoveis.get(position).getId();
                Log.v(config.DEBUG_SHOWACT,config.DEBUG_SEP);
                Log.v(config.DEBUG_SHOWACT,"Id do Imovel é:"+idImovel);
                Log.v(config.DEBUG_SHOWACT,config.DEBUG_SEP);
                bundle.putLong("id",idImovel);
                bundle.putDouble("latitude", ImoveisRecebidos.get(position).LATITUDE);
                bundle.putDouble("longitude", ImoveisRecebidos.get(position).LONGITUDE);

                bottomSheetAppBarFragment.setArguments(bundle);
                bottomSheetAppBarFragment.show(getSupportFragmentManager(), bottomSheetAppBarFragment.getTag());

            }
        });

         fabAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), InsertActivity.class));
                finishAffinity();
                overridePendingTransition(R.anim.res_anim_fadein, R.anim.res_anim_fadeout);
            }
        });
        //Verificação de booleana externa, se positivo alguma informação foi validada anteriormente
        Intent i = getIntent();
        View v = findViewById(android.R.id.content);
        if(i.getBooleanExtra("insert",false)){
            Snackbar.make(v,R.string.txt_infoInserida,Snackbar.LENGTH_SHORT).show();
        }else if(i.getBooleanExtra("remove",false)){
            Snackbar msgDeletar = Snackbar.make(v,R.string.txt_deletarMsg,Snackbar.LENGTH_LONG);
            msgDeletar.setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE);
            msgDeletar.show();
        }

        ImoveisRecebidos = new ArrayList<>();

        try{
            ImoveisRecebidos = imovelDAO.buscarImoveis();

//            adapter = new ArrayAdapter<>(ShowActivity.this,android.R.layout.simple_list_item_1, ImoveisRecebidos);
            adapter = new ListAdapter(ImoveisRecebidos,this);
            list_imoveis.setAdapter(adapter);
            Log.v(config.DEBUG_SHOWACT,"ADAPTER ADICIONADO");
        }catch(Exception e){
            Log.v(config.DEBUG_EXCEPTION ,e.toString());
        }

    }
}