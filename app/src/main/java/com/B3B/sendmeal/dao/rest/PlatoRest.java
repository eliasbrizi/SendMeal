package com.B3B.sendmeal.dao.rest;

import com.B3B.sendmeal.domain.Plato;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PlatoRest {
    @GET("platos/") Call<List<Plato>> listarTodos();

    @POST("platos/") Call<Plato> crearPlato(@Body Plato p);
}
