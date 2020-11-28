package com.projFg.moreaqui.activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
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

import java.util.List;


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
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(perms,200);
            }
        }
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
                tipoMarcado = (RadioButton) findViewById(checkedId);
            }
        });
        tamanhosDeImovel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                tamanhoMarcado = (RadioButton) findViewById(checkedId);
            }
        });


        //onClick Listeners
        fabInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                        double[] l =  getLocation();

                        try {
                            int telefone = Integer.parseInt(txtTelefone.getText().toString());
                            if (tipoMarcado == null || tamanhoMarcado == null) {
                                Snackbar.make(v, R.string.txt_erroFaltaInformacoes, Snackbar.LENGTH_SHORT).show();
                            } else {
                                double[] local = new double[0];
                                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                                    local = getLocation();
                                    if(local!=null){
                                        double latitude = local[0];
                                        double longitude = local[1];
                                        imovel = new LocationEstate(
                                                Integer.toString(telefone),
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


                        } catch (Exception e) {
                            Log.v(config.DEBUG_INSERTACT, "Exception -> " + e.toString()); //Debug de codigo via logCat
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