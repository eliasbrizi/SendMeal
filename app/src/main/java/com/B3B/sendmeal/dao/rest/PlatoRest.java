package com.B3B.sendmeal.dao.rest;

import com.B3B.sendmeal.domain.Plato;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PlatoRest {

    @POST("platos/")
    Call<Plato> crearPlato(@Body Plato p);

    @GET("platos/")
    Call<List<Plato>> listarTodos();

    @GET("platos/{ID}")
    Call<Plato> buscarPlatoPorID(@Path("ID") Integer id);

    @GET("platos/{nombre}")
    Call<List<Plato>> buscarPlatoPorNombre(@Path("nombre") String nombre);

    @GET("platos/{nombre}")
    Call<List<Plato>> buscarPlatoPorPrecios(@Path("nombre") String nombre);

    @PUT("platos/{ID}")
    Call<Plato> actualizarPlato(@Path("ID") Integer id, @Body Plato plato);

    @DELETE("platos/{ID}")
    Call<Void> eliminarPlato(@Path("ID") Integer id);
}
