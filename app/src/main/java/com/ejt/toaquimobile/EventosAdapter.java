package com.ejt.toaquimobile;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ejt.toaquimobile.Evento;
import com.ejt.toaquimobile.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Eric Gustavo on 28/04/2016.
 */
public class EventosAdapter extends BaseAdapter {

    Context context;
    List<Evento> eventos;
    private static String URL;

    public EventosAdapter(Context context, List<Evento> eventos){
        this.context = context;
        this.eventos = eventos;
    }

    @Override
    public int getCount() {
        return eventos.size();
    }

    @Override
    public Object getItem(int position) {
        return eventos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Evento evento = eventos.get(position);

        //Novo
        ViewHolder holder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_evento, null);
            holder = new ViewHolder();
            holder.imgEvento = (ImageView) convertView.findViewById(R.id.imgEvento);
            holder.txtNomeEvento = (TextView) convertView.findViewById(R.id.txtNomeEvento);
            holder.txtLocalEvento = (TextView) convertView.findViewById(R.id.txtLocalEvento);
            holder.txtDataEvento = (TextView) convertView.findViewById(R.id.txtDataEvento);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        //http://192.168.1.120
        URL = "http://192.168.43.3:6090/ToAqui/";
        Resources res = context.getResources();

        if(evento.getImagem().equals("")){
            Picasso.with(context).load(R.drawable.semimg).into(holder.imgEvento);
        }else{
            Picasso.with(context).load(URL.concat(evento.getImagem())).into(holder.imgEvento);
        }
        holder.txtNomeEvento.setText(evento.getNome());
        holder.txtLocalEvento.setText(evento.getLocal());
        holder.txtDataEvento.setText(evento.getData_evento());

        return convertView;
    }

    static class ViewHolder{
        ImageView imgEvento;
        TextView txtNomeEvento;
        TextView txtLocalEvento;
        TextView txtDataEvento;

    }
}
