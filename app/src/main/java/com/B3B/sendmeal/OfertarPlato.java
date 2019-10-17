package com.B3B.sendmeal;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.B3B.sendmeal.domain.Plato;

public class OfertarPlato extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Plato plato = ListaPlatos._PLATOS.get(getIntent().getExtras().getInt("posicion"));
        plato.setOferta(true);

        Toast.makeText(getApplicationContext(),R.string.platoOfertado,Toast.LENGTH_SHORT).show();
        startService(getIntent());
    }
}
