package com.B3B.sendmeal.dao;

import android.os.Message;

import com.B3B.sendmeal.dao.rest.PlatoRest;
import com.B3B.sendmeal.domain.Plato;


import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlatoRepository {

    private static PlatoRepository _INSTANCE;
    public static String _SERVER = "http://10.0.2.2:5000/";
    private List<Plato> listaPlatos;

    /*
    Retrofit
     */
    private Retrofit rf;
    private PlatoRest platoRest;

    /*
    Response variables
     */
    public static final int _ALTA_PLATO =1;
    public static final int _UPDATE_PLATO =2;
    public static final int _BORRADO_PLATO =3;
    public static final int _CONSULTA_PLATO =4;
    public static final int _ERROR_PLATO =9;

    private PlatoRepository(){}

    public static PlatoRepository getInstance(){
        if(_INSTANCE==null){
            _INSTANCE = new PlatoRepository();
            _INSTANCE.configurarRetrofit();
            _INSTANCE.listaPlatos = new ArrayList<>();
        }
        return _INSTANCE;
    }

    private void configurarRetrofit(){
        this.rf = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Log.d("APP_2","INSTANCIA CREADA");
        this.platoRest = this.rf.create(PlatoRest.class);
    }

    public void listarPlatos(/*final Handler h*/){

        Call<List<Plato>> llamada = this.platoRest.listarTodos();
        llamada.enqueue(new Callback<List<Plato>>() {
            @Override
            public void onResponse(Call<List<Plato>> call, Response<List<Plato>> response) {
                if(response.isSuccessful()){
                    listaPlatos.clear();
                    listaPlatos.addAll(response.body());
/*                    Message m = new Message();
                    m.arg1 = _CONSULTA_PLATO;
                    h.sendMessage(m);*/
                }
            }

            @Override
            public void onFailure(Call<List<Plato>> call, Throwable t) {
/*                Message m = new Message();
                m.arg1 = _ERROR_PLATO;
                h.sendMessage(m);*/
                Log.d("APP_2","fallo");
            }
        });
    }

    public void crearPlato(Plato plato/*, final Handler h*/){
        Call<Plato> llamada = this.platoRest.crearPlato(plato);
        llamada.enqueue(new Callback<Plato>() {
            @Override
            public void onResponse(Call<Plato> call, Response<Plato> response) {
                Log.d("APP_2","Despues que ejecuta"+ response.isSuccessful());
                Log.d("APP_2","COdigo"+ response.code());

                if(response.isSuccessful()){
                    Log.d("APP_2","EJECUTO");
                    listaPlatos.add(response.body());
                    /*Message m = new Message();
                    m.arg1 = _ALTA_OBRA;
                    h.sendMessage(m);*/
                }
            }

            @Override
            public void onFailure(Call<Plato> call, Throwable t) {
                Log.d("APP_2","ERROR "+t.getMessage());
                /*Message m = new Message();
                m.arg1 = _ERROR_OBRA;
                h.sendMessage(m);*/
            }
        });
    }




/*
    public void actualizarObra(final Obra o, final Handler h){
        Call<Obra> llamada = this.platoRest.actualizar(o.getId(),o);
        llamada.enqueue(new Callback<Obra>() {
            @Override
            public void onResponse(Call<Obra> call, Response<Obra> response) {
                Log.d("APP_2","Despues que ejecuta"+ response.isSuccessful());
                Log.d("APP_2","COdigo"+ response.code());

                if(response.isSuccessful()){
                    Log.d("APP_2","EJECUTO");
                    listaObras.remove(o);
                    listaObras.add(response.body());
                    Message m = new Message();
                    m.arg1 = _UPDATE_OBRA;
                    h.sendMessage(m);
                }
            }

            @Override
            public void onFailure(Call<Obra> call, Throwable t) {
                Log.d("APP_2","ERROR "+t.getMessage());
                Message m = new Message();
                m.arg1 = _ERROR_OBRA;
                h.sendMessage(m);
            }
        });
    }

    public void crearObra(Obra o, final Handler h){
        Call<Obra> llamada = this.platoRest.crear(o);
        llamada.enqueue(new Callback<Obra>() {
            @Override
            public void onResponse(Call<Obra> call, Response<Obra> response) {
                Log.d("APP_2","Despues que ejecuta"+ response.isSuccessful());
                Log.d("APP_2","COdigo"+ response.code());

                if(response.isSuccessful()){
                    Log.d("APP_2","EJECUTO");
                    listaObras.add(response.body());
                    Message m = new Message();
                    m.arg1 = _ALTA_OBRA;
                    h.sendMessage(m);
                }
            }

            @Override
            public void onFailure(Call<Obra> call, Throwable t) {
                Log.d("APP_2","ERROR "+t.getMessage());
                Message m = new Message();
                m.arg1 = _ERROR_OBRA;
                h.sendMessage(m);
            }
        });
    }

    public void listarObra(final Handler h){
        Call<List<Obra>> llamada = this.platoRest.buscarTodas();
        llamada.enqueue(new Callback<List<Obra>>() {
            @Override
            public void onResponse(Call<List<Obra>> call, Response<List<Obra>> response) {
                if(response.isSuccessful()){
                    listaObras.clear();
                    listaObras.addAll(response.body());
                    Message m = new Message();
                    m.arg1 = _CONSULTA_OBRA;
                    h.sendMessage(m);
                }
            }

            @Override
            public void onFailure(Call<List<Obra>> call, Throwable t) {
                Message m = new Message();
                m.arg1 = _ERROR_OBRA;
                h.sendMessage(m);
            }
        });
    }

    public void borrarObra(final Obra o, final Handler h){
        Call<Void> llamada = this.platoRest.borrar(o.getId());
        llamada.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("APP_2","Despues que ejecuta"+ response.isSuccessful());
                Log.d("APP_2","COdigo"+ response.code());

                if(response.isSuccessful()){
                    Log.d("APP_2","EJECUTO");
                    for(Obra o : listaObras){
                        Log.d("APP_2","Obra "+o.getId());
                    }
                    Log.d("APP_2","BORRA Obra "+o.getId());
                    listaObras.remove(o);
                    for(Obra o : listaObras){
                        Log.d("APP_2","Obra "+o.getId());
                    }
                    Message m = new Message();
                    m.arg1 = _BORRADO_OBRA;
                    h.sendMessage(m);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("APP_2","ERROR "+t.getMessage());
                Message m = new Message();
                m.arg1 = _ERROR_OBRA;
                h.sendMessage(m);
            }
        });
    }*/
    public List<Plato> getListaPlatos() {
        return listaPlatos;
    }
}
