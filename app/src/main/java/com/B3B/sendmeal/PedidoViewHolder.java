package com.B3B.sendmeal;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
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
    Button editarCantidadPedido;
    Button eliminarPlatoPedido;

    public PedidoViewHolder(@NonNull View itemView) {
        super(itemView);
        nombrePlato = (TextView) itemView.findViewById(R.id.txtTituloPlatoEnPedido);
        precioPlato = (TextView) itemView.findViewById(R.id.txtPrecioPlatoEnPedido);
        cantidadPlato = (TextView) itemView.findViewById(R.id.txtCantidadPlatoEnPedido);
        imagenPlato = (ImageView) itemView.findViewById(R.id.imagenPlatoPedido);
        editarCantidadPedido = (Button) itemView.findViewById(R.id.btnEditarCantidadPedido);
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

    public void setImagenPlato(String nombrePlato) {
        switch (nombrePlato){
            case "Milanesa": this.imagenPlato.setImageResource(R.drawable.milanesa);
                break;
            case "Papas Fritas": this.imagenPlato.setImageResource(R.drawable.papas);
                break;
            case "Hamburguesa": this.imagenPlato.setImageResource(R.drawable.hamburguesa);
                break;
            case "Pizza": this.imagenPlato.setImageResource(R.drawable.pizza);
                break;
            case "Matambre": this.imagenPlato.setImageResource(R.drawable.matambre);
                break;
            default: this.imagenPlato.setImageResource(R.drawable.costillita);
        }
    }

    public void setImagePlato(String fotoBase64){
        byte[] bytesDecodeados = Base64.decode(fotoBase64, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytesDecodeados, 0, bytesDecodeados.length);
        imagenPlato.setImageBitmap(bitmap);
    }


}
