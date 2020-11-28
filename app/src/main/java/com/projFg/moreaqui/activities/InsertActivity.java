package com.projFg.moreaqui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.switchmaterial.SwitchMaterial;

//Classe Estate com as informações do imovel
import com.projFg.moreaqui.DAO.ImovelDAO;
import com.projFg.moreaqui.R;
import com.projFg.moreaqui.config;
import com.projFg.moreaqui.fragments.MenuFragment;
import com.projFg.moreaqui.model.Estate;


/*
* Grupo 11
* Lucas Vinicius Silva Mendes
* João Gabriel
* Lucas Eduardo M de Amorim
* Marcos Vinicius Silva
* Igor Bezerra
*/


public class InsertActivity extends AppCompatActivity {
    EditText txtTelefone;
    RadioGroup tiposDeImovel,tamanhosDeImovel;
    FloatingActionButton fabInserir;
    RadioButton tipoMarcado,tamanhoMarcado;
    SwitchMaterial emConstrucao;
    Estate imovel;
    ImovelDAO imovelDAO;

    @Override
    public void onBackPressed(){
        startActivity(new Intent(this, MoreAqui3Activity.class));
        finishAffinity();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);


        /*
        * Instanciando variaveis
        */
        txtTelefone = (EditText) findViewById(R.id.txt_telefone);
        tiposDeImovel = (RadioGroup) findViewById(R.id.opt_tipos);
        tamanhosDeImovel = (RadioGroup) findViewById(R.id.opt_tamanhos);
        emConstrucao = (SwitchMaterial) findViewById(R.id.sw_construcao);
        fabInserir = (FloatingActionButton) findViewById(R.id.fabInserir);
        imovelDAO = new ImovelDAO(this);

        //Menu parte debaixo

        final BottomAppBar menu = findViewById(R.id.menuBottomShow);
        setSupportActionBar(menu);
        menu.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final MenuFragment bottomSheetAppBarFragment = new MenuFragment();
                bottomSheetAppBarFragment.show(getSupportFragmentManager(), bottomSheetAppBarFragment.getTag());

            }
        });


        //onChecked Listeners

        tiposDeImovel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                tipoMarcado = (RadioButton)  findViewById(checkedId);
            }
        });
        tamanhosDeImovel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
               tamanhoMarcado = (RadioButton)  findViewById(checkedId);
            }
        });


        //onClick Listeners
        fabInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    int telefone = Integer.parseInt(txtTelefone.getText().toString());
                    if (tipoMarcado == null || tamanhoMarcado == null){
                        Snackbar.make(v,R.string.txt_erroFaltaInformacoes,Snackbar.LENGTH_SHORT).show();
                    }else {
                        imovel = new Estate(
                                Integer.toString(telefone),
                                tipoMarcado.getText().toString(),
                                tamanhoMarcado.getText().toString(),
                                Boolean.toString(emConstrucao.isChecked()));

                        //Log.v("New",imovel.toString());

                        Long id = imovelDAO.inserirImovel(imovel);
                        if(id != null){
                            //Log.v("DEBUG Inserir Imovel","ID: "+id);
                        }


                        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);


                        Intent i = new Intent(InsertActivity.this, ShowActivity.class);
                        i.putExtra("insert",true);
                        startActivity(i);
                        finishAffinity();


                    }




                }catch(Exception e){
                    Log.v(config.DEBUG_INSERTACT,"Exception -> "+e.toString()); //Debug de codigo via logCat
                    Snackbar.make(v,R.string.txt_erroInfoInvalida,Snackbar.LENGTH_SHORT).show();

                }
            }
        });


        //onFocusChange Listeners
        txtTelefone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    if(txtTelefone.getText().toString().equals(getString(R.string.txt_telefone))){
                        txtTelefone.setText("");
                    }
                }else{
                    if(txtTelefone.getText().toString().equals("")){
                        txtTelefone.setText(getString(R.string.txt_telefone));
                    }
                }
            }
        });


    }
}