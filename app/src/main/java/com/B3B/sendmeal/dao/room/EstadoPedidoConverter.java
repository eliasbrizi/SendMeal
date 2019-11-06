package com.B3B.sendmeal.dao.room;

import androidx.room.TypeConverter;

import com.B3B.sendmeal.domain.EstadoPedido;

public class EstadoPedidoConverter {
    @TypeConverter
    public static EstadoPedido fromId(int id){
        switch (id){
            case 1: return EstadoPedido.PENDIENTE;
            case 2: return EstadoPedido.ENVIADO;
            case 3: return EstadoPedido.ACEPTADO;
            case 4: return EstadoPedido.RECHAZADO;
            case 5: return EstadoPedido.EN_PREPARACION;
            case 6: return EstadoPedido.EN_ENVIO;
            case 7: return EstadoPedido.ENTREGADO;
            case 8: return EstadoPedido.CANCELADO;
            default: return null;
        }
    }

    @TypeConverter
    public static int EstadoToId(EstadoPedido estadoPedido){
        if(estadoPedido == EstadoPedido.PENDIENTE){
            return 1;
        }
        else if(estadoPedido == EstadoPedido.ENVIADO){
            return 2;
        }
        else if(estadoPedido == EstadoPedido.ACEPTADO){
            return 3;
        }
        else if(estadoPedido == EstadoPedido.RECHAZADO){
            return 4;
        }
        else if(estadoPedido == EstadoPedido.EN_PREPARACION){
            return 5;
        }
        else if(estadoPedido == EstadoPedido.EN_ENVIO){
            return 6;
        }
        else if(estadoPedido == EstadoPedido.ENTREGADO){
            return 7;
        }
        else if(estadoPedido == EstadoPedido.CANCELADO){
            return 8;
        }
        else {
            return -1;
        }
    }
}
