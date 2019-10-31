package com.B3B.sendmeal.dao;

import com.B3B.sendmeal.dao.rest.PlatoRest;
import com.B3B.sendmeal.domain.Plato;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlatoRepository{

    private static PlatoRepository _INSTANCE;
    public static String _SERVER = "http://10.0.2.2:5000/";
    private List<Plato> listaPlatos;
    private Plato plato;

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

    private void configurarRetrofit (){
        this.rf = new Retrofit.Builder()
                .baseUrl(_SERVER)
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
                }
            }

            @Override
            public void onFailure(Call<List<Plato>> call, Throwable t) {
                Log.d("APP_2","fallo");
            }
        });
    }

    public void crearPlato (Plato plato/*, final Handler h*/){
        Call<Plato> llamada = this.platoRest.crearPlato(plato);
        llamada.enqueue(new Callback<Plato>() {
            @Override
            public void onResponse(Call<Plato> call, Response<Plato> response) {
                Log.d("APP_2","Despues que ejecuta"+ response.isSuccessful());
                Log.d("APP_2","COdigo"+ response.code());

                if(response.isSuccessful()){
                    Log.d("APP_2","EJECUTO");
                    listaPlatos.add(response.body());
                }
            }

            @Override
            public void onFailure(Call<Plato> call, Throwable t) {
                Log.d("APP_2","ERROR "+t.getMessage());
            }
        });
    }

    public Plato buscarPlatoPorID(int id){
        Call<Plato> llamada = this.platoRest.buscarPlatoPorID(id);
        llamada.enqueue(new Callback<Plato>() {
            @Override
            public void onResponse(Call<Plato> call, Response<Plato> response) {
                if(response.isSuccessful()){
                    plato = response.body();
                }
            }

            @Override
            public void onFailure(Call<Plato> call, Throwable t) {
                Log.d("APP_2","fallo");
            }
        });
        return plato;
    }

    public ArrayList<Plato> buscarPlatoPorNombre(String nombre){
        Call<List<Plato>> llamada = this.platoRest.buscarPlatoPorNombre(nombre);
        final ArrayList<Plato> listaNombrePlato = new ArrayList<>();
        llamada.enqueue(new Callback<List<Plato>>() {
            @Override
            public void onResponse(Call<List<Plato>> call, Response<List<Plato>> response) {
                if(response.isSuccessful()){
                    listaNombrePlato.clear();
                    listaNombrePlato.addAll(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Plato>> call, Throwable t) {
                Log.d("APP_2","fallo");
            }
        });
        return listaNombrePlato;
    }

    public List<Plato> getListaPlatos() {
        return listaPlatos;
    }

    public void borrarPlato (final Plato plato/*, final Handler h*/){
        Call<Void> llamada = this.platoRest.eliminarPlato(plato.getID());
        llamada.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("APP_2","Despues que ejecuta"+ response.isSuccessful());
                Log.d("APP_2","COdigo"+ response.code());

                if(response.isSuccessful()){
                    Log.d("APP_2","EJECUTO");
                    for(Plato o : listaPlatos){
                        Log.d("APP_2","Obra "+o.getID());
                    }
                    Log.d("APP_2","BORRA Obra "+plato.getID());
                    listaPlatos.remove(plato);
                    for(Plato o : listaPlatos){
                        Log.d("APP_2","Obra "+o.getID());
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("APP_2","ERROR "+t.getMessage());
            }
        });
    }
}
