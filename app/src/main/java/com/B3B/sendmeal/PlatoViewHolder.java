package com.B3B.sendmeal;

import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


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

    public void setImagePlato(String fotoCodificadaBase64){
        byte[] bytesDecodeados = Base64.decode(fotoCodificadaBase64, Base64.DEFAULT);
        try {
            OutputStream stream = new FileOutputStream(Environment.DIRECTORY_PICTURES);
            stream.write(bytesDecodeados);
            Path destino = Paths.get(Environment.DIRECTORY_PICTURES, "image.jpg");
            Files.write(destino, bytesDecodeados);
        } catch (FileNotFoundException ex) {
            Log.d("EXCEPCION", "NO ENCONTRO LA FOTO");
            ex.printStackTrace();
        } catch (IOException ex){
            Log.d("EXCEPCION", "NO PUDO ABRIR LA FOTO");
            ex.printStackTrace();
        }
    }
}
