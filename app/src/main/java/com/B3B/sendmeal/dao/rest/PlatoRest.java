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

    @GET("/platos?id={ID}")
    Call<Plato> buscarPlatoPorID(@Path("ID") Integer id);

    @GET("/platos?nombre={nomb}")
    Call<List<Plato>> buscarPlatoPorNombre(@Path("nomb") String nombre);

    @GET("/platos?precio_gte={minimo}&precio_lte={maximo}")
    Call<List<Plato>> buscarPlatoPorPrecios(@Path("minimo") Integer precioMinimo, @Path("maximo") Integer precioMaximo);

    @PUT("platos/{ID}")
    Call<Plato> actualizarPlato(@Path("ID") Integer id, @Body Plato plato);

    @DELETE("platos/{ID}")
    Call<Void> eliminarPlato(@Path("ID") Integer id);
}
