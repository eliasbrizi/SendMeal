package com.B3B.sendmeal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.B3B.sendmeal.domain.ItemsPedido;
import com.B3B.sendmeal.domain.Plato;

import java.util.List;

public class PedidoViewAdapter extends RecyclerView.Adapter<PedidoViewHolder>{
    private List<ItemsPedido> items;
    private Context contexto;
    private AltaPedido altaPedido;

    public PedidoViewAdapter(Context cont, List<ItemsPedido> it, AltaPedido ap){
        items = it;
        contexto = cont;
        altaPedido = ap;
    }

    @NonNull
    @Override
    public PedidoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_pedido, parent, false);
        PedidoViewHolder vh = new PedidoViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull PedidoViewHolder holder, final int position) {
        final Plato pl = items.get(position).getPlatoItem();
        holder.setNombrePlato(pl.getNombre());
        holder.setPrecioPlato(pl.getPrecio().toString());
        holder.setCantidadPlato(String.valueOf(items.get(position).getCantidad()));
        /*
        Cargar imagen
         */
        if(pl.getFotoBase64() == null){
            holder.setImagenPlato(pl.getNombre());
        }
        else {
            holder.setImagePlato(pl.getFotoBase64());
        }


        holder.editarPlatoPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.eliminarPlatoPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
