package com.projFg.moreaqui.activities;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import android.Manifest;
import android.animation.Animator;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.projFg.moreaqui.DAO.ImovelDAO;
import com.projFg.moreaqui.R;
import com.projFg.moreaqui.model.LocationEstate;
import java.util.ArrayList;
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

public class ShowAddressesActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private final List<BitmapDescriptor> images = new ArrayList<>();
    private FloatingActionButton fabMenu,fabAdd,fabHome,fabSair;
    boolean fabOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_addresses);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        fabMenu = findViewById(R.id.fabMenu);
        fabHome = findViewById(R.id.fabHome);
        fabAdd = findViewById(R.id.fabAdd);
        fabSair = findViewById(R.id.fabSair);

        fabMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabAnimar();
            }
        });
        fabHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), MoreAqui5Activity.class));
                finishAffinity();

            }
        });
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), InsertActivity.class));
                finishAffinity();
            }
        });
        fabSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);

            }
        });

    }

    public void fabAnimar(){
        float rotationMenu = fabMenu.getRotation();
        float rotationMiniFabs;



        if(rotationMenu == 225){
            fabOpen = false;
            rotationMenu = 0;
            rotationMiniFabs = 0;
            fabAdd.animate().translationY(0).rotation(rotationMiniFabs).setDuration(500);
            fabHome.animate().translationY(0).rotation(rotationMiniFabs).setDuration(500);
            fabSair.animate().translationY(0).rotation(rotationMiniFabs).setDuration(500);
        }else{
            fabOpen=true;
            rotationMenu = 225;
            rotationMiniFabs = 360;
            fabAdd.animate().translationY(-getResources().getDimension(R.dimen.dimenFab1)).rotation(rotationMiniFabs).setDuration(500);
            fabHome.animate().translationY(-getResources().getDimension(R.dimen.dimenFab2)).rotation(rotationMiniFabs).setDuration(500);
            fabSair.animate().translationY(-getResources().getDimension(R.dimen.dimenFab3)).rotation(rotationMiniFabs).setDuration(500);
        }

        fabMenu.animate().rotation(rotationMenu).setDuration(500).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                fabMenu.setClickable(false);
                fabAdd.setClickable(false);
                fabHome.setClickable(false);
                fabSair.setClickable(false);


            }

            @Override
            public void onAnimationEnd(Animator animation) {
                fabMenu.setClickable(true);
                if(fabOpen){
                    fabAdd.setClickable(true);
                    fabHome.setClickable(true);
                    fabSair.setClickable(true);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        if(fabOpen){
            fabAnimar();
        }else{
            startActivity(new Intent(this, ShowActivity.class));
            finishAffinity();
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        UiSettings ui = mMap.getUiSettings();
        ui.setCompassEnabled(true);
        ui.setZoomControlsEnabled(true);
        ui.setMyLocationButtonEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(perms,200);
            }
            return;
        }
        mMap.setMyLocationEnabled(true);

        ImovelDAO imDao = new ImovelDAO(getBaseContext());
        List<LocationEstate> lista;



        images.clear();
        images.add(BitmapDescriptorFactory.fromResource(R.drawable.mappoint));


        if(imDao.buscarImoveis() != null){
            lista = imDao.buscarImoveis();
            LocationEstate im = lista.get(0);
            LatLng initialPosition = new LatLng(im.LATITUDE,im.LONGITUDE);
            CameraPosition initialPosCam = new CameraPosition(initialPosition,15,0,0);
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(initialPosCam));
            Intent i = getIntent();
            if(i.getBooleanExtra("goTo",false)){
                initialPosition =
                        new LatLng(
                                i.getDoubleExtra("latitude",0),
                                i.getDoubleExtra("longitude",0));
            }
            initialPosCam = new CameraPosition(initialPosition,15,0,0);
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(initialPosCam),300,null);




            mMap.clear();
            for (LocationEstate imovel:lista) {

                LatLng pos= new LatLng(imovel.LATITUDE,imovel.LONGITUDE);
                Marker iMark = mMap.addMarker(new MarkerOptions().position(pos));
                iMark.setTitle(getString(R.string.txt_infoImovelContato) + imovel.PHONE);
                iMark.setSnippet(getString(R.string.txt_infoImovel)+ imovel.TYPE);
                iMark.setIcon(images.get(0));
                iMark.setTag(imovel);

            }
        }
        mMap.setOnMarkerClickListener(this);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        //Quando clicar no item

        

        return false;

    }
}