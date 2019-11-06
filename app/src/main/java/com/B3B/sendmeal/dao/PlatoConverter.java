package com.B3B.sendmeal.dao;

import android.util.Log;

import androidx.room.TypeConverter;

import com.B3B.sendmeal.domain.Plato;

public class PlatoConverter {
    @TypeConverter
    public static Plato fromID(int id){
        if(id < 0){
            return null;
        }
        else{
            PlatoRepository.getInstance().buscarPlatoPorID(id);
            return PlatoRepository.getInstance().getPlato();
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
