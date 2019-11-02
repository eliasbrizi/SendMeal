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
    Call<Pedido> crearPedido(@Body Pedido p);

    @GET("pedidos/")
    Call<List<Pedido>> listarTodos();

    @GET("pedidos/{id}")
    Call<Pedido> buscarPlatoPorID(@Path("id") Integer id);

    @PUT("pedidos/{ID}")
    Call<Pedido> actualizarPedido(@Path("ID") Integer id, @Body Pedido pedido);

    @DELETE("pedidos/{ID}")
    Call<Void> eliminarPedido(@Path("ID") Integer id);
}
