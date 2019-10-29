package com.B3B.sendmeal.domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.B3B.sendmeal.dao.FechaConverter;

import java.util.Date;
import java.util.List;

@Entity(tableName = "PEDIDO")
public class Pedido {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "idpedido")
    private int idPedido;
    @ColumnInfo(name = "estadopedido")
    private int estadoPedido;
    @ColumnInfo(name = "latitud")
    private double lat;
    @ColumnInfo(name = "longitud")
    private double lng;
    @TypeConverters(FechaConverter.class)
    private Date fechaPedido;
    @Ignore
    private List<ItemsPedido> items;

    public void Pedido(){

    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public int getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(int estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public List<ItemsPedido> getItems() {
        return items;
    }

    public void setItems(List<ItemsPedido> items) {
        this.items = items;
    }
}
