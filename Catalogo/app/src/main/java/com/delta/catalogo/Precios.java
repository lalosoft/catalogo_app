package com.delta.catalogo;

public class Precios
{
    private int precio_tope;
    private int precio_inicio;
    private String cadena_precios;

    public Precios(int precio_tope, int precio_inicio, String cadena_precios) {
        this.precio_tope = precio_tope;
        this.precio_inicio = precio_inicio;
        this.cadena_precios = cadena_precios;
    }

    public int getPrecio_tope() {
        return precio_tope;
    }

    public int getPrecio_inicio() {
        return precio_inicio;
    }

    public String getCadena_precios() {
        return cadena_precios;
    }

    public void setPrecio_tope(int precio_tope) {
        this.precio_tope = precio_tope;
    }

    public void setPrecio_inicio(int precio_inicio) {
        this.precio_inicio = precio_inicio;
    }

    public void setCadena_precios(String cadena_precios) {
        this.cadena_precios = cadena_precios;
    }
}
