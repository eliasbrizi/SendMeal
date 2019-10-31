package com.B3B.sendmeal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.B3B.sendmeal.domain.Plato;

import java.util.List;

public class PlatosPedidoViewAdapter extends RecyclerView.Adapter<PlatosPedidoViewHolder>{
    private List<Plato> platos;
    private Context contexto;
    private MostrarPlatosPedido mostrarPlatosPedido;

    public PlatosPedidoViewAdapter(Context cont, List<Plato> pl, MostrarPlatosPedido mpp){
        platos = pl;
        contexto = cont;
        mostrarPlatosPedido = mpp;
    }

    @NonNull
    @Override
    public PlatosPedidoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_mostrarplatopedido, parent, false);
        PlatosPedidoViewHolder vh = new PlatosPedidoViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull PlatosPedidoViewHolder holder, final int position) {
        final Plato pl = platos.get(position);
        holder.setNombrePlato(pl.getNombre());
        holder.setPrecioPlato(pl.getPrecio().toString());
        /*
        Cargar imagen
         */
        //TODO
        holder.setImagenPlato();

        holder.agregarPlato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarPlatosPedido.agregarComidaAPedido(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return platos.size();
    }

}