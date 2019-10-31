package com.B3B.sendmeal;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
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

        _PLATOS = (ArrayList<Plato>) PlatoRepository.getInstance().getListaPlatos();
        if (_PLATOS.isEmpty()) {
            PlatoRepository.getInstance().listarPlatos();
            _PLATOS = (ArrayList<Plato>) PlatoRepository.getInstance().getListaPlatos();
        }

        mAdapter = new PlatoViewAdapter(getApplicationContext(),_PLATOS,this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
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
                                Plato pl = _PLATOS.get(position);
                                PlatoRepository.getInstance().borrarPlato(pl);
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

    public void showDialogCantidad(final int position){
        LayoutInflater inflater = LayoutInflater.from(this);
        final View dialogoView = inflater.inflate(R.layout.dialog_cantidad,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final EditText campoCantidad = (EditText) dialogoView.findViewById(R.id.txtCantidadDialog);

        builder.setView(dialogoView).setTitle("Cantidad")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    int cantidadPedido = Integer.valueOf(campoCantidad.getText().toString());
                    Intent i1 = new Intent(getApplicationContext(), AltaPedido.class);
                    i1.putExtra("posicion", position);
                    i1.putExtra("cantidad", cantidadPedido);
                    startActivity(i1);
                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        AlertDialog dialog = builder.create();

        dialog.show();
    }

}
