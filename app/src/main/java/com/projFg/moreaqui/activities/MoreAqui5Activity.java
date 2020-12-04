package com.projFg.moreaqui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.projFg.moreaqui.DAO.ImovelDAO;
import com.projFg.moreaqui.R;


/*
 * Grupo 11
 * Lucas Vinicius Silva Mendes - Mat. 201806442
 * João Gabriel da Silva - Mat. 201805070
 * Lucas Eduardo M de Amorim - Mat. 201708075
 * Marcos Vinicius Silva - Mat. 201900939
 * Igor Bezerra Borges de Lima - Mat. 202005035
 */


public class MoreAqui5Activity extends AppCompatActivity {
    Button btnNovo,btnVisualizar,btnMapa,btnGravar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moreaqui5);


        /*
        * Instanciando variaveis
        */
        btnNovo = findViewById(R.id.btn_novo);
        btnVisualizar = findViewById(R.id.btn_visualizar);
        btnMapa = findViewById(R.id.btn_mapa);
        btnGravar = findViewById(R.id.btn_gravar);

        //onClick Listeners

        //Botão Novo
        btnNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MoreAqui5Activity.this,
                        InsertActivity.class);
                startActivity(i);
                finishAffinity();

            }
        });

        //Botão Visualizar
        btnVisualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MoreAqui5Activity.this,
                        ShowActivity.class);
                startActivity(i);
                finishAffinity();

            }
        });

        //Botão mapa
        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MoreAqui5Activity.this,
                        ShowAddressesActivity.class);
                startActivity(i);
                finishAffinity();
            }
        });

        //Botão Gravar
        btnGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImovelDAO imData = new ImovelDAO(v.getContext());
                imData.gravarImoveis();

                Snackbar msgGravar = Snackbar.make(v,R.string.txt_gravarInfo,Snackbar.LENGTH_LONG);
                msgGravar.setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE);
                msgGravar.show();
            }
        });



    }
}