package com.B3B.sendmeal;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.B3B.sendmeal.domain.Plato;

public class MostrarPlato extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrarplato);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarBack);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*
        Variables de pantalla
         */
        final EditText editIdPlato = (EditText) findViewById(R.id.editNumIDMostrarPlato);
        final EditText editTituloPlato = (EditText) findViewById(R.id.editTituloMostrarPlato);
        final EditText editDescripcionPlato = (EditText) findViewById(R.id.editDescripcionMostrarPlato);
        final EditText editPrecio = (EditText) findViewById(R.id.editPrecioMostrarPlato);
        final EditText editCalorias = (EditText) findViewById(R.id.editCaloriasMostrarPlato);

        final Button btnGuardarPlato = (Button) findViewById(R.id.buttonGuardarPlatoMostrarPlato);

         /*
        Logica
        */

        final Plato plato = ListaPlatos._PLATOS.get(getIntent().getExtras().getInt("Posicion"));
        editCalorias.setText(plato.getCalorias().toString());
        editDescripcionPlato.setText(plato.getDescripcion());
        editIdPlato.setText(plato.getID().toString());
        editPrecio.setText(plato.getPrecio().toString());
        editTituloPlato.setText(plato.getNombre());
    }
}
