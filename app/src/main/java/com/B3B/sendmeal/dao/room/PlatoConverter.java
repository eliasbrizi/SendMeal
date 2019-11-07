package com.B3B.sendmeal.dao.room;

import android.content.Intent;
import android.util.Log;

import androidx.room.TypeConverter;

import com.B3B.sendmeal.dao.PlatoRepository;
import com.B3B.sendmeal.domain.Plato;

public class PlatoConverter {
    @TypeConverter
    public static Plato fromID(int id){
        PlatoRepository.getInstance().buscarPlatoPorID(id);
        Plato p = PlatoRepository.getInstance().getPlato(); //aca esta el error de lista siempre repite
        if(id < 0){
            return null;
        }
        else{
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
