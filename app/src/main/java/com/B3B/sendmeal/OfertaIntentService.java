package com.B3B.sendmeal;

import android.app.IntentService;
import android.content.Intent;

public class OfertaIntentService extends IntentService {
    public OfertaIntentService(){
        super("OfertaIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //logica servicio

        try {
            Thread.sleep(10000);
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Intent i1 = new Intent();
        i1.putExtra("Posicion", intent.getExtras().getString("Posicion"));
        i1.putExtra("NombrePlato", intent.getExtras().getString("NombrePlato"));
        sendBroadcast(i1);
    }


}
