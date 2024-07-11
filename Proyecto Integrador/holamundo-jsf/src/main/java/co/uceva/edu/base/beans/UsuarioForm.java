package co.uceva.edu.base.beans;

import co.uceva.edu.base.models.Usuario;
import co.uceva.edu.base.services.UsuarioService;
import co.uceva.edu.base.services.UsuarioServiceJdbcImpl;
import co.uceva.edu.base.util.ConexionBaseDatos;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Named
@SessionScoped
public class UsuarioForm implements Serializable {


    private  Long id;
    private String nombre;
    private String login;
    private String password;
    private Usuario editar_usu;
    private String tipo;
    private List<Usuario> usuarios;

    public Usuario getEditar_usu() {
        return editar_usu;
    }

    public void setEditar_usu(Usuario editar_usu) {
        this.editar_usu = editar_usu;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String crear() {
        try {
            UsuarioService usuarioService = new UsuarioServiceJdbcImpl(ConexionBaseDatos.getConnection());
            Usuario usuario = new Usuario();
            usuario.setId(this.id);
            usuario.setLogin(this.login);
            usuario.setNombre(this.nombre);
            usuario.setPassword(this.password);
            usuario.setTipo(this.tipo);

            usuarioService.guardar(usuario);

            System.out.println(id);
            System.out.println(nombre);
            System.out.println(login);
            System.out.println(password);
            System.out.println(tipo);
            return "listar-usuarios.xhtml?faces-redirect=true";
        } catch (SQLException e) {
            e.printStackTrace();
            return "crear-usuario.xhtml?faces-redirect=true";
        }
    }

    public String guardar_visitante() {
        try {
            UsuarioService usuarioService = new UsuarioServiceJdbcImpl(ConexionBaseDatos.getConnection());
            Usuario usuario = new Usuario();
            usuario.setId(this.id);
            usuario.setLogin(this.login);
            usuario.setNombre(this.nombre);
            usuario.setPassword(this.password);
            usuario.setTipo("visitante");

            usuarioService.guardar(usuario);

            System.out.println(id);
            System.out.println(nombre);
            System.out.println(login);
            System.out.println(password);
            System.out.println(tipo);
            return "login.xhtml?faces-redirect=true";
        } catch (SQLException e) {
            e.printStackTrace();
            return "crear-usuario.xhtml?faces-redirect=true";
        }
    }
    public String guardar_edi() {
        try {
            UsuarioService usuarioService = new UsuarioServiceJdbcImpl(ConexionBaseDatos.getConnection());
            Usuario usuario = new Usuario();
            usuario.setId(this.editar_usu.getId());
            usuario.setLogin(this.editar_usu.getLogin());
            usuario.setNombre(this.editar_usu.getNombre());
            usuario.setPassword(this.editar_usu.getPassword());

            usuarioService.guardar(usuario);

            System.out.println(editar_usu.getId());
            System.out.println(editar_usu.getLogin());
            System.out.println(editar_usu.getNombre());
            System.out.println(editar_usu.getPassword());
            return "listar-usuarios.xhtml?faces-redirect=true";
        } catch (SQLException e) {
            e.printStackTrace();
            return "crear-usuario.xhtml?faces-redirect=true";
        }
    }


    public String editar( long idEditar){
        try {
            UsuarioService usuarioService = new UsuarioServiceJdbcImpl(ConexionBaseDatos.getConnection());
            Optional<Usuario> usuario = usuarioService.porId(idEditar);

            if(usuario.isPresent()){
                this.editar_usu = usuario.get();
                return "editar-usuario.xhtml?faces-redirect=true";
            }else{
                return "listar-usuarios.xhtml?faces-redirect=true";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return "crear-usuario.xhtml?faces-redirect=true";
        }
    }


    public String eliminar(Long id){

        try {
            UsuarioService usuarioService = new UsuarioServiceJdbcImpl(ConexionBaseDatos.getConnection());
            usuarioService.eliminar(id);
            return "listar-usuarios.xhtml?faces-redirect=true";
        } catch (SQLException e) {
            e.printStackTrace();
            return "listar-usuarios.xhtml?faces-redirect=true";
        }
    }


    public List<Usuario> listar() throws SQLException {
        UsuarioService usuarioService = new UsuarioServiceJdbcImpl(ConexionBaseDatos.getConnection());
        return usuarioService.listar();
    }


}
