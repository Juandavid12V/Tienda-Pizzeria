package co.uceva.edu.base.services;

import co.uceva.edu.base.models.Empleado;

import java.util.List;
import java.util.Optional;

public interface EmpleadoService {
    List<Empleado> listar();

    Optional<Empleado> porId(Long id);

    void guardar(Empleado empleado);

    void eliminar(Long id);
}
