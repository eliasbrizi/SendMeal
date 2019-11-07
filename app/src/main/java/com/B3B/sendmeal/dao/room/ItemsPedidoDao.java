package com.B3B.sendmeal.dao.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.RoomWarnings;
import androidx.room.Update;

import com.B3B.sendmeal.domain.ItemsPedido;
import com.B3B.sendmeal.domain.Pedido;

import java.util.List;

@Dao
public interface ItemsPedidoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertarItemsPedido(ItemsPedido ip, Pedido p);

    @Update
    void actualizarItemsPedido(ItemsPedido ip, Pedido p);

    @Delete
    void borrarItemsPedido(ItemsPedido ip, Pedido p);

    @Query("SELECT * FROM ITEMSPEDIDO WHERE idpedido = :id")
    List<ItemsPedido> getItemsPedidoByIdPedido(int id);

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM ITEMSPEDIDO, PEDIDO WHERE ITEMSPEDIDO.idpedido = PEDIDO.idpedido AND PEDIDO.idpedido = :idped AND platoItem = :idPlato")
    ItemsPedido getItemPedidoByPlato(int idPlato, int idped);

}
