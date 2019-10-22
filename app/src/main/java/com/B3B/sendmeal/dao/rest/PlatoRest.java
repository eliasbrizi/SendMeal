package com.B3B.sendmeal.dao.rest;

import com.B3B.sendmeal.domain.Plato;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface PlatoRest {
    @GET("platos/") Call<List<Plato>> listarTodos();

    @GET("platos/") Call<Plato> findPlatoByID(int id);

    @POST("platos/") Call<Plato> crearPlato(@Body Plato p);

    @PATCH("platos/") Call<Plato> actualizarNombrePlato(String nombre);

    @PATCH("platos/") Call<Plato> actualizarDescripcionPlato(String descripcion);

    @DELETE("platos/") Call<Plato> eliminarPlato(int id);
}
