package co.uceva.edu.base.beans;

import co.uceva.edu.base.models.Usuario;
import co.uceva.edu.base.services.UsuarioService;
import co.uceva.edu.base.services.UsuarioServiceJdbcImpl;
import co.uceva.edu.base.util.ConexionBaseDatos;
import co.uceva.edu.base.util.SessionUtils;

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
public class Resultado_MateForm implements Serializable{

    private  double resultado;





}
