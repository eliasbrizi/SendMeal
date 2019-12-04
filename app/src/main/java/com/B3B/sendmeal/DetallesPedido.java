package com.B3B.sendmeal;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.B3B.sendmeal.dao.PedidoRepository;
import com.B3B.sendmeal.dao.PedidoRepositoryServer;
import com.B3B.sendmeal.dao.PlatoRepository;
import com.B3B.sendmeal.domain.ItemsPedido;
import com.B3B.sendmeal.domain.Pedido;
import com.B3B.sendmeal.domain.Plato;

import java.util.ArrayList;
import java.util.List;

public class DetallesPedido extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private int idPedido;

    public static BroadcastReceiver br;


    public static ArrayList<Plato> _PLATOS = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detallespedido);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarBack);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerDetallesPedido);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        idPedido = getIntent().getExtras().getInt("idPedido");
        Log.d("A VER",""+idPedido);
        //TODO no anda nada
        final Pedido pedido = PedidoRepositoryServer.getInstance().getPedido();
        /*
        Detalles del pedido
         */
        final EditText editID = (EditText) findViewById(R.id.editIDDetPedido);
        final EditText editEstado = (EditText)findViewById(R.id.editEstadoPedidoDetPedido);
        final EditText editCosto = (EditText) findViewById(R.id.editCostoDetPedido);
        editID.setText(String.valueOf(pedido.getIdPedido()));
        editEstado.setText(pedido.getEstadoPedido().toString());
        editCosto.setText("not yet implemented");

        /*
        Recycler
         */
        for (ItemsPedido it : pedido.getItemsPedido()){
            _PLATOS.add(it.getPlatoItem());
        }
        mAdapter = new PlatosDetallePedidoViewAdapter(getApplicationContext(),_PLATOS);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}
