package com.B3B.sendmeal.database;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.B3B.sendmeal.dao.room.DAO;
import com.B3B.sendmeal.dao.room.ItemsPedidoDao;
import com.B3B.sendmeal.dao.room.PedidoDao;
import com.B3B.sendmeal.domain.ItemsPedido;
import com.B3B.sendmeal.domain.Pedido;

@Database(entities = {Pedido.class, ItemsPedido.class}, version = 3)
public abstract class BaseDatosSendMeal extends RoomDatabase {

    public abstract PedidoDao pedidoDao();
    public abstract ItemsPedidoDao itemsPedidoDao();

}
