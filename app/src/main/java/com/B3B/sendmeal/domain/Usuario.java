package com.B3B.sendmeal.domain;

import java.util.Objects;

public class Usuario {

    private Integer id;
    private String nombre;
    private String mail;
    private String clave;
    private Boolean notificarMail;
    private Double credito;

    private TarjetaCredito tarjetaAsociada;
    private CuentaBancaria cuentaAsociada;
    private TipoCuenta tipoDeCuenta;

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", mail='" + mail + '\'' +
                ", clave='" + clave + '\'' +
                ", notificarMail=" + notificarMail +
                ", credito=" + credito +
                ", tarjetaAsociada=" + tarjetaAsociada +
                ", cuentaAsociada=" + cuentaAsociada +
                ", tipoDeCuenta=" + tipoDeCuenta +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id.equals(usuario.id) &&
                Objects.equals(nombre, usuario.nombre) &&
                Objects.equals(mail, usuario.mail) &&
                Objects.equals(clave, usuario.clave) &&
                Objects.equals(notificarMail, usuario.notificarMail) &&
                Objects.equals(credito, usuario.credito) &&
                Objects.equals(tarjetaAsociada, usuario.tarjetaAsociada) &&
                Objects.equals(cuentaAsociada, usuario.cuentaAsociada) &&
                tipoDeCuenta == usuario.tipoDeCuenta;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, mail, clave, notificarMail, credito, tarjetaAsociada, cuentaAsociada, tipoDeCuenta);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Boolean getNotificarMail() {
        return notificarMail;
    }

    public void setNotificarMail(Boolean notificarMail) {
        this.notificarMail = notificarMail;
    }

    public Double getCredito() {
        return credito;
    }

    public void setCredito(Double credito) {
        this.credito = credito;
    }

    public TarjetaCredito getTarjetaAsociada() {
        return tarjetaAsociada;
    }

    public void setTarjetaAsociada(TarjetaCredito tarjetaAsociada) {
        this.tarjetaAsociada = tarjetaAsociada;
    }

    public CuentaBancaria getCuentaAsociada() {
        return cuentaAsociada;
    }

    public void setCuentaAsociada(CuentaBancaria cuentaAsociada) {
        this.cuentaAsociada = cuentaAsociada;
    }

    public TipoCuenta getTipoDeCuenta() {
        return tipoDeCuenta;
    }

    public void setTipoDeCuenta(TipoCuenta tipoDeCuenta) {
        this.tipoDeCuenta = tipoDeCuenta;
    }
}
