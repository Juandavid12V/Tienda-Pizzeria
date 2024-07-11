package co.uceva.edu.base.repositories;


import java.sql.SQLException;
import java.util.List;

public interface RepositoryReportePedidosHora_Resultado<T> {

    List<T> porHora_Resultado() throws SQLException;
}