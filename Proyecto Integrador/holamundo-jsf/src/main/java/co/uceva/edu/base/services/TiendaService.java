package co.uceva.edu.base.services;


import co.uceva.edu.base.models.Tienda;

import java.util.List;
import java.util.Optional;

public interface TiendaService {
    List<Tienda> listar();

    Optional<Tienda> porId(Long id);

    void guardar(Tienda tienda);

    void eliminar(Long id);
}
