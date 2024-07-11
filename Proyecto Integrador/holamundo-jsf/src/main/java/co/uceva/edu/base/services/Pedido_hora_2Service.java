package co.uceva.edu.base.services;


import co.uceva.edu.base.models.Pedido_hora;
import co.uceva.edu.base.models.Pedido_hora_2;

import java.util.List;
import java.util.Optional;

public interface Pedido_hora_2Service {
    List<Pedido_hora_2> listar();

    Optional<Pedido_hora_2> porId(Long id);

    void guardar(Pedido_hora_2 pedido_hora_2);

    void eliminar(Long id);
}
