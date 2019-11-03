package com.B3B.sendmeal.domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.B3B.sendmeal.dao.FechaConverter;

import java.util.ArrayList;
import java.util.Date;

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
    private ArrayList<ItemsPedido> itemsPedido;

    public void Pedido(){
        itemsPedido = new ArrayList<>();
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

    public EstadoPedido getEstadoPedidoEnum() {
        return EstadoPedido.values()[estadoPedido];
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

    public ArrayList<ItemsPedido> getItemsPedido() {
        return itemsPedido;
    }

    public void setItemsPedido(ArrayList<ItemsPedido> itemsPedido) {
        this.itemsPedido = itemsPedido;
    }

    public Double getPrecio(){
        Double result=0.0;
        for (ItemsPedido i: itemsPedido){
            result+=i.getPrecio();
        } return result;
    }
}
