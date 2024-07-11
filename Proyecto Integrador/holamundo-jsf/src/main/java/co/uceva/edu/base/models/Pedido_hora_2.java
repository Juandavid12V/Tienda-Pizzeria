package co.uceva.edu.base.models;

import java.sql.Timestamp;

public class Pedido_hora_2 {

    private Long id;
    private Timestamp fecha_hora;
    private double precioT;

    public Timestamp getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(Timestamp fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

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
