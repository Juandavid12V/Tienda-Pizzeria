package co.uceva.edu.base.repositories;


import co.uceva.edu.base.models.Pedido;

import java.sql.SQLException;
import java.util.List;

public interface RepositoryPedido<T> {

    List<T> listar() throws SQLException;
    void guardar(T t) throws SQLException;
    void eliminar(Long id) throws SQLException;
    Pedido porId(Long id) throws SQLException;
    List<T> porHora() throws SQLException;
    List<T> porHora_2() throws SQLException;
    List<T> porHora_3() throws SQLException;
    List<T> porHora_Resultado() throws SQLException;
    List<T> porHora_ResultadoF() throws SQLException;
}