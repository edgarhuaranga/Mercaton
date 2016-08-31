package com.edhuar.mercaton;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by root on 28/08/16.
 */
public class RecyclerItemViewHolder extends RecyclerView.ViewHolder {
    private final TextView mItemTextView;
    private final TextView textViewCodigo;
    private final TextView textViewDireccion;
    private final TextView textViewGiro;
    private final TextView textViewMercado;
    private final ImageView imageViewPerfil;

    public RecyclerItemViewHolder(final View parent, TextView itemTextView, TextView textViewCodigo, TextView textViewDireccion, TextView textViewGiro, TextView textViewMercado, ImageView imageView) {
        super(parent);
        mItemTextView = itemTextView;
        this.textViewCodigo = textViewCodigo;
        this.textViewDireccion = textViewDireccion;
        this.textViewGiro = textViewGiro;
        this.textViewMercado = textViewMercado;
        this.imageViewPerfil = imageView;
    }

    public static RecyclerItemViewHolder newInstance(View parent) {
        TextView itemTextView = (TextView) parent.findViewById(R.id.itemTextView);
        TextView textViewCodigo = (TextView) parent.findViewById(R.id.textview_codigo_cliente);
        TextView textViewDireccion = (TextView) parent.findViewById(R.id.textview_direccion_cliente);
        TextView textViewGiro = (TextView) parent.findViewById(R.id.textview_giro_cliente);
        TextView textViewMercado = (TextView) parent.findViewById(R.id.textview_mercado_cliente);
        ImageView imageViewPerfil = (ImageView) parent.findViewById(R.id.imageview_fotoperfil);
        return new RecyclerItemViewHolder(parent, itemTextView, textViewCodigo, textViewDireccion, textViewGiro, textViewMercado, imageViewPerfil);
    }

    public void setItemValues(Cliente cliente) {
        mItemTextView.setText(cliente.nombre);
        textViewCodigo.setText(cliente.codigo);
        textViewDireccion.setText(cliente.direccion);
        textViewGiro.setText(cliente.giro);
        textViewMercado.setText(cliente.mercado);
    }
}
