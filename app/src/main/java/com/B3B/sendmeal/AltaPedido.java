package com.B3B.sendmeal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
import java.util.Random;

public class AltaPedido extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static ArrayList<Plato> _PLATOS;
    private int idPedido;

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

        _PLATOS = new ArrayList<Plato>();

        int idPedaux = getIntent().getExtras().getInt("idPedido");
        if(idPedaux > 0){
            idPedido = idPedaux;
            List<ItemsPedido> items = PedidoRepository.getInstance(getApplicationContext()).buscarItemsPedidoPorIdPedido(idPedido);
            for(ItemsPedido ip : items){
                _PLATOS.add(ip.getPlatoItem());
            }
        }
        else {
            Random r = new Random();
            idPedido = r.nextInt(10000) + 1;
        }

        PlatoRepository.getInstance().listarPlatos();
        List<Plato> aux = PlatoRepository.getInstance().getListaPlatos();

        _PLATOS.add(aux.get(getIntent().getExtras().getInt("posicion")));

        mAdapter = new PedidoViewAdapter(getApplicationContext(), _PLATOS, this, getIntent().getExtras().getInt("cantidad"));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        agregarPlatoPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //insertar item en room
                Random r = new Random();
                int idIt = r.nextInt(10000) + 1;

                List<Plato> platoList = PlatoRepository.getInstance().getListaPlatos();
                Plato p = platoList.get(getIntent().getExtras().getInt("posicion"));

                Pedido pedido = new Pedido();
                pedido.setIdPedido(idPedido);
                pedido.setEstadoPedido(1);
                pedido.setLng(10.0);
                pedido.setLat(20.0);
                pedido.setFechaPedido(new Date(System.currentTimeMillis()));

                ItemsPedido itemsPedido = new ItemsPedido();
                itemsPedido.setPrecio(p.getPrecio());
                itemsPedido.setPlatoItem(p);
                itemsPedido.setCantidad(getIntent().getExtras().getInt("cantidad"));
                itemsPedido.setIdItem(idIt);
                itemsPedido.setIdPedido(pedido.getIdPedido());

                ArrayList<ItemsPedido> items = new ArrayList<ItemsPedido>();
                items.add(itemsPedido);
                pedido.setItemsPedido(items);

                PedidoRepository.getInstance(getApplicationContext()).crearPedido(pedido);
                PedidoRepository.getInstance(getApplicationContext()).crearItemPedido(itemsPedido, pedido);
                Log.d("ROOM", "ITEM PEDIDO CREADO");

                Intent i1 = new Intent(getApplicationContext(), MostrarPlatosPedido.class);
                i1.putExtra("idPedido", idPedido);
                startActivity(i1);
            }
        });

        crearPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //OBSOLETOOOO CAMBIAR LUEGO
                ArrayList<ItemsPedido> items = new ArrayList<ItemsPedido>();
                for (int i = 0; i < _PLATOS.size(); i++) {
                    ItemsPedido ip = new ItemsPedido();
                    ip.setIdItem(i+1);
                    ip.setCantidad(1);
                    ip.setPrecio(_PLATOS.get(i).getPrecio());
                    ip.setPlatoItem(_PLATOS.get(i));
                    items.add(ip);
                }
                Pedido p = new Pedido();
                p.setIdPedido(2);
                p.setFechaPedido(new Date(System.currentTimeMillis()));
                p.setEstadoPedido(1);
                p.setLat(50);
                p.setLng(3.6);
                newPedido(p);
            }
        });

        enviarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO subir pedido al servidor
            }
        });
    }

    private void newPedido(Pedido p){
        PedidoRepository.getInstance(getApplicationContext()).crearPedido(p);
    }
}