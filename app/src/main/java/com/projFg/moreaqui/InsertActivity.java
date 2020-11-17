package com.projFg.moreaqui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.switchmaterial.SwitchMaterial;

//Classe Estate com as informações do imovel
import com.projFg.moreaqui.model.ImovelModel;

public class InsertActivity extends AppCompatActivity {
    EditText txtTelefone;
    RadioGroup tiposDeImovel,tamanhosDeImovel;
    Button btnSalvar;
    RadioButton tipoMarcado,tamanhoMarcado;
    SwitchMaterial emConstrucao;
    ImovelModel imovel;
    FloatingActionButton fabVoltar;

    @Override
    public void onBackPressed(){
        startActivity(new Intent(this, MoreAqui2Activity.class));
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
        btnSalvar = (Button) findViewById(R.id.btn_salvar);
        tamanhosDeImovel = (RadioGroup) findViewById(R.id.opt_tamanhos);
        emConstrucao = (SwitchMaterial) findViewById(R.id.sw_construcao);
        fabVoltar = (FloatingActionButton) findViewById(R.id.fab_voltar);


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

        fabVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InsertActivity.this, MoreAqui2Activity.class));
                finishAffinity();
            }
        });
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    int telefone = Integer.parseInt(txtTelefone.getText().toString());
                    if (tipoMarcado == null || tamanhoMarcado == null){
                        Snackbar.make(v,R.string.txt_erroFaltaInformacoes,Snackbar.LENGTH_SHORT).show();
                    }else {
                        imovel = new ImovelModel(
                                telefone,
                                tipoMarcado.getText().toString(),
                                tamanhoMarcado.getText().toString(),
                                emConstrucao.isChecked());
                        Log.v("New",imovel.toString());

                        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

                        Snackbar.make(v,R.string.txt_infoInserida,Snackbar.LENGTH_SHORT).show();

                    }




                }catch(Exception e){
                    Log.v("DEBUG","Exception -> "+e.toString()); //Debug de codigo via logCat
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