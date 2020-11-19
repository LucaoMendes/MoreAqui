package com.projFg.moreaqui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.navigation.NavigationView;
import com.projFg.moreaqui.R;
import com.projFg.moreaqui.activities.MoreAqui2Activity;
import com.projFg.moreaqui.activities.SobreActivity;

public class MenuImovelFragment extends BottomSheetDialogFragment  {

    @Override public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {

        return inflater
                .inflate(R.layout.activity_fragmentmenuimovel, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Menu flutuante
        final NavigationView navigationView = view.findViewById(R.id.navigationViewImovel);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                return false;
            }
        });
    }
}