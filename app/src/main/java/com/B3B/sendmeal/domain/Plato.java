package com.B3B.sendmeal.domain;

public class Plato {

    private Integer ID;
    private Integer calorias;
    private Double precio;
    private String nombre;
    private String descripcion;

    /**
     * Crea un plato con
     * @param id
     * @param cal calorias
     * @param prec precio
     * @param nombr nombre
     * @param descr descripcion
     */
    public Plato(Integer id, Integer cal, Double prec,String nombr, String descr){
        ID = id;
        calorias = cal;
        precio = prec;
        nombre = nombr;
        descripcion = descr;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getCalorias() {
        return calorias;
    }

    public void setCalorias(Integer calorias) {
        this.calorias = calorias;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
