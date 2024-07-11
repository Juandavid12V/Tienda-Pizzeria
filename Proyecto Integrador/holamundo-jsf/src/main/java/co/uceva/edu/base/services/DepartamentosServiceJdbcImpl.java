package co.uceva.edu.base.services;

import co.uceva.edu.base.models.Departamento;
import co.uceva.edu.base.repositories.DepartamentosRepositoryJdbcImpl;
import co.uceva.edu.base.repositories.RepositoryDepartamento;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class DepartamentosServiceJdbcImpl implements DepartamentosService{
    private RepositoryDepartamento<Departamento> repositoryJdbc;

    public DepartamentosServiceJdbcImpl(Connection connection) {
        this.repositoryJdbc = new DepartamentosRepositoryJdbcImpl(connection);
    }

    @Override
    public List<Departamento> listar() {
        try {
            return repositoryJdbc.listar();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Departamento> porId(Long id) {
        try {
            return Optional.ofNullable(repositoryJdbc.porId(id));
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());

        }
    }

    @Override
    public void guardar(Departamento departamentos) {
        try {
            repositoryJdbc.guardar(departamentos);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            repositoryJdbc.eliminar(id);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

}