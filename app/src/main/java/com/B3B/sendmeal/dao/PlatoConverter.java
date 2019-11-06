package com.B3B.sendmeal.dao;

import androidx.room.TypeConverter;

import com.B3B.sendmeal.domain.Plato;

public class PlatoConverter {
    @TypeConverter
    public static Plato fromID(int id){
        if(id < 0){
            return null;
        }
        else{
            PlatoRepository.getInstance().findPlatoByID(id);
            return PlatoRepository.getInstance().getListaPlatos().get(0);
        }
    }

    @TypeConverter
    public static int platoToID(Plato p){
        if(p == null){
            return -1;
        }
        else{
            return p.getID();
        }
    }
}
