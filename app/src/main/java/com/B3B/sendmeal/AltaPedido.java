package com.B3B.sendmeal;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.B3B.sendmeal.dao.PedidoRepository;
import com.B3B.sendmeal.dao.PlatoRepository;
import com.B3B.sendmeal.domain.ItemsPedido;
import com.B3B.sendmeal.domain.Pedido;
import com.B3B.sendmeal.domain.Plato;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AltaPedido extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static ArrayList<Plato> _PLATOS;

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

        final Button agregarPlatoPedido = (Button) findViewById(R.id.btnAgregarComida);
        final Button crearPedido = (Button) findViewById(R.id.btnCrearPedido);
        final Button enviarPedido = (Button) findViewById(R.id.btnEnviarPedido);

        PlatoRepository.getInstance().listarPlatos();
        List<Plato> aux = PlatoRepository.getInstance().getListaPlatos();

        _PLATOS = new ArrayList<Plato>();
        _PLATOS.add(aux.get(getIntent().getExtras().getInt("posicion")));

        mAdapter = new PedidoViewAdapter(getApplicationContext(), _PLATOS, this, getIntent().getExtras().getInt("cantidad"));
        mRecyclerView.setAdapter(mAdapter);

        agregarPlatoPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        crearPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlatoRepository.getInstance().listarPlatos();
                List<Plato> platos = PlatoRepository.getInstance().getListaPlatos();
                ArrayList<ItemsPedido> items = new ArrayList<ItemsPedido>();
                for (int i = 0; i < platos.size(); i++) {
                    ItemsPedido ip = new ItemsPedido();
                    ip.setIdItem(i+1);
                    ip.setCantidad(1);
                    ip.setPrecio(platos.get(i).getPrecio());
                    ip.setPlatoItem(platos.get(i));
                    items.add(ip);
                }
                Pedido p = new Pedido();
                p.setIdPedido(2);
                p.setFechaPedido(new Date(System.currentTimeMillis()));
                p.setEstadoPedido(1);
                p.setLat(50);
                p.setLng(3.6);
                p.setItems(items);
                newPedido(p);
            }
        });

        enviarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void newPedido(Pedido p){
        PedidoRepository.getInstance(getApplicationContext()).crearPedido(p);
    }
}