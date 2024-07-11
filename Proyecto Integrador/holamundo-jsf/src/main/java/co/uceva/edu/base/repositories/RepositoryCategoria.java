package co.uceva.edu.base.repositories;

import co.uceva.edu.base.models.Categoria;
import co.uceva.edu.base.models.Departamento;

import java.sql.SQLException;
import java.util.List;

public interface RepositoryCategoria<T> {

    List<T> listar() throws SQLException;
    void guardar(T t) throws SQLException;
    void eliminar(Long id) throws SQLException;
    Categoria porId(Long id) throws SQLException;
}
