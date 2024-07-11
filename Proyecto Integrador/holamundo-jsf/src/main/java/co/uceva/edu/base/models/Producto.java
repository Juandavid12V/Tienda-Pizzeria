package co.uceva.edu.base.models;

import java.time.LocalDate;

public class Producto {

    private Long id;
    private String nombre;
    private String descripcion;
    private String imagen;
    private double precio;
    private Long id_pedido;

    public Long getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(Long id_pedido) {
        this.id_pedido = id_pedido;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getImagenShow() {

        if(imagen!=null) {
            String[] fotoArray = imagen.split("\\\\");
            return fotoArray[fotoArray.length-1];
        }else{
            return "";
        }

    }

}
