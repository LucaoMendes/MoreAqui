package com.projFg.moreaqui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MoreAqui2Activity extends AppCompatActivity {
    Button btnNovo;
    Button btnVisualizar;
    Button btnMapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moreaqui2);

        /*
        * Instanciando variaveis
        */
        btnNovo = (Button) findViewById(R.id.btn_novo);
        btnVisualizar = (Button) findViewById(R.id.btn_visualizar);
        btnMapa = (Button) findViewById(R.id.btn_mapa);




        //onClick Listeners
        btnNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MoreAqui2Activity.this,
                        InsertActivity.class);
                startActivity(i);
                finishAffinity();
            }
        });

        btnVisualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MoreAqui2Activity.this,
                        ShowActivity.class);
                startActivity(i);
                finishAffinity();
            }
        });

    }
}