package co.uceva.edu.base.services;


import co.uceva.edu.base.models.Pedido_hora_Resultado;

import java.util.List;
import java.util.Optional;

public interface Pedido_hora_ResultadoService {
    List<Pedido_hora_Resultado> listar();

    Optional<Pedido_hora_Resultado> porId(Long id);

    void guardar(Pedido_hora_Resultado pedido_hora_resultadofinal);

    void eliminar(Long id);
}
