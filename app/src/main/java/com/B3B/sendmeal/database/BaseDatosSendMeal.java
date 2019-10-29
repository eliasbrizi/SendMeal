package com.B3B.sendmeal.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.B3B.sendmeal.dao.FechaConverter;
import com.B3B.sendmeal.dao.PlatoConverter;
import com.B3B.sendmeal.dao.room.ItemsPedidoDao;
import com.B3B.sendmeal.dao.room.PedidoDao;
import com.B3B.sendmeal.domain.ItemsPedido;
import com.B3B.sendmeal.domain.Pedido;

@Database(entities = {Pedido.class, ItemsPedido.class}, version = 4, exportSchema = false)
@TypeConverters({FechaConverter.class, PlatoConverter.class})
public abstract class BaseDatosSendMeal extends RoomDatabase {

    public abstract PedidoDao pedidoDao();
    public abstract ItemsPedidoDao itemsPedidoDao();

}
