package co.uceva.edu.base.services;


import co.uceva.edu.base.models.Pedido_hora;
import co.uceva.edu.base.models.Pedido_hora_3;

import java.util.List;
import java.util.Optional;

public interface Pedido_hora_3Service {
    List<Pedido_hora_3> listar();

    Optional<Pedido_hora_3> porId(Long id);

    void guardar(Pedido_hora_3 pedido_hora_3);

    void eliminar(Long id);
}
