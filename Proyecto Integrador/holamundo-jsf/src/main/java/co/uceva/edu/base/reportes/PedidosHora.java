package co.uceva.edu.base.reportes;

import java.sql.Timestamp;

public class PedidosHora {

    Long cantidad;
    Timestamp hora;

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public Timestamp getHora() {
        return hora;
    }

    public void setHora(Timestamp hora) {
        this.hora = hora;
    }


}