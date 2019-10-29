package com.B3B.sendmeal;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PedidoViewHolder extends RecyclerView.ViewHolder {
    TextView nombrePlato;
    TextView precioPlato;
    TextView cantidadPlato;
    ImageView imagenPlato;
    Button editarPlatoPedido;
    Button eliminarPlatoPedido;

    public PedidoViewHolder(@NonNull View itemView) {
        super(itemView);
        nombrePlato = (TextView) itemView.findViewById(R.id.txtTituloPlatoEnPedido);
        precioPlato = (TextView) itemView.findViewById(R.id.txtPrecioPlatoEnPedido);
        cantidadPlato = (TextView) itemView.findViewById(R.id.txtCantidadPlatoEnPedido);
        imagenPlato = (ImageView) itemView.findViewById(R.id.imagenPlatoPedido);
        editarPlatoPedido = (Button) itemView.findViewById(R.id.btnEditarPlatoPedido);
        eliminarPlatoPedido = (Button) itemView.findViewById(R.id.btnEliminarPlatoPedido);
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

    public TextView getCantidadPlato() {
        return cantidadPlato;
    }

    public void setCantidadPlato(String cantidadPlato) {
        this.cantidadPlato.setText(cantidadPlato);
    }

    public ImageView getImagenPlato() {
        return imagenPlato;
    }

    public void setImagenPlato() {
        this.imagenPlato.setImageResource(R.drawable.costillita);
    }
}
