package com.B3B.sendmeal;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
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

        BroadcastReceiver br = new OfertaBroadcastReceiver();
        IntentFilter filtro = new IntentFilter();
        filtro.addAction(OfertaBroadcastReceiver.OFERTA);
        getApplication().getApplicationContext().registerReceiver(br, filtro);

        startService(getIntent());
    }

    @Override
    public void onDestroy(){
        this.registerReceiver(null, null);
    }
}
