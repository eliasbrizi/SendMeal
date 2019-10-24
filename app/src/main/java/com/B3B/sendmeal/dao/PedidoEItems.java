package com.B3B.sendmeal.dao;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.B3B.sendmeal.domain.ItemsPedido;
import com.B3B.sendmeal.domain.Pedido;

import java.util.List;

public class PedidoEItems {
    @Embedded
    public Pedido pedido;

    @Relation(parentColumn = "idpedido", entityColumn = "iditem", entity = ItemsPedido.class)
    public List<ItemsPedido> items;
}
