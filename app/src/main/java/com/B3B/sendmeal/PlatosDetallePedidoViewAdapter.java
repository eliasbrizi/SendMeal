package com.B3B.sendmeal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.B3B.sendmeal.domain.Plato;

import java.util.List;

public class PlatosDetallePedidoViewAdapter extends RecyclerView.Adapter<PlatosDetallePedidoViewHolder>{
    private List<Plato> platos;
    private Context contexto;

    public PlatosDetallePedidoViewAdapter(Context cont, List<Plato> pl){
        platos = pl;
        contexto = cont;
    }

    @NonNull
    @Override
    public PlatosDetallePedidoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_mostrarplatodetallepedido, parent, false);
        PlatosDetallePedidoViewHolder vh = new PlatosDetallePedidoViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull PlatosDetallePedidoViewHolder holder, final int position) {
        final Plato pl = platos.get(position);
        holder.setNombrePlato(pl.getNombre());
        holder.setPrecioPlato(pl.getPrecio().toString());
        /*
        Cargar imagen
         */
        if(pl.getFotoBase64() == null) {
            holder.setImagenPlato(pl.getNombre());
        }
        else{
            holder.setImagePlato(pl.getFotoBase64());
        }
    }

    @Override
    public int getItemCount() {
        return platos.size();
    }

}