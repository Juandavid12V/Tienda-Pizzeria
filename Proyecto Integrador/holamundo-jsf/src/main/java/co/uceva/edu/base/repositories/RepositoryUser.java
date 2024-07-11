package co.uceva.edu.base.repositories;

import co.uceva.edu.base.models.Usuario;

import java.sql.SQLException;
import java.util.List;

public interface RepositoryUser<T> {

    List<T> listar() throws SQLException;
    T login(String usuario, String password) throws SQLException;
    T tipologin(String usuario, String password, String tipo) throws SQLException;
    void guardar(T t) throws SQLException;
    void eliminar(Long id) throws SQLException;
    Usuario porId(Long id) throws SQLException;
}
