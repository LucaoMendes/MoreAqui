package com.projFg.moreaqui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.navigation.NavigationView;
import com.projFg.moreaqui.R;
import com.projFg.moreaqui.activities.MoreAqui4Activity;
import com.projFg.moreaqui.activities.SobreActivity;

/*
 * Grupo 11
 * Lucas Vinicius Silva Mendes
 * João Gabriel
 * Lucas Eduardo M de Amorim
 * Marcos Vinicius Silva
 * Igor Bezerra
 */

public class MenuFragment extends BottomSheetDialogFragment {

    @Override public View onCreateView(
             LayoutInflater inflater,
            ViewGroup container,
             Bundle savedInstanceState
    ) {

        return inflater
                .inflate(R.layout.activity_fragmentmenuflutuante, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Menu flutuante
        final NavigationView navigationView = view.findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getTitle().equals(getString(R.string.txt_inicio))){
                    startActivity(new Intent(getActivity(), MoreAqui4Activity.class));
                    getActivity().finish();
                }else if(item.getTitle().equals(getString(R.string.txt_sobre))){
                    startActivity(new Intent(getActivity(), SobreActivity.class));
                    getActivity().finish();
                }else if(item.getTitle().equals(getString(R.string.txt_sair))){
                    getActivity().finish();
                    System.exit(0);
                }
                return false;
            }
        });
    }

}