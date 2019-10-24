package com.B3B.sendmeal.dao.room;

import androidx.room.Dao;
import androidx.room.Query;

import com.B3B.sendmeal.dao.PedidoEItems;
import com.B3B.sendmeal.domain.Pedido;

@Dao
public interface PedidoItemsDao {
    @Query("SELECT * FROM ITEMSPEDIDO WHERE idpedido = :p")
    PedidoEItems listarItemsdePedido(int p);

}
