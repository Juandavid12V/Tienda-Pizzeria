package co.uceva.edu.base.services;


import co.uceva.edu.base.models.Municipio;

import java.util.List;
import java.util.Optional;

public interface MunicipioService {
    List<Municipio> listar();

    Optional<Municipio> porId(Long id);

    void guardar(Municipio municipio);

    void eliminar(Long id);
}
