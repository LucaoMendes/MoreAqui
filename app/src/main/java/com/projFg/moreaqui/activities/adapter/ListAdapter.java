package com.projFg.moreaqui.activities.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.projFg.moreaqui.R;
import com.projFg.moreaqui.model.LocationEstate;
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


public class ListAdapter extends BaseAdapter {
    private final List<LocationEstate> lista_imoveis;
    private final Activity activity;
    public ListAdapter(List<LocationEstate> lista_imoveis, Activity activity) {
        this.lista_imoveis = lista_imoveis;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return lista_imoveis.size();
    }

    @Override
    public LocationEstate getItem(int position) {
        return lista_imoveis.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = activity.getLayoutInflater().inflate(R.layout.imoveis_fragment,parent,false);
        LocationEstate imovel = lista_imoveis.get(position);
        TextView infoImovel = v.findViewById(R.id.txt_infoImovel);
        TextView telefone = v.findViewById(R.id.txt_telefoneImovel);
        TextView tamanho = v.findViewById(R.id.txt_tamanhoImovel);
        TextView infoConstrucao = v.findViewById(R.id.txt_infoConstrucao);
        ImageView img_imovel = v.findViewById(R.id.img_imovel);

        infoImovel.setText(imovel.TYPE + " à venda.");
        telefone.setText(imovel.PHONE);
        tamanho.setText(imovel.SIZE);
        if(Boolean.parseBoolean(imovel.STATUS)){
            infoConstrucao.setText(activity.getString(R.string.txt_construcao));
            infoConstrucao.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.construction,0);
        }else{
            infoConstrucao.setText(activity.getString(R.string.txt_pronto));
            infoConstrucao.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.check,0);
        }
        if(imovel.TYPE.equals("Casa") || imovel.TYPE.equals("Apartamento")){
            img_imovel.setImageResource(R.drawable.home);
        }else{
            img_imovel.setImageResource(R.drawable.shop);
        }




        return v;
    }
}
