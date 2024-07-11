package co.uceva.edu.base.services;

import co.uceva.edu.base.models.Categoria;
import co.uceva.edu.base.models.Empleado;
import co.uceva.edu.base.models.Usuario;
import co.uceva.edu.base.repositories.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CategoriaServiceJdbcImpl implements CategoriaService{
    private RepositoryCategoria<Categoria> repositoryCategoriaJdbc;

    public CategoriaServiceJdbcImpl(Connection connection) {
        this.repositoryCategoriaJdbc = new CategoriaRepositoryJdbcImpl(connection);
    }

    @Override
    public List<Categoria> listar() {
        try {
            return repositoryCategoriaJdbc.listar();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }
    @Override
    public Optional<Categoria> porId(Long id) {
        try {
            return Optional.ofNullable(repositoryCategoriaJdbc.porId(id));
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());

        }
    }


    @Override
    public void guardar(Categoria categorias) {
        try {
            repositoryCategoriaJdbc.guardar(categorias);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            repositoryCategoriaJdbc.eliminar(id);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }


}
