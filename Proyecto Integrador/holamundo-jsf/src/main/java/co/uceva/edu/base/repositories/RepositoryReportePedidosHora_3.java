package co.uceva.edu.base.repositories;


import java.sql.SQLException;
import java.util.List;

public interface RepositoryReportePedidosHora_3<T> {

    List<T> porHora_3() throws SQLException;
}