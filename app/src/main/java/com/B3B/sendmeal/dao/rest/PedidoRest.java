package com.B3B.sendmeal.dao.rest;

import com.B3B.sendmeal.domain.Pedido;
import com.B3B.sendmeal.domain.Plato;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PedidoRest {

    @POST("pedidos/")
    Call<Plato> crearPedido(@Body Pedido p);

    @GET("pedidos/")
    Call<List<Plato>> listarTodos();

    @GET("pedidos/{id}")
    Call<Plato> buscarPlatoPorID(@Path("id") Integer id);

    @PUT("pedidos/{ID}")
    Call<Plato> actualizarPedido(@Path("ID") Integer id, @Body Pedido plato);

    @DELETE("pedidos/{ID}")
    Call<Void> eliminarPedido(@Path("ID") Integer id);
}
