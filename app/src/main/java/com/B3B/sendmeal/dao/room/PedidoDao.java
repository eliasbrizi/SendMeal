package com.B3B.sendmeal.dao.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.B3B.sendmeal.domain.Pedido;

@Dao
public interface PedidoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertarPedido(Pedido p);

    @Update
    public void actualizarPedido(Pedido p);

    @Delete
    public void borrarPedido(Pedido p);

    @Query("SELECT * FROM PEDIDO WHERE idpedido = :id")
    public Pedido getPedidoByID(int id);
}
