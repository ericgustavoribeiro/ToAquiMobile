package com.ejt.toaquimobile;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ejt.toaquimobile.Avaliacao;
import com.ejt.toaquimobile.R;

import java.util.List;

/**
 * Created by Eric Gustavo on 21/05/2016.
 */
public class AvaliacaoAdapter extends BaseAdapter {

    Context context;
    List<Avaliacao> avaliacoes;

    public AvaliacaoAdapter(Context context, List<Avaliacao> avaliacoes){
        this.context = context;
        this.avaliacoes = avaliacoes;
    }

    @Override
    public int getCount() {
        return avaliacoes.size();
    }

    @Override
    public Object getItem(int position) {
        return avaliacoes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Avaliacao avaliacao = avaliacoes.get(position);

        ViewHolder holder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_avaliacao, null);
            holder = new ViewHolder();
            holder.txtNomeUser = (TextView) convertView.findViewById(R.id.textViewNomeAvaliacao);
            holder.txtNota = (TextView) convertView.findViewById(R.id.textViewNotaAVALIACAO);
            holder.edtDescricao = (EditText) convertView.findViewById(R.id.editTextDescAvaliacao);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        Resources res = context.getResources();

        holder.txtNomeUser.setText(avaliacao.getUsuario().getNome());
        holder.txtNota.setText(String.valueOf(avaliacao.getNota()));
        holder.edtDescricao.setText(avaliacao.getDescricao());

        return convertView;
    }

    static class ViewHolder{
        TextView txtNomeUser;
        TextView txtNota;
        EditText edtDescricao;

    }
}
