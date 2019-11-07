package com.B3B.sendmeal;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class PlatoViewHolder extends RecyclerView.ViewHolder {

    TextView nombrePlato;
    TextView precioPlato;
    ImageView imagenPlato;
    Button editarPlato;
    Button ofertarPlato;
    Button eliminarPlato;

    public PlatoViewHolder(@NonNull View itemView) {
        super(itemView);
        nombrePlato = (TextView) itemView.findViewById(R.id.txtTituloPlatoEnLista);
        precioPlato = (TextView) itemView.findViewById(R.id.txtPrecioPlatoEnLista);
        imagenPlato = (ImageView) itemView.findViewById(R.id.imagenPlatoLista);
        imagenPlato.setClickable(true);
        editarPlato = (Button) itemView.findViewById(R.id.btnEditarPlato);
        ofertarPlato = (Button) itemView.findViewById(R.id.btnOfertarPlato);
        eliminarPlato = (Button) itemView.findViewById(R.id.btnEliminarPlato);
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
}
