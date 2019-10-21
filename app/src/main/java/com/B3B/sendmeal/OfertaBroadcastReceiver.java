package com.B3B.sendmeal;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class OfertaBroadcastReceiver extends BroadcastReceiver {
    public static final String OFERTA = "On Sale";

    private Context contexto;
    final String CHANNEL1 = "Ofertas";

    @Override
    public void onReceive(Context context, Intent intent) {
        contexto = context;
        Intent destinoNotificacion = new Intent(context, MostrarPlato.class);
        destinoNotificacion.putExtra("Posicion", intent.getExtras().getInt("Posicion"));
        destinoNotificacion.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, destinoNotificacion, 0);

        createNotificationChannel();

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, CHANNEL1)
                .setSmallIcon(R.drawable.icono_notify).setContentTitle(context.getString(R.string.nombreNotificacionOferta))
                .setContentText(intent.getExtras().getString("NombrePlato") + " " + context.getString(R.string.detalleNotificacionOferta))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(contexto);
        notificationManagerCompat.notify(99, mBuilder.build());
    }

    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = contexto.getString(R.string.nombreCanal);
            String descripcion = contexto.getString(R.string.descripcionCanal);
            int importancia = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel canal = new NotificationChannel(CHANNEL1, name, importancia);
            canal.setDescription(descripcion);

            NotificationManager notificationManager = contexto.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(canal);
        }
    }

}
