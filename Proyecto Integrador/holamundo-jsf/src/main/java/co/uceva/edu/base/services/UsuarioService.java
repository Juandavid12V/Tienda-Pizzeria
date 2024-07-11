package co.uceva.edu.base.services;

import co.uceva.edu.base.models.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<Usuario> listar();

    Optional<Usuario> login(String usuario, String password);

    void guardar(Usuario usuario);

    void eliminar(Long id);

    Optional<Usuario> tipologin(String usuario, String password, String tipo);

    Optional<Usuario> porId(Long id);

}
