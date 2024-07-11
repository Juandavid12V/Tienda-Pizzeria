package co.uceva.edu.base.repositories;


import java.sql.SQLException;
import java.util.List;

public interface RepositoryReportePedidosHora_ResultadoF<T> {

    List<T> porHora_ResultadoF() throws SQLException;
}