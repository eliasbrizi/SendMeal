package com.B3B.sendmeal;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PlatosPedidoViewHolder extends RecyclerView.ViewHolder {
    TextView nombrePlato;
    TextView precioPlato;
    ImageView imagenPlato;
    Button agregarPlato;

    public PlatosPedidoViewHolder(@NonNull View itemView) {
        super(itemView);
        nombrePlato = (TextView) itemView.findViewById(R.id.txtTituloPlatoEnPedido);
        precioPlato = (TextView) itemView.findViewById(R.id.txtPrecioPlatoEnPedido);
        imagenPlato = (ImageView) itemView.findViewById(R.id.imagenPlatoPedido);
        agregarPlato = (Button) itemView.findViewById(R.id.btnAgregarPlatoPedido);
    /*
    Posicion en lista
     */
        nombrePlato.setTag(getAdapterPosition());
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
        this.imagenPlato.setImageResource(R.drawable.costillita);
    }
}
