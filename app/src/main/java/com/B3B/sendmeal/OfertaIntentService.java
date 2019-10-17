package com.B3B.sendmeal;

import android.app.IntentService;
import android.content.Intent;

import com.B3B.sendmeal.ListaPlatos;
import com.B3B.sendmeal.domain.Plato;

public class OfertaIntentService extends IntentService {
    public OfertaIntentService(){
        super("OfertaIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //logica servicio
        String name = "";
        try {
            name = Thread.currentThread().getName();
            Thread.sleep(10000);
        }
        catch (InterruptedException e) {

        }
    }
}
