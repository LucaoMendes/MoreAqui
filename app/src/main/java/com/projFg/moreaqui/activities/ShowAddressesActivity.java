package com.projFg.moreaqui.activities;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
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
import com.projFg.moreaqui.DAO.ImovelDAO;
import com.projFg.moreaqui.R;
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

public class ShowAddressesActivity extends FragmentActivity implements OnMapReadyCallback{

    private GoogleMap mMap;
    private final List<BitmapDescriptor> images = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_addresses);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MoreAqui5Activity.class));
        finishAffinity();
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
                iMark.setTitle(getString(R.string.txt_infoImovel) + imovel.TYPE);
                iMark.setSnippet(getString(R.string.txt_infoImovelContato)+ imovel.PHONE +"\n" + getString(R.string.txt_tamanho) + imovel.SIZE);
                iMark.setIcon(images.get(0));

                //groundOverlay = mMap.addGroundOverlay(
                //        new GroundOverlayOptions().image(images.get(0)).position(pos,3600f));

            }
        }
    }
}