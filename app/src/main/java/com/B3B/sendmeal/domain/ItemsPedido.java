package com.B3B.sendmeal.domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;

@Entity(tableName = "ITEMSPEDIDO", primaryKeys = {"iditem","idpedido"},
        foreignKeys = @ForeignKey(entity = Pedido.class, parentColumns = "idpedido", childColumns = "idpedido",
                onDelete = ForeignKey.NO_ACTION), indices = {@Index("idpedido")})
public class ItemsPedido {

    @NonNull
    @ColumnInfo(name = "iditem")
    private int idItem;
    @ColumnInfo(name = "idpedido")
    private int idPedido;
    @Ignore
    private Plato platoItem;
    @ColumnInfo(name = "cantidad")
    private int cantidad;
    @ColumnInfo(name = "precio")
    private double precio;

    public void ItemsPedido(){

    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Plato getPlatoItem() {
        return platoItem;
    }

    public void setPlatoItem(Plato platoItem) {
        this.platoItem = platoItem;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
