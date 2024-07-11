package co.uceva.edu.base.services;


import co.uceva.edu.base.models.Pedido_hora_Resultado;
import co.uceva.edu.base.models.Pedido_hora_ResultadoF;

import java.util.List;
import java.util.Optional;

public interface Pedido_hora_ResultadoFService {
    List<Pedido_hora_ResultadoF> listar();

    Optional<Pedido_hora_ResultadoF> porId(Long id);

    void guardar(Pedido_hora_ResultadoF pedido_hora_resultadofinalF);

    void eliminar(Long id);
}
