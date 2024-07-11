package co.uceva.edu.base.services;


import co.uceva.edu.base.models.Departamento;

import java.util.List;
import java.util.Optional;

public interface DepartamentosService {
    List<Departamento> listar();

    Optional<Departamento> porId(Long id);

    void guardar(Departamento departamentos);

    void eliminar(Long id);
}
