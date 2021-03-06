package com.projFg.moreaqui.fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.navigation.NavigationView;
import com.projFg.moreaqui.DAO.ImovelDAO;
import com.projFg.moreaqui.R;
import com.projFg.moreaqui.activities.ShowAddressesActivity;
import com.projFg.moreaqui.config;

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
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Menu flutuante
        final NavigationView navigationView = view.findViewById(R.id.navigationViewImovel);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Bundle bundle;
                bundle = getArguments();
                ImovelDAO imDao = new ImovelDAO(getContext());
                if(item.getTitle().equals(getString(R.string.txt_apagar))){
                    //Botão de apagar imovel
                    Log.v(config.DEBUG_MENU_IMOVEL,"Removendo imovel id:"+bundle.getLong("id"));

                    imDao.removerImovel(bundle.getLong("id"));
                    Intent i = new Intent(getContext(),getActivity().getClass());
                    i.putExtra("insert",false);
                    i.putExtra("remove",true);

                    startActivity(i);
                    getActivity().finishAffinity();
                    getActivity().overridePendingTransition(R.anim.res_anim_fadein, R.anim.res_anim_fadeout);
                }else if(item.getTitle().equals(getString(R.string.txt_editar))){
                    //Função do botão de editar o imovel

                }else if(item.getTitle().equals(getString(R.string.btn_visualizar))){
                    Intent i = new Intent(getActivity(),ShowAddressesActivity.class);
                    i.putExtra("goTo",true);
                    i.putExtra("latitude",bundle.getDouble("latitude"));
                    i.putExtra("longitude",bundle.getDouble("longitude"));
                    startActivity(i);
                    getActivity().finishAffinity();
                    getActivity().overridePendingTransition(R.anim.res_anim_fadein, R.anim.res_anim_fadeout);
                }
                return false;
            }
        });
    }
}