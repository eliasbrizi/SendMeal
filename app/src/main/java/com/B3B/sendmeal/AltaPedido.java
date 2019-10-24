package com.B3B.sendmeal;

import android.content.BroadcastReceiver;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.B3B.sendmeal.dao.PlatoRepository;
import com.B3B.sendmeal.domain.Pedido;
import com.B3B.sendmeal.domain.Plato;
import java.util.ArrayList;

public class AltaPedido extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static BroadcastReceiver br;

    /*
    Lista de platos en pedido (pedirla del json-server)
     */
    public static ArrayList<Plato> _PLATOS = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altanuevopedido);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarBack);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerAltaPedido);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        PlatoRepository.getInstance().listarPlatos();
        ArrayList<Plato> aux = (ArrayList<Plato>) PlatoRepository.getInstance().getListaPlatos();
        Plato p = aux.get(getIntent().getExtras().getInt("posicion"));

        _PLATOS.add(p);

        mAdapter = new PedidoViewAdapter(getApplicationContext(), _PLATOS, this);
        mRecyclerView.setAdapter(mAdapter);
    }
}