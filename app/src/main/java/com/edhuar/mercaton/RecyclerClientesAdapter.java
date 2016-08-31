package com.edhuar.mercaton;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by root on 28/08/16.
 */
public class RecyclerClientesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<Cliente> listaClientes;
    private String rol;
    private Context context;

    public RecyclerClientesAdapter(List<Cliente> listaClientes, String rol, Context context){
        this.listaClientes = listaClientes;
        this.rol = rol;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_cliente, parent, false);
        return RecyclerItemViewHolder.newInstance(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        RecyclerItemViewHolder itemViewHolder = (RecyclerItemViewHolder) holder;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("cod", listaClientes.get(position).codigo);
                Intent intent = new Intent(context, MenuCliente.class);
                intent.putExtra("codigo",listaClientes.get(position).codigo);
                intent.putExtra("rol",rol);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        Cliente cliente = listaClientes.get(position);
        itemViewHolder.setItemValues(cliente);
    }

    @Override
    public int getItemCount() {
        return listaClientes == null? 0: listaClientes.size();
    }
}
