package com.B3B.sendmeal;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.B3B.sendmeal.domain.Plato;

public class EditarPlato extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editarplato);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarBack);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*
        Variables de pantalla
         */
        final EditText editIdPlato = (EditText) findViewById(R.id.editNumIDEditarPlato);
        final EditText editTituloPlato = (EditText) findViewById(R.id.editTituloEditarPlato);
        final EditText editDescripcionPlato = (EditText) findViewById(R.id.editDescripcionEditarPlato);
        final EditText editPrecio = (EditText) findViewById(R.id.editPrecioEditarPlato);
        final EditText editCalorias = (EditText) findViewById(R.id.editCaloriasEditarPlato);

        final Button btnGuardarPlato = (Button) findViewById(R.id.buttonGuardarPlatoEditarPlato);

        /*
        Logica
        */

        final Plato plato = ListaPlatos._PLATOS.
                get(getIntent().getExtras().getInt("posicion"));
        editCalorias.setText(plato.getCalorias().toString());
        editDescripcionPlato.setText(plato.getDescripcion());
        editIdPlato.setText(plato.getID().toString());
        editPrecio.setText(plato.getPrecio().toString());
        editTituloPlato.setText(plato.getNombre());
        /*

         */
        btnGuardarPlato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editCalorias.getText().toString().equals("") ||
                        editDescripcionPlato.getText().toString().equals("") ||
                        editIdPlato.getText().toString().equals("") ||
                        editPrecio.getText().toString().equals("") ||
                        editTituloPlato.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(),R.string.faltanDatos,Toast.LENGTH_SHORT).show();
                } else {

                    plato.setCalorias(Integer.parseInt(editCalorias.getText().toString()));
                    plato.setPrecio(Double.parseDouble(editPrecio.getText().toString()));
                    plato.setNombre(editTituloPlato.getText().toString());
                    plato.setDescripcion(editCalorias.getText().toString());

                    Toast.makeText(getApplicationContext(),R.string.platoCreado,Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
