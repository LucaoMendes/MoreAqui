package com.projFg.moreaqui.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
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
import com.projFg.moreaqui.model.LocationEstate;
import com.projFg.moreaqui.model.Mask;

import java.util.List;


/**
 * Grupo 11
 * GitHub:https://github.com/LucaoMendes/MoreAqui
 * Trello:https://trello.com/b/XstseyJW/moreaqui
 * Lucas Vinicius Silva Mendes - Mat. 201806442
 * João Gabriel da Silva - Mat. 201805070
 * Lucas Eduardo M de Amorim - Mat. 201708075
 * Marcos Vinicius Silva - Mat. 201900939
 * Igor Bezerra Borges de Lima - Mat. 202005035
 */

public class InsertActivity extends AppCompatActivity {

    //Instanciando Variaveis
    EditText txtTelefone;
    RadioGroup tiposDeImovel, tamanhosDeImovel;
    FloatingActionButton fabInserir;
    RadioButton tipoMarcado, tamanhoMarcado;
    SwitchMaterial emConstrucao;
    LocationEstate imovel;
    ImovelDAO imovelDAO;

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MoreAqui5Activity.class));
        finishAffinity();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Verificação da permissão da localização
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                //Verifica se a versão do android é igual ou maior que Marshmallow e faz a solicitação
                //da Permissão
                requestPermissions(perms,200);
            }
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);


        /*
         * Inicializando variaveis
         */
        txtTelefone = findViewById(R.id.txt_telefone);
        tiposDeImovel = findViewById(R.id.opt_tipos);
        tamanhosDeImovel = findViewById(R.id.opt_tamanhos);
        emConstrucao = findViewById(R.id.sw_construcao);
        fabInserir = findViewById(R.id.fabInserir);
        imovelDAO = new ImovelDAO(this);

        txtTelefone.addTextChangedListener(Mask.mask(txtTelefone, Mask.FORMAT_FONE));

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
                tipoMarcado = findViewById(checkedId);
            }
        });
        tamanhosDeImovel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                tamanhoMarcado = findViewById(checkedId);
            }
        });


        //onClick Listeners
        fabInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    //Verifica a versão do android, se Maior que Marshmallow pega a localização anteriormente PERMITIDA
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                        double[] l =  getLocation();
                        //Verifica o tamanho do telefone
                        String telefone = txtTelefone.getText().toString();
                        if(telefone.length() == 14) {

                            if (tipoMarcado == null || tamanhoMarcado == null) {
                                Snackbar.make(v, R.string.txt_erroFaltaInformacoes, Snackbar.LENGTH_SHORT).show();
                            } else {
                                double[] local;
                                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                                    local = getLocation();
                                    if(local!=null){
                                        double latitude = local[0];
                                        double longitude = local[1];
                                        imovel = new LocationEstate(
                                                telefone,
                                                tipoMarcado.getText().toString(),
                                                tamanhoMarcado.getText().toString(),
                                                Boolean.toString(emConstrucao.isChecked()),
                                                latitude,
                                                longitude);
                                        Log.v(config.DEBUG_INSERTACT,imovel.toString());
                                        Long id = imovelDAO.inserirImovel(imovel);



                                        Intent i = new Intent(InsertActivity.this, ShowActivity.class);
                                        i.putExtra("insert", true);
                                        startActivity(i);
                                        finishAffinity();
                                    }else{
                                        Log.v(config.DEBUG_INSERTACT, "Erro de GPS - getLocation = null"); //Debug de codigo via logCat
                                        Snackbar.make(v, R.string.txt_erroGPS, Snackbar.LENGTH_LONG).show();
                                    }
                                }
                                //Fechar teclado
                                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

                            }


                        } else {
                            Log.v(config.DEBUG_INSERTACT, "Exception -> TELEFONE INVALIDO"); //Debug de codigo via logCat
                            Snackbar.make(v, R.string.txt_erroInfoInvalida, Snackbar.LENGTH_SHORT).show();

                        }
                    }
                }catch(Exception e){
                    Log.v(config.DEBUG_INSERTACT, "Exception -> " + e.toString()); //Debug de codigo via logCat
                    Snackbar.make(v, R.string.txt_erroPermissao, Snackbar.LENGTH_SHORT).show();
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
    @RequiresApi(api = Build.VERSION_CODES.M)
    public double[] getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
            requestPermissions(perms,200);
        }

        LocationManager lm = (LocationManager)getApplicationContext().getSystemService(LOCATION_SERVICE);
        List<String> providers = lm.getProviders(true);
        Location location = null;
        for (String provider : providers) {
            Location l = lm.getLastKnownLocation(provider);
            if (l == null) {
                continue;
            }
            if (location == null || l.getAccuracy() < location.getAccuracy()) {
                location = l;
            }
        }

        if(location!=null){
            double longitude = location.getLongitude();
            double latitude = location.getLatitude();
            double[] local = new double[2];
            local[0] = latitude;
            local[1] = longitude;
            return local;
        }else{

            return null;
        }




    }


}