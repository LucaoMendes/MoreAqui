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
 * Lucas Vinicius Silva Mendes
 * João Gabriel
 * Lucas Eduardo M de Amorim
 * Marcos Vinicius Silva
 * Igor Bezerra
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
        btnNovo = (Button) findViewById(R.id.btn_novo);
        btnVisualizar = (Button) findViewById(R.id.btn_visualizar);
        btnMapa = (Button) findViewById(R.id.btn_mapa);
        btnGravar = (Button) findViewById(R.id.btn_gravar);

        //onClick Listeners
        btnNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MoreAqui5Activity.this,
                        InsertActivity.class);
                startActivity(i);
                finishAffinity();

            }
        });

        btnVisualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MoreAqui5Activity.this,
                        ShowActivity.class);
                startActivity(i);
                finishAffinity();

            }
        });

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

        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MoreAqui5Activity.this,
                        ShowAddressesActivity.class);
                startActivity(i);
                finishAffinity();
            }
        });


    }
}