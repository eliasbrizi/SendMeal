package com.B3B.sendmeal;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class OfertaBroadcastReceiver extends BroadcastReceiver {
    public static final String OFERTA = "On Sale";

    private Context contexto;

    @Override
    public void onReceive(Context context, Intent intent) {
        contexto = context;
        //Toast.makeText(context,"El plato " + intent.getExtras().getString("nombrePlato") + " se encuentra en OFERTA!!",Toast.LENGTH_SHORT).show();
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(contexto, "CH1")
                .setSmallIcon(R.drawable.icono_notify).setContentTitle(contexto.getString(R.string.nombreNotificacionOferta))
                .setContentText(intent.getExtras().getString("nombrePlato") + contexto.getString(R.string.detalleNotificacionOferta))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

    }

    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            final String CANAL_ID = "CH1";
            CharSequence name = contexto.getString(R.string.nombreCanal);
            String descripcion = contexto.getString(R.string.descripcionCanal);
            int importancia = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel canal = new NotificationChannel(CANAL_ID, name, importancia);
            canal.setImportance(importancia);
            NotificationManager notificationManager = contexto.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(canal);
        }
    }

}
