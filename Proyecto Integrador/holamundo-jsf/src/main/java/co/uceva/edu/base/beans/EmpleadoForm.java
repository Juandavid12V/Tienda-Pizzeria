package co.uceva.edu.base.beans;

import co.uceva.edu.base.models.Empleado;
import co.uceva.edu.base.services.EmpleadoService;
import co.uceva.edu.base.services.EmpleadoServiceJdbcImpl;
import co.uceva.edu.base.util.ConexionBaseDatos;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Named
@SessionScoped
public class EmpleadoForm implements Serializable {

    private  Long cedula;
    private  String nombre;
    private  String apellido;
    private  String telefono;
    private  Long id_tienda;
    private  Long id_municipio;
    private Empleado editar_em;

    public Empleado getEditar_em() {
        return editar_em;
    }

    public void setEditar_em(Empleado editar_em) {
        this.editar_em = editar_em;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Long getId_tienda() {
        return id_tienda;
    }

    public void setId_tienda(Long id_tienda) {
        this.id_tienda = id_tienda;
    }

    public Long getId_municipio() {
        return id_municipio;
    }

    public void setId_municipio(Long id_municipio) {
        this.id_municipio = id_municipio;
    }


    public String crear() {
        try {
            EmpleadoService EmpleadoService = new EmpleadoServiceJdbcImpl(ConexionBaseDatos.getConnection());
            Empleado empleado = new Empleado();
            empleado.setCedula(this.cedula);
            empleado.setNombre(this.nombre);
            empleado.setApellido(this.apellido);
            empleado.setTelefono(this.telefono);
            empleado.setId_tienda(this.id_tienda);
            empleado.setId_municipio(this.id_municipio);

            EmpleadoService.guardar(empleado);

            System.out.println(cedula);
            System.out.println(nombre);
            System.out.println(apellido);
            System.out.println(telefono);
            System.out.println(id_tienda);
            System.out.println(id_municipio);
            return "listar-empleados.xhtml?faces-redirect=true";
        } catch (SQLException e) {
            e.printStackTrace();
            return "crear-emplados.xhtml?faces-redirect=true";
        }
    }

    public String guardar_edi() {
        try {
            EmpleadoService empleadoService = new EmpleadoServiceJdbcImpl(ConexionBaseDatos.getConnection());
            Empleado empleado = new Empleado();
            empleado.setCedula(this.editar_em.getCedula());
            empleado.setNombre(this.editar_em.getNombre());
            empleado.setApellido(this.editar_em.getApellido());
            empleado.setId_tienda(this.editar_em.getId_tienda());
            empleado.setId_municipio(this.editar_em.getId_municipio());

            empleadoService.guardar(empleado);

            System.out.println(editar_em.getCedula());
            System.out.println(editar_em.getNombre());
            System.out.println(editar_em.getApellido());
            System.out.println(editar_em.getId_tienda());
            System.out.println(editar_em.getId_municipio());

            return "listar-empleados.xhtml?faces-redirect=true";
        } catch (SQLException e) {
            e.printStackTrace();
            return "crear-emplados.xhtml?faces-redirect=true";
        }
    }


    public String editar( long idEditar){
        try {
            EmpleadoService empleadoService = new EmpleadoServiceJdbcImpl(ConexionBaseDatos.getConnection());
            Optional<Empleado> empleado = empleadoService.porId(idEditar);

            if(empleado.isPresent()){
                this.editar_em = empleado.get();
                return "editar-empleado.xhtml?faces-redirect=true";
            }else{
                return "listar-empleados.xhtml?faces-redirect=true";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return "crear-emplados.xhtml?faces-redirect=true";
        }
    }

    public void setEmpleados(List<Empleado> Empleados) {
        this.Empleados = Empleados;
    }

    private List<Empleado> Empleados;

    public List<Empleado> listar() throws SQLException {
        EmpleadoService empleadoService = new EmpleadoServiceJdbcImpl(ConexionBaseDatos.getConnection());
        return empleadoService.listar();
    }

    public String eliminar(Long id){

        try {
            EmpleadoService empleadoService = new EmpleadoServiceJdbcImpl(ConexionBaseDatos.getConnection());
            empleadoService.eliminar(id);
            return "listar-empleados.xhtml?faces-redirect=true";
        } catch (SQLException e) {
            e.printStackTrace();
            return "listar-empleados.xhtml?faces-redirect=true";
        }
    }
}