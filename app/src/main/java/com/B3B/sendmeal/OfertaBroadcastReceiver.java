package com.B3B.sendmeal;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class OfertaBroadcastReceiver extends BroadcastReceiver {
    public static final String OFERTA = "On Sale";

    @Override
    public void onReceive(Context context, Intent intent) {
        //Toast.makeText(context,"El plato " + intent.getExtras().getString("nombrePlato") + " se encuentra en OFERTA!!",Toast.LENGTH_SHORT).show();
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, canal)
                .setSmallIcon(icono).setContentTitle(titulo).setContentText(explicacion).setPriority(NotificationCompat.PRIORITY_DEFAULT);

    }

    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "Canal 1";
            String descripcion = "Canal de notificaciones";
            int importancia = NotificationManager.IMPORTANCE_DEFAULT;
        }
    }

}
