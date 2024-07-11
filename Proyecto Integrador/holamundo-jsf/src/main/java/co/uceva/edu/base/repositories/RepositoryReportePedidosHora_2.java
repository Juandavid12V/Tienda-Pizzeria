package co.uceva.edu.base.repositories;


import co.uceva.edu.base.models.Pedido;

import java.sql.SQLException;
import java.util.List;

public interface RepositoryReportePedidosHora_2<T> {

    List<T> porHora_2() throws SQLException;

}