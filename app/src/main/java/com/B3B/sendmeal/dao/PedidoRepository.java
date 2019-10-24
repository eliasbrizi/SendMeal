package com.B3B.sendmeal.dao;

import android.content.Context;

import androidx.room.Room;

import com.B3B.sendmeal.dao.room.ItemsPedidoDao;
import com.B3B.sendmeal.dao.room.PedidoDao;
import com.B3B.sendmeal.database.BaseDatosSendMeal;
import com.B3B.sendmeal.domain.ItemsPedido;
import com.B3B.sendmeal.domain.Pedido;

import java.util.List;

public class PedidoRepository {

    private static PedidoRepository _REPO = null;
    private PedidoDao pedidoDao;
    private ItemsPedidoDao itemsPedidoDao;

    private PedidoRepository(Context ctx){
        BaseDatosSendMeal db = Room.databaseBuilder(ctx, BaseDatosSendMeal.class, "DB-SM")
                .fallbackToDestructiveMigration().build();

        pedidoDao = db.pedidoDao();
        itemsPedidoDao = db.itemsPedidoDao();
    }

    public static PedidoRepository getInstance(Context ctx){
        if(_REPO == null){
            _REPO = new PedidoRepository(ctx);
        }
        return _REPO;
    }

    public void crearPedido(Pedido p){
        pedidoDao.insertarPedido(p);
    }

    public void actualizarPedido(Pedido p){
        pedidoDao.actualizarPedido(p);
    }

    public void borrarPedido(Pedido p){
        pedidoDao.borrarPedido(p);
    }

    public Pedido buscarPedidoPorID(int id){
        return pedidoDao.getPedidoByID(id);
    }

    public void crearItemPedido(ItemsPedido ip, Pedido p){
        itemsPedidoDao.insertarItemsPedido(ip, p);
    }

    public void actualizarItemPedido(ItemsPedido ip, Pedido p){
        itemsPedidoDao.actualizarItemsPedido(ip, p);
    }

    public void borrarItemPedido(ItemsPedido ip, Pedido p){
        itemsPedidoDao.borrarItemsPedido(ip, p);
    }


    /* DESCOMENTAAAAAAAR DESPUES
    public List<ItemsPedido> buscarItemsPedidoPorID(int id){
        return itemsPedidoDao.getItemsPedidoByID(id);
    }

    */
}
