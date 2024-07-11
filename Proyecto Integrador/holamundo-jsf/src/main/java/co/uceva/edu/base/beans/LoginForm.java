package co.uceva.edu.base.beans;

import co.uceva.edu.base.models.Usuario;
import co.uceva.edu.base.services.UsuarioService;
import co.uceva.edu.base.services.UsuarioServiceJdbcImpl;
import co.uceva.edu.base.util.ConexionBaseDatos;
import co.uceva.edu.base.util.SessionUtils;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Optional;

@Named
@SessionScoped
public class LoginForm implements Serializable{

    private  String usuario;

    private  String password;


    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String validarUsuario() throws SQLException {

        UsuarioService usuarioService = new UsuarioServiceJdbcImpl(ConexionBaseDatos.getConnection());
        Optional<Usuario> usuarioOptional = usuarioService.login(this.usuario,this.password);
        Optional<Usuario> usuarioOptional2 = usuarioService.tipologin(this.usuario,this.password,"admin");
        Optional<Usuario> usuarioOptional3 = usuarioService.tipologin(this.usuario,this.password,"visitante");

        if(usuarioOptional.isPresent()){
            if(usuarioOptional2.isPresent()){
                HttpSession session =  SessionUtils.getSession();
                session.setAttribute("username",this.usuario);

                return "admin";
            }else {
                if (usuarioOptional3.isPresent()) {
                    return "visitante";
                }
            }

            }else{
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_FATAL,
                    "Usuario o Password incorrecto",
                    "Por favor intente de nuevo");

            FacesContext.getCurrentInstance().addMessage(null,mensaje);
            System.out.println("Password incorrrecto");
        }

        return "";
    }

    public String logout(){
        HttpSession session =  SessionUtils.getSession();
        session.invalidate();
        return "login";
    }





}
