package co.uceva.edu.base.services;

import co.uceva.edu.base.models.Consulta_9;
import co.uceva.edu.base.repositories.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class Consulta_9ServiceJdbcImpl implements Consulta_9Service{
    private RepositoryConsulta_9<Consulta_9> repositoryJdbc;

    public Consulta_9ServiceJdbcImpl(Connection connection) {
        this.repositoryJdbc = new Consulta_9RepositoryJdbcImpl(connection);
    }

    @Override
    public List<Consulta_9> listar() {
        try {
            return repositoryJdbc.listar();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public List<Consulta_9> listar_2() {
        try {
            return repositoryJdbc.listar_2();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public List<Consulta_9> listar_3() {
        try {
            return repositoryJdbc.listar_3();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public List<Consulta_9> listar_4() {
        try {
            return repositoryJdbc.listar_4();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public List<Consulta_9> listar_5() {
        try {
            return repositoryJdbc.listar_5();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }

    }

    @Override
    public List<Consulta_9> listar_6() {
        try {
            return repositoryJdbc.listar_6();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }

    }


    @Override
    public List<Consulta_9> listar_7() {
        try {
            return repositoryJdbc.listar_7();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }

    }

    @Override
    public List<Consulta_9> listar_8() {
        try {
            return repositoryJdbc.listar_8();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }

    }

}