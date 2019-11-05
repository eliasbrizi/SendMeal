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
            /*
            PlatoRepository.getInstance().buscarPlatoPorID(id);
            return PlatoRepository.getInstance().getPlato();

             */
            Plato p = new Plato();
            p.setNombre("Pizza");
            p.setCalorias(4000);
            p.setPrecio(300.00);
            p.setID(2);
            p.setDescripcion("Calabresa");
            p.setOferta(false);
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
