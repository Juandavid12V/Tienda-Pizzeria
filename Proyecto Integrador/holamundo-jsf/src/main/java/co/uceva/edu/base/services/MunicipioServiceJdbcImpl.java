package co.uceva.edu.base.services;

import co.uceva.edu.base.models.Municipio;
import co.uceva.edu.base.repositories.MunicipioRepositoryJdbcImpl;
import co.uceva.edu.base.repositories.Repository;
import co.uceva.edu.base.repositories.RepositoryMunicipio;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MunicipioServiceJdbcImpl implements MunicipioService{
    private RepositoryMunicipio<Municipio> repositoryJdbc;

    public MunicipioServiceJdbcImpl(Connection connection) {
        this.repositoryJdbc = new MunicipioRepositoryJdbcImpl(connection);
    }

    @Override
    public List<Municipio> listar() {
        try {
            return repositoryJdbc.listar();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Municipio> porId(Long id) {
        try {
            return Optional.ofNullable(repositoryJdbc.porId(id));
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());

        }
    }

    @Override
    public void guardar(Municipio municipio) {
        try {
            repositoryJdbc.guardar(municipio);
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