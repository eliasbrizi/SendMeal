package com.B3B.sendmeal;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.B3B.sendmeal.dao.PlatoRepository;
import com.B3B.sendmeal.domain.Plato;

import java.util.ArrayList;

public class MostrarPlatosPedido extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private int idPedido;

    public static BroadcastReceiver br;

    /*
Lista de platos
 */
    public static ArrayList<Plato> _PLATOS = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrarplatospedido);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarBack);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerMostrarPlatosPedido);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        _PLATOS = (ArrayList<Plato>) PlatoRepository.getInstance().getListaPlatos();
        if (_PLATOS.isEmpty()) {
            PlatoRepository.getInstance().listarPlatos();
            _PLATOS = (ArrayList<Plato>) PlatoRepository.getInstance().getListaPlatos();
        }

        idPedido = getIntent().getExtras().getInt("idPedido");

        mAdapter = new PlatosPedidoViewAdapter(getApplicationContext(), _PLATOS, this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    public void agregarComidaAPedido(final int position){
        LayoutInflater inflater = LayoutInflater.from(this);
        final View dialogoView = inflater.inflate(R.layout.dialog_cantidad,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final EditText campoCantidad = (EditText) dialogoView.findViewById(R.id.txtCantidadDialog);

        builder.setView(dialogoView).setTitle("Cantidad")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        PlatoRepository.getInstance().buscarPlatoPorID(getIntent().getExtras().getInt("idPlato"));

                        int cantidadPedido = Integer.valueOf(campoCantidad.getText().toString());
                        Intent i1 = new Intent(getApplicationContext(), AltaPedido.class);
                        i1.putExtra("posicion", position);
                        i1.putExtra("cantidad", cantidadPedido);
                        i1.putExtra("idPedido", idPedido);
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