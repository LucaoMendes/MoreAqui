package com.projFg.moreaqui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;


import com.projFg.moreaqui.model.ImovelModel;

public class InsertActivity extends AppCompatActivity {
    EditText txtTelefone;
    RadioGroup tiposDeImovel;
    RadioGroup tamanhosDeImovel;
    Button btnSalvar;
    RadioButton tipoMarcado;
    RadioButton tamanhoMarcado;
    Switch emConstrucao;
    ImovelModel imovel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        txtTelefone = (EditText) findViewById(R.id.txt_telefone);
        tiposDeImovel = (RadioGroup) findViewById(R.id.opt_tipos);
        btnSalvar = (Button) findViewById(R.id.btn_salvar);
        tamanhosDeImovel = (RadioGroup) findViewById(R.id.opt_tamanhos);
        emConstrucao = (Switch) findViewById(R.id.sw_construcao);

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


        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    int telefone = Integer.parseInt(txtTelefone.getText().toString());
                    if (tipoMarcado == null || tamanhoMarcado == null){
                        Toast.makeText(InsertActivity.this, getString(R.string.txt_erroFaltaInformacoes), Toast.LENGTH_SHORT).show();
                    }else {
                        imovel = new ImovelModel(
                                telefone,
                                tipoMarcado.getText().toString(),
                                tamanhoMarcado.getText().toString(),
                                emConstrucao.isChecked());
                        Log.v("New",imovel.toString());
                        Toast.makeText(InsertActivity.this, getString(R.string.txt_infoInserida), Toast.LENGTH_SHORT).show();
                    }




                }catch(Exception e){
                    Log.v("DEBUG","Exception -> "+e.toString());
                    Toast.makeText(InsertActivity.this, getString(R.string.txt_erroInfoInvalida), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}