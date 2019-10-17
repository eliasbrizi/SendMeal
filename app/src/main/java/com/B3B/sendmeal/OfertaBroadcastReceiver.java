package com.B3B.sendmeal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class OfertaBroadcastReceiver extends BroadcastReceiver {

    public static final String OFERTA = "On Sale";

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,intent.getAction(),Toast.LENGTH_SHORT).show();
    }
}
