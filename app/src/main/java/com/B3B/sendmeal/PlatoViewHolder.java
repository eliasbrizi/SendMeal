package com.B3B.sendmeal;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class PlatoViewHolder extends RecyclerView.ViewHolder {

    TextView nombrePlato;
    TextView precioPlato;
    ImageView imagenPlato;

    public PlatoViewHolder(@NonNull View itemView) {
        super(itemView);
        nombrePlato = (TextView) itemView.findViewById(R.id.txtTituloPlatoEnLista);
        precioPlato = (TextView) itemView.findViewById(R.id.txtPrecioPlatoEnLista);
        imagenPlato = (ImageView) itemView.findViewById(R.id.imagenPlatoLista);
    }

    public TextView getNombrePlato() {
        return nombrePlato;
    }

    public void setNombrePlato(String nombrePlato) {
        this.nombrePlato.setText(nombrePlato);
    }

    public TextView getPrecioPlato() {
        return precioPlato;
    }

    public void setPrecioPlato(String precioPlato) {
        this.precioPlato.setText(precioPlato);
    }

    public ImageView getImagenPlato() {
        return imagenPlato;
    }

    public void setImagenPlato() {
        this.imagenPlato.setImageResource(R.drawable.ic_launcher_foreground);
    }
}
