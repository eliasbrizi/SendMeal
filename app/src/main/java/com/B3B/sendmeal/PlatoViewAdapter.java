package com.B3B.sendmeal;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.B3B.sendmeal.domain.Plato;

import java.util.List;

public class PlatoViewAdapter extends RecyclerView.Adapter<PlatoViewHolder> {

    private List<Plato> platos;
    private Context contexto;

    public  PlatoViewAdapter(Context cont, List<Plato> pl){
        platos = pl;
        contexto = cont;
    }

    @NonNull
    @Override
    public PlatoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_plato, parent, false);
        PlatoViewHolder vh = new PlatoViewHolder(v);
        //vh.editarPlato.
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull PlatoViewHolder holder, int position) {
        Plato pl = platos.get(position);
        holder.setNombrePlato(pl.getNombre());
        holder.setPrecioPlato(pl.getPrecio().toString());
        /*
        Cargar imagen
         */
        //TODO
        holder.setImagenPlato();
        final Integer posicion = (Integer) holder.nombrePlato.getTag();
        holder.editarPlato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent (contexto,EditarPlato.class);
                new AppCompatActivity().startActivity(i1);
            }
        });
        holder.ofertarPlato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.eliminarPlato.setOnClickListener(new View.OnClickListener() {
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
