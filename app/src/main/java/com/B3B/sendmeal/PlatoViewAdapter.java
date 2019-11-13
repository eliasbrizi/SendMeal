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

public class PlatoViewAdapter extends RecyclerView.Adapter<PlatoViewHolder> {

    private List<Plato> platos;
    private Context contexto;
    private ListaPlatos listaPlatos;

    public  PlatoViewAdapter(Context cont, List<Plato> pl,ListaPlatos lp){
        platos = pl;
        contexto = cont;
        listaPlatos = lp;
    }

    @NonNull
    @Override
    public PlatoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_plato, parent, false);
        PlatoViewHolder vh = new PlatoViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull PlatoViewHolder holder, final int position) {
        final Plato pl = platos.get(position);
        holder.setNombrePlato(pl.getNombre());
        holder.setPrecioPlato(pl.getPrecio().toString());
        /*
        Cargar imagen
         */
        holder.setImagenPlato(pl.getNombre());

        holder.imagenPlato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listaPlatos.showDialogCantidad(position);
            }
        });
        holder.editarPlato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent (contexto,EditarPlato.class);
                i1.putExtra("posicion",position);
                listaPlatos.startActivity(i1);
            }
        });
        holder.ofertarPlato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listaPlatos.ponerEnOferta(position);
            }
        });
        holder.eliminarPlato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listaPlatos.showDialogEliminar(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return platos.size();
    }
}
