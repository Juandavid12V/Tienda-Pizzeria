package co.uceva.edu.base.services;


import co.uceva.edu.base.models.Pedido_hora;

import java.util.List;
import java.util.Optional;

public interface Pedido_horaService {
    List<Pedido_hora> listar();

    Optional<Pedido_hora> porId(Long id);

    void guardar(Pedido_hora pedido_hora);

    void eliminar(Long id);
}
