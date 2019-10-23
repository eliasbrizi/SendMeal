package com.B3B.sendmeal.dao.room;

import android.content.ClipData;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.B3B.sendmeal.domain.ItemsPedido;
import com.B3B.sendmeal.domain.Pedido;

@Dao
public interface ItemsPedidoDao {

    @Insert
    public void insertarItemsPedido(ItemsPedido ip, Pedido p);

    @Update
    public void actualizarItemsPedido(ItemsPedido ip, Pedido p);

    @Delete
    public void borrarItemsPedido(ItemsPedido ip, Pedido p);

    @Query("SELECT * FROM ITEMSPEDIDO WHERE pedido = :id")
    public ItemsPedido getItemsPedidoByID(int id);

}
