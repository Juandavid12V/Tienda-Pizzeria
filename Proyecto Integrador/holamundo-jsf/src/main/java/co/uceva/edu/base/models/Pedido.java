package co.uceva.edu.base.models;

import java.sql.Timestamp;
import java.util.Date;

public class Pedido {

    private Long id;
    private Timestamp fecha_hora;
    private double precioT;
    private long id_tienda;

    public long getId_tienda() {
        return id_tienda;
    }

    public void setId_tienda(long id_tienda) {
        this.id_tienda = id_tienda;
    }


    public Timestamp getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(Timestamp fecha_hora) {this.fecha_hora = fecha_hora;}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrecioT() {
        return precioT;
    }

    public void setPrecioT(double precioT) {
        this.precioT = precioT;
    }

}
