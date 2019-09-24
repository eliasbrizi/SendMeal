package com.B3B.sendmeal;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.B3B.sendmeal.domain.Plato;

public class AltaNuevoPlato extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altanuevoplato);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarBack);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*
        Variables de pantalla
         */
        final EditText editIdPlato = (EditText) findViewById(R.id.editNumIDANP);
        final EditText editTituloPlato = (EditText) findViewById(R.id.editTituloANP);
        final EditText editDescripcionPlato = (EditText) findViewById(R.id.editDescripcionANP);
        final EditText editPrecio = (EditText) findViewById(R.id.editPrecioANP);
        final EditText editCalorias = (EditText) findViewById(R.id.editCaloriasANP);

        final Button btnGuardarPlato = (Button) findViewById(R.id.buttonGuardarPlato);

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
                    Plato pl = new Plato(Integer.parseInt(editIdPlato.getText().toString()),
                            Integer.parseInt(editCalorias.getText().toString()),
                            Double.parseDouble(editPrecio.getText().toString()),
                            editTituloPlato.getText().toString(),
                            editDescripcionPlato.getText().toString());
                    Toast.makeText(getApplicationContext(),R.string.platoCreado,Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
