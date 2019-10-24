package com.B3B.sendmeal.domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

//@Entity(tableName = "ITEMSPEDIDO", foreignKeys = @ForeignKey(entity = Pedido.class, parentColumns = "idpedido", childColumns = "pedido"))
@Entity(tableName = "ITEMSPEDIDO")
public class ItemsPedido {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "iditem")
    private int idItem;
    @Ignore //No debe ignorarse
    private Pedido pedido;
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

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
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
