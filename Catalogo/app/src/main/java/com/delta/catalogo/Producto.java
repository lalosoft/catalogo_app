package com.delta.catalogo;

public class Producto
{
    private String imagen;
    private String clave;
    private String nombre;
    private String precio;
    private String lab;
    private String present;

    public Producto(String imagen, String clave, String nombre, String precio, String lab, String present) {
        this.imagen = imagen;
        this.clave = clave;
        this.nombre = nombre;
        this.precio = precio;
        this.lab = lab;
        this.present = present;
    }

    public String getImagen() {
        return imagen;
    }

    public String getClave() {
        return clave;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public String getLab(){ return  lab; }

    public String getPresent() { return  present; }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public void setLab(String lab) { this.lab = lab; }

    public void setPresent(String present) { this.present = present; }
}
