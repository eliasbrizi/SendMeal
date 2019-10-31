package com.B3B.sendmeal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.B3B.sendmeal.dao.PlatoRepository;

public class BuscarPlato extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busquedaplatos);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarBack);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        final EditText editPrecioMin = (EditText) findViewById(R.id.editMinBP);
        final EditText editPrecioMax = (EditText) findViewById(R.id.editMaxBP);

        final Button btnBuscar = (Button) findViewById(R.id.buttonBuscarBP);


        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("falla","click");
                PlatoRepository.getInstance().buscarPlatoPorPrecio(
                        Integer.parseInt(editPrecioMin.getText().toString()),
                        Integer.parseInt(editPrecioMax.getText().toString()));
                Intent i1 = new Intent(getApplicationContext(),ListaPlatos.class);
                startActivity(i1);
            }
        });

    }


}
