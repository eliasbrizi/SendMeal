package com.B3B.sendmeal.dao;

import android.util.Log;

import androidx.room.TypeConverter;

import com.B3B.sendmeal.domain.Plato;

public class PlatoConverter {
    @TypeConverter
    public static Plato fromID(int id){
        PlatoRepository.getInstance().buscarPlatoPorID(id);
        Plato p = PlatoRepository.getInstance().getPlato();
        if(id < 0){
            return null;
        }
        else{
            if(p == null){
                Log.d("PLATO", "NULL");
            }
            else {
                Log.d("PLATO", p.getNombre());
            }
            return p;
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
