package com.B3B.sendmeal.domain;

public class Plato {

    private Integer id;
    private Integer calorias;
    private Double precio;
    private String nombre;
    private String descripcion;
    private  Boolean oferta;

    /**
     * Crea un plato con
     * @param i
     * @param cal calorias
     * @param prec precio
     * @param nombr nombre
     * @param descr descripcion
     */
    public Plato(Integer i, Integer cal, Double prec,String nombr, String descr){
        id = i;
        calorias = cal;
        precio = prec;
        nombre = nombr;
        descripcion = descr;
        oferta = false;
    }

    public Integer getID() {
        return id;
    }

    public void setID(Integer ID) {
        this.id = ID;
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


    public Boolean getOferta() {
        return oferta;
    }

    public void setOferta(Boolean oferta) {
        this.oferta = oferta;
    }
}
