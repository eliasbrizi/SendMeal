package com.B3B.sendmeal.domain;

import android.app.IntentService;
import android.content.Intent;

import com.B3B.sendmeal.ListaPlatos;

public class OfertaIntentService extends IntentService {
    public OfertaIntentService(){
        super("OfertaIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //hacer logica servicio
        String name = "";
        try {
            name = Thread.currentThread().getName();
            Thread.sleep(10000);
            final Plato plato = ListaPlatos._PLATOS.get(intent.getExtras().getInt("posicion"));
            plato.setOferta(true);
        }
        catch (InterruptedException e) {

        }
    }
}
