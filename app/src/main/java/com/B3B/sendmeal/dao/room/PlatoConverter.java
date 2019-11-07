package com.B3B.sendmeal.dao.room;

import androidx.room.TypeConverter;

import com.B3B.sendmeal.dao.PlatoRepository;
import com.B3B.sendmeal.domain.Plato;

import java.util.ArrayList;

public class PlatoConverter {
    @TypeConverter
    public static Plato fromID(int id){
        ArrayList<Plato> platos = (ArrayList) PlatoRepository.getInstance().getListaPlatos();
        Plato p = new Plato();
        for(Plato plato : platos){
            if(plato.getID() == id){
                p = plato;
            }
        }
        return p;
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
