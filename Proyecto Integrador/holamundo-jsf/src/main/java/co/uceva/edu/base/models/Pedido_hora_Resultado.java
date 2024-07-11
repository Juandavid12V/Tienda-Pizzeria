package co.uceva.edu.base.models;

import java.sql.Timestamp;

public class Pedido_hora_Resultado {



    private Long cantidad;
    private  Long tiempo_resultante;

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public Long getTiempo_resultante() {
        return tiempo_resultante;
    }

    public void setTiempo_resultante(Long tiempo_resultante) {
        this.tiempo_resultante = tiempo_resultante;
    }
}
