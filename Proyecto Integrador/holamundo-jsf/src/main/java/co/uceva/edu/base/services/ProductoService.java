package co.uceva.edu.base.services;

import co.uceva.edu.base.models.Categoria;
import co.uceva.edu.base.models.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> listar();

    Optional<Producto> porId(Long id);

    void guardar(Producto producto);

    void eliminar(Long id);
}
