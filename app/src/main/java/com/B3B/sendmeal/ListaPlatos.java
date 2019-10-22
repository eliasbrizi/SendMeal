package com.B3B.sendmeal;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.B3B.sendmeal.dao.PlatoRepository;
import com.B3B.sendmeal.domain.Plato;

import java.util.ArrayList;

public class ListaPlatos extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static BroadcastReceiver br;

/*
Lista de platos
 */
    public static ArrayList<Plato> _PLATOS = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listaplatos);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarBack);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerListaPlatos);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //TODO
//        if (_PLATOS.isEmpty()){
//             /*
//              creo una lista de un plato para que no explotee
//             */
//            Plato p;
//            for (int i=0; i<10 ; i++){
//                if(i % 2 == 0){
//                    p = new Plato(i+1, 100+10*i, 350.0+5*i , "Costillita " + String.valueOf(i+1), "Ternera o cerdo");
//                }
//                else{
//                    p = new Plato(i+1, 100+15*i, 150.0+10*i, "Milanesa " + String.valueOf(i+1), "Comun, a la pizza o napolitana");
//                }
//                _PLATOS.add(p);
//            }
//        }
        /*
        borrar anterior
        */

        PlatoRepository.getInstance().listarPlatos();
        _PLATOS = (ArrayList<Plato>) PlatoRepository.getInstance().getListaPlatos();

        mAdapter = new PlatoViewAdapter(getApplicationContext(),_PLATOS,this);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void ponerEnOferta(final int position){
        final Plato plato = ListaPlatos._PLATOS.get(position);
        plato.setOferta(true);

        Toast.makeText(getApplicationContext(),R.string.platoOfertado,Toast.LENGTH_SHORT).show();

        br = new OfertaBroadcastReceiver();
        IntentFilter filtro = new IntentFilter();
        filtro.addAction(OfertaBroadcastReceiver.OFERTA);
        getApplication().getApplicationContext().registerReceiver(br, filtro);

        Intent servicio = new Intent(this, OfertaIntentService.class);
        servicio.putExtra("Posicion", position);
        servicio.putExtra("NombrePlato", plato.getNombre());
        startService(servicio);
    }

    public void showDialogEliminar(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.dialogoEliminarPlato).setTitle(R.string.tituloDialogo)
                .setPositiveButton("Si",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                _PLATOS.remove(position);
                                mAdapter.notifyDataSetChanged();
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

/*    Handler miHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(Message msg) {
            Log.d("APP_2","Vuelve al handler"+msg.arg1);

            switch (msg.arg1 ){
                case PlatoRepository._ALTA_PLATO:
                case PlatoRepository._UPDATE_PLATO:
                    Intent i = new Intent(ObraActivity.this,ObraListActivity.class);
                    startActivity(i);
                    break;
            }
        }
    };*/

}
