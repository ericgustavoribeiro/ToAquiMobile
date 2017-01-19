package com.ejt.toaquimobile;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ejt.toaquimobile.Estabelecimento;
import com.ejt.toaquimobile.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Eric Gustavo on 28/04/2016.
 */
public class EstabelecimentosAdapter extends BaseAdapter {
    Context context;
    List<Estabelecimento> estabelecimentos;
    private static String URL;

    public EstabelecimentosAdapter(Context context, List<Estabelecimento> estabelecimentos){
        this.context = context;
        this.estabelecimentos = estabelecimentos;
    }


    @Override
    public int getCount() {
        return estabelecimentos.size();
    }

    @Override
    public Object getItem(int position) {
        return estabelecimentos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Estabelecimento estab = estabelecimentos.get(position);

       //Antigo
        //View linha = LayoutInflater.from(context).inflate(R.layout.item_estabelecimento, null);

        //Novo
        ViewHolder holder = null;
       if(convertView == null){//Nova View Para Reduzir Dados, Caso tenha necessidae de muitos dados os
           convertView = LayoutInflater.from(context).inflate(R.layout.item_estabelecimento, null);
           holder = new ViewHolder();
           holder.imgEstab = (ImageView) convertView.findViewById(R.id.imgEstab);
           holder.txtNomeEstab = (TextView) convertView.findViewById(R.id.txtNomeEstab);
           holder.txtCategotisEstab = (TextView) convertView.findViewById(R.id.txtCategoriaEstab);
           convertView.setTag(holder);
       }else{//scrolls utilizaram linhas ja criadas
           holder = (ViewHolder)convertView.getTag();
       }


        Resources res = context.getResources();
        URL = "http://192.168.43.3:6090/ToAqui/";

        if(estab.getImagem().equals("")){
            Picasso.with(context).load(R.drawable.semimg).into(holder.imgEstab);
        }else{
            Picasso.with(context).load(URL.concat(estab.getImagem())).into(holder.imgEstab);
        }
        holder.txtNomeEstab.setText(estab.getNome());
        holder.txtCategotisEstab.setText(estab.getCategoria());

        return convertView;
    }

    static class ViewHolder{
        ImageView imgEstab;
        TextView txtNomeEstab;
        TextView txtCategotisEstab;
    }
}
