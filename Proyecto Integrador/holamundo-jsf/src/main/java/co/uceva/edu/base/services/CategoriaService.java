package co.uceva.edu.base.services;

import co.uceva.edu.base.models.Categoria;
import co.uceva.edu.base.models.Empleado;
import co.uceva.edu.base.models.Usuario;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {
    List<Categoria> listar();

    void guardar(Categoria categorias);

    void eliminar(Long id);

    Optional<Categoria> porId(Long id);
}

