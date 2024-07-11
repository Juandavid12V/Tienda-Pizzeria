package co.uceva.edu.base.repositories;


import co.uceva.edu.base.models.Empleado;

import java.sql.SQLException;
import java.util.List;

public interface RepositoryEmpleado<T> {

    List<T> listar() throws SQLException;
    void guardar(T t) throws SQLException;
    void eliminar(Long id) throws SQLException;
    Empleado porId(Long id) throws SQLException;

}