package com.B3B.sendmeal.dao.rest;

import com.B3B.sendmeal.domain.EstadoPedido;
import com.B3B.sendmeal.domain.Pedido;

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

    @GET("pedidos/")
    Call<List<Pedido>> getPedido(@Query("idPedido") Integer id);

    @GET("pedidos/")
    Call<List<Pedido>> listarPedidosEnEstado(@Query("estadoPedido") EstadoPedido estadoPedido);

    @PUT("pedidos/{id}")
    Call<Pedido> actualizarPedido(@Path("id") Integer id, @Body Pedido pedido);

    @DELETE("pedidos/{id}")
    Call<Void> eliminarPedido(@Path("id") Integer id);
}
