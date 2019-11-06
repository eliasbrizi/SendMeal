package com.B3B.sendmeal.dao;

import android.util.Log;

import com.B3B.sendmeal.dao.rest.PedidoRest;
import com.B3B.sendmeal.dao.rest.PlatoRest;
import com.B3B.sendmeal.domain.Pedido;
import com.B3B.sendmeal.domain.Plato;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PedidoRepositoryServer {

    private static PedidoRepositoryServer _INSTANCE;
    public static String _SERVER = "http://10.0.2.2:5000/";
    /*
    Retrofit
     */
    private Retrofit rf;
    private PedidoRest pedidoRest;
    /*

     */
    private List<Pedido> listaPedidos;

    private PedidoRepositoryServer(){}

    public static PedidoRepositoryServer getInstance(){
        if(_INSTANCE==null){
            _INSTANCE = new PedidoRepositoryServer();
            _INSTANCE.configurarRetrofit();
            _INSTANCE.listaPedidos = new ArrayList<>();
        }
        return _INSTANCE;
    }

    private void configurarRetrofit (){
        this.rf = new Retrofit.Builder()
                .baseUrl(_SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Log.d("INFO","INSTANCIA CREADA");
        this.pedidoRest = this.rf.create(PedidoRest.class);
    }

    public void crearPedido(Pedido pedido/*, final Handler h*/){
        Call<Pedido> llamada = this.pedidoRest.crearPedido(pedido);
        llamada.enqueue(new Callback<Pedido>() {
            @Override
            public void onResponse(Call<Pedido> call, Response<Pedido> response) {
                Log.d("APP_2","Despues que ejecuta"+ response.isSuccessful());
                Log.d("APP_2","COdigo"+ response.code());

                if(response.isSuccessful()){
                    Log.d("APP_2","EJECUTO");
                    //Agregaria el response body a la lista de pedidos si tuviere
                }
            }

            @Override
            public void onFailure(Call<Pedido> call, Throwable t) {
                Log.d("APP_2","ERROR "+t.getMessage());
            }
        });
    }

    public void listarPedidos(){

        Call<List<Pedido>> llamada = this.pedidoRest.listarTodos();
        llamada.enqueue(new Callback<List<Pedido>>() {
            @Override
            public void onResponse(Call<List<Pedido>> call, Response<List<Pedido>> response) {
                if(response.isSuccessful()){
                    listaPedidos.clear();
                    listaPedidos.addAll(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Pedido>> call, Throwable t) {
                Log.d("APP_2","fallo");
            }
        });
    }

    public List<Pedido> getListaPedidos(){
        return listaPedidos;
    }
}
