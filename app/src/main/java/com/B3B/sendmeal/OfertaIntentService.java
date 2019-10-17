package com.B3B.sendmeal;

import android.app.IntentService;
import android.content.Intent;
import android.widget.Toast;

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
            Thread.sleep(5000);
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Intent i1 = new Intent();
        i1.putExtra("Nombre", "Plato en ofertaaaaa");
        i1.setAction(OfertaBroadcastReceiver.OFERTA);
        sendBroadcast(i1);
    }


}
