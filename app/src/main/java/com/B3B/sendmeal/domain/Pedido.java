package com.B3B.sendmeal.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Pedido {

    private int id;
    private LocalDateTime fecha;
    private int estado;
    private double lat;
    private double lng;
    private List<ItemsPedido> items;

    public void Pedido(){

    }
}
