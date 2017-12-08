package com.divaga.pescador;

/**
 * Creado por dakedroid el 07/12/17.
 */

public class Item {

    private  String  cantidad;
    private String titulo;
    private  String monto;
    private String opcion;

    public Item() {
    }

    public Item(String cantidad, String titulo, String monto, String opcion) {
        this.cantidad = cantidad;
        this.titulo = titulo;
        this.monto = monto;
        this.opcion = opcion;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }
}
