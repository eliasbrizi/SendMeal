package com.B3B.sendmeal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.B3B.sendmeal.dao.PedidoRepository;
import com.B3B.sendmeal.dao.PedidoRepositoryServer;
import com.B3B.sendmeal.dao.PlatoRepository;
import com.B3B.sendmeal.domain.EstadoPedido;
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

    public static ArrayList<ItemsPedido> _ITEMS;
    private int idPedido;
    private Pedido pedido;
    private ItemsPedido ip;

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
        enviarPedido.setEnabled(false);

        _ITEMS = new ArrayList<ItemsPedido>();
        List<ItemsPedido> items = new ArrayList<ItemsPedido>();
        final int idPedaux = getIntent().getExtras().getInt("idPedido");

        if(idPedaux > 0){
            idPedido = idPedaux;
            pedido = PedidoRepository.getInstance(getApplicationContext()).buscarPedidoPorID(idPedido);
            items.addAll(PedidoRepository.getInstance(getApplicationContext()).buscarItemsPedidoPorIdPedido(idPedido));
            for(ItemsPedido itemsPedido : items){
                _ITEMS.add(itemsPedido);
            }
        }
        else {
            Random r = new Random();
            idPedido = r.nextInt(10000) + 1;
            pedido = new Pedido();
            pedido.setIdPedido(idPedido);
        }

        pedido.setEstadoPedido(EstadoPedido.PENDIENTE);
        pedido.setLng(10.0);
        pedido.setLat(20.0);
        pedido.setFechaPedido(new Date(System.currentTimeMillis()));
        pedido.setItemsPedido((ArrayList) items);

        PlatoRepository.getInstance().listarPlatos();
        List<Plato> auxPlato = PlatoRepository.getInstance().getListaPlatos();

        ip = new ItemsPedido();
        ip.setCantidad(getIntent().getExtras().getInt("cantidad"));
        ip.setPlatoItem(auxPlato.get(getIntent().getExtras().getInt("posicion")));
        ip.setPrecio(auxPlato.get(getIntent().getExtras().getInt("posicion")).getPrecio());
        ip.setIdPedido(idPedido);
        if(idPedaux > 0){
            List<ItemsPedido> itemsPed = PedidoRepository.getInstance(getApplicationContext()).buscarItemsPedidoPorIdPedido(idPedido);
            int idItem = itemsPed.get(itemsPed.size()-1).getIdItem()+1;
            ip.setIdItem(idItem);
        }
        else{
            ip.setIdItem(1);
        }

        _ITEMS.add(ip);

        mAdapter = new PedidoViewAdapter(getApplicationContext(), _ITEMS, this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        agregarPlatoPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Necesario para Room
                List<Plato> platoList = PlatoRepository.getInstance().getListaPlatos();
                Plato p = platoList.get(getIntent().getExtras().getInt("posicion"));

                //insertar item en room
                List<ItemsPedido> items = new ArrayList<ItemsPedido>();
                items.addAll(PedidoRepository.getInstance(getApplicationContext()).buscarItemsPedidoPorIdPedido(idPedido));
                items.add(ip);
                pedido.setItemsPedido((ArrayList) items);

                if(idPedaux > 0){
                    PedidoRepository.getInstance(getApplicationContext()).actualizarPedido(pedido);
                }
                else{
                    PedidoRepository.getInstance(getApplicationContext()).crearPedido(pedido);
                }
                PedidoRepository.getInstance(getApplicationContext()).crearItemPedido(ip, pedido);
                Log.d("ROOM", "ITEM PEDIDO CREADO");

                Intent i1 = new Intent(getApplicationContext(), MostrarPlatosPedido.class);
                i1.putExtra("idPedido", idPedido);
                i1.putExtra("idPlato", p.getID());
                startActivity(i1);
            }
        });

        crearPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<ItemsPedido> itemsPed = new ArrayList<ItemsPedido>();
                PlatoRepository.getInstance().listarPlatos();
                itemsPed.addAll(PedidoRepository.getInstance(getApplicationContext()).buscarItemsPedidoPorIdPedido(idPedido));
                itemsPed.add(ip);
                if(idPedaux > 0){
                    PedidoRepository.getInstance(getApplicationContext()).actualizarPedido(pedido);
                }
                else{
                    PedidoRepository.getInstance(getApplicationContext()).crearPedido(pedido);
                }
                PedidoRepository.getInstance(getApplicationContext()).crearItemPedido(ip, pedido);

                enviarPedido.setEnabled(true);
                Intent i1 = new Intent(getApplicationContext(),MapsActivity.class);
                i1.putExtra("idPed", idPedido);
                startActivityForResult(i1, 1);
            }
        });

        enviarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<ItemsPedido> itemsPedidos = (ArrayList) PedidoRepository.getInstance(getApplicationContext()).buscarItemsPedidoPorIdPedido(idPedido);
                pedido.setItemsPedido(itemsPedidos);
                PedidoRepositoryServer.getInstance().crearPedido(pedido);
                Log.d("SERVER","PEDIDO ENVIADO");
                Intent i1 = new Intent(getApplicationContext(),Home.class);
                startActivity(i1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent i1){

        if(requestCode == 1 && resultCode == 1){
            idPedido = i1.getExtras().getInt("idPed");
            Pedido actual = PedidoRepository.getInstance(getApplicationContext()).buscarPedidoPorID(idPedido);

            actual.setLat(i1.getExtras().getDouble("latitud"));
            actual.setLng(i1.getExtras().getDouble("longitud"));
            PedidoRepository.getInstance(getApplicationContext()).actualizarPedido(actual);
            pedido = actual;
            Log.d("ROOM", "LAT: " + i1.getExtras().getDouble("latitud"));
            Log.d("ROOM", "LNG: " + i1.getExtras().getDouble("longitud"));
            Log.d("ROOM", "PEDIDO CREADO");
        }
        else {
            Log.d("Error", "Fallo onActivityResultMapas");
        }
    }

    public void showDialogEliminar(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.dialogoQuitarPlatoPedido).setTitle(R.string.tituloDialogo)
                .setPositiveButton("Si",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ItemsPedido itemBorrar = _ITEMS.get(position);
                                _ITEMS.remove(position);
                                Pedido pedido = PedidoRepository.getInstance(getApplicationContext()).buscarPedidoPorID(itemBorrar.getIdPedido());
                                List<ItemsPedido> items = PedidoRepository.getInstance(getApplicationContext()).buscarItemsPedidoPorIdPedido(pedido.getIdPedido());

                                if(_ITEMS.isEmpty()){
                                    PedidoRepository.getInstance(getApplicationContext()).borrarPedido(pedido);
                                    PedidoRepositoryServer.getInstance().borrarPedido(pedido);
                                }
                                else{
                                    PedidoRepositoryServer.getInstance().borrarItemPedido(pedido);
                                }
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

    public void edicionPedido(final int position){
        //VER QUE SE VA A PODER MODIFICAR
    }
}