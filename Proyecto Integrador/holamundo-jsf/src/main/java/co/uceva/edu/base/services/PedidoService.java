package co.uceva.edu.base.services;


import co.uceva.edu.base.models.Pedido;
import co.uceva.edu.base.reportes.*;

import java.util.List;
import java.util.Optional;

public interface PedidoService {
    List<Pedido> listar();

    Optional<Pedido> porId(Long id);

    void guardar(Pedido pedido);

    void eliminar(Long id);

    List<PedidosHora> porHora();

    List<PedidosHora_2> porHora_2();

    List<PedidosHora_3> porHora_3();

    List<PedidosResultado> porHora_Resultado();

    List<PedidosResultadoF> porHora_ResultadoF();


}
