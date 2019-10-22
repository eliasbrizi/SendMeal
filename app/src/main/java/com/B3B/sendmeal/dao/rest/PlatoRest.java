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

    @GET("platos/{id}")
    Call<Plato> buscarPlatoPorID(@Path("id") Integer id);

    @PUT("platos/{id}")
    Call<Plato> actualizarPlato(@Path("id") Integer id, @Body Plato plato);

    @DELETE("platos/{id}")
    Call<Plato> eliminarPlato(@Path("id") Integer id);

}
