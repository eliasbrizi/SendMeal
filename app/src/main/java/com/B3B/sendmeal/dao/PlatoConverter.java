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
            Plato pl = PlatoRepository.getInstance().buscarPlatoPorID(id);
            return pl;
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
