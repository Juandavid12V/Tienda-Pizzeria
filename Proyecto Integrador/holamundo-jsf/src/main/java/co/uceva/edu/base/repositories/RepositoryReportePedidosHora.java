package co.uceva.edu.base.repositories;


import co.uceva.edu.base.models.Pedido;
import co.uceva.edu.base.reportes.PedidosHora_2;

import java.sql.SQLException;
import java.util.List;

public interface RepositoryReportePedidosHora<T> {

    List<T> porHora() throws SQLException;

}