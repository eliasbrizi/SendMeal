package com.B3B.sendmeal.dao;

import androidx.room.TypeConverter;

import java.util.Date;

public class FechaConverter {
    @TypeConverter
    public static Date fromTime(Long value){
        if(value == null){
            return null;
        }
        else{
            return new Date(value);
        }
    }

    @TypeConverter
    public static Long dateToTimeStamp(Date fecha){
        if(fecha == null){
            return null;
        }
        else{
            return fecha.getTime();
        }
    }
}
