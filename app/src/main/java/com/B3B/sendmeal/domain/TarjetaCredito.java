package com.B3B.sendmeal.domain;

import java.util.Objects;

public class TarjetaCredito {
    private Integer id;
    private String numero;
    private Integer digitoVericicacion;
    private String vencimiento;

    @Override
    public String toString() {
        return "TarjetaCredito{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", digitoVericicacion=" + digitoVericicacion +
                ", vencimiento='" + vencimiento + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TarjetaCredito that = (TarjetaCredito) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(numero, that.numero) &&
                Objects.equals(digitoVericicacion, that.digitoVericicacion) &&
                Objects.equals(vencimiento, that.vencimiento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numero, digitoVericicacion, vencimiento);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Integer getDigitoVericicacion() {
        return digitoVericicacion;
    }

    public void setDigitoVericicacion(Integer digitoVericicacion) {
        this.digitoVericicacion = digitoVericicacion;
    }

    public String getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(String vencimiento) {
        this.vencimiento = vencimiento;
    }
}
