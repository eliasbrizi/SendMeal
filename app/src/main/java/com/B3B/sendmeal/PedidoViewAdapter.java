package com.B3B.sendmeal;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.B3B.sendmeal.domain.Plato;

import java.util.List;

public class PedidoViewAdapter extends RecyclerView.Adapter<PedidoViewHolder>{
    private List<Plato> platos;
    private Context contexto;
    private AltaPedido altaPedido;

    public PedidoViewAdapter(Context cont, List<Plato> pl, AltaPedido ap){
        platos = pl;
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
        final Plato pl = platos.get(position);
        holder.setNombrePlato(pl.getNombre());
        holder.setPrecioPlato(pl.getPrecio().toString());
        holder.setCantidadPlato("1");
        /*
        Cargar imagen
         */
        //TODO
        holder.setImagenPlato();

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

        holder.agregarPlatoPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.crearPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.enviarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return platos.size();
    }
}
