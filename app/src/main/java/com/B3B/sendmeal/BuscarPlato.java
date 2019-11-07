package com.B3B.sendmeal;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.B3B.sendmeal.dao.PedidoRepository;
import com.B3B.sendmeal.dao.PlatoRepository;
import com.B3B.sendmeal.domain.EstadoPedido;
import com.B3B.sendmeal.domain.ItemsPedido;
import com.B3B.sendmeal.domain.Pedido;
import com.B3B.sendmeal.domain.Plato;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        final EditText editNombre = (EditText) findViewById(R.id.editNombreBP);

        final Button btnBuscar = (Button) findViewById(R.id.buttonBuscarBP);


        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editNombre.getText().toString().equals("")){
                    PlatoRepository.getInstance().buscarPlatoPorPrecio(
                            Integer.parseInt(editPrecioMin.getText().toString()),
                            Integer.parseInt(editPrecioMax.getText().toString()));
                }
                else if(editPrecioMin.getText().toString().equals("") && editPrecioMax.getText().toString().equals("")){
                    PlatoRepository.getInstance().buscarPlatoPorNombre(editNombre.getText().toString());
                }
                else{
                    PlatoRepository.getInstance().buscarPlatoPorNombreYPrecio(editNombre.getText().toString(), Integer.parseInt(editPrecioMin.getText().toString()), Integer.parseInt(editPrecioMax.getText().toString()));
                }

                Intent i1 = new Intent(getApplicationContext(),ListaPlatos.class);
                startActivity(i1);
            }
        });



    }
}
