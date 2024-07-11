package co.uceva.edu.base.services;

import co.uceva.edu.base.repositories.CategoriaRepositoryJdbcImpl;
import co.uceva.edu.base.repositories.ProductoRepositoryJdbcImpl;
import co.uceva.edu.base.repositories.Repository;
import co.uceva.edu.base.models.Categoria;
import co.uceva.edu.base.models.Producto;
import co.uceva.edu.base.repositories.RepositoryProducto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductoServiceJdbcImpl implements ProductoService{
    private RepositoryProducto<Producto> productoRepository;

    public ProductoServiceJdbcImpl(Connection connection) {
        this.productoRepository = new ProductoRepositoryJdbcImpl(connection);
    }

    @Override
    public List<Producto> listar() {
        try {
            return productoRepository.listar();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Producto> porId(Long id) {
        try {
            return Optional.ofNullable(productoRepository.porId(id));
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());

        }
    }

    @Override
    public void guardar(Producto producto) {
        try {
            productoRepository.guardar(producto);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            productoRepository.eliminar(id);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

}