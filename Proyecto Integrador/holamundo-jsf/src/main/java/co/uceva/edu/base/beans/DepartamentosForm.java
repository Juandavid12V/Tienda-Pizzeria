package co.uceva.edu.base.beans;
import co.uceva.edu.base.models.Departamento;
import co.uceva.edu.base.services.*;

import co.uceva.edu.base.util.ConexionBaseDatos;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Named
@SessionScoped
public class DepartamentosForm  implements Serializable {

    private Long id;
    private Departamento editar_dep;
    private String nombre;
    private List<Departamento> Departamento;

    public Departamento getEditar_dep(){
        return editar_dep;
    }

    public void setEditar_dep(Departamento editar_dep) {
        this.editar_dep = editar_dep;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String crear() {
        try {
            DepartamentosService departamentosService = new DepartamentosServiceJdbcImpl(ConexionBaseDatos.getConnection());
            Departamento departamento = new Departamento();
            departamento.setId(this.id);
            departamento.setNombre(this.nombre);

            departamentosService.guardar(departamento);

            System.out.println(id);
            System.out.println(nombre);
            return "departamento.xhtml?faces-redirect=true";
        } catch (SQLException e) {
            e.printStackTrace();
            return "crear-departamento.xhtml?faces-redirect=true";
        }
    }

    public String guardar_dep() {
        try {
            DepartamentosService departamentosService = new DepartamentosServiceJdbcImpl(ConexionBaseDatos.getConnection());
            Departamento departamento = new Departamento();
            departamento.setId(this.editar_dep.getId());
            departamento.setNombre(this.editar_dep.getNombre());

            departamentosService.guardar(departamento);

            System.out.println(editar_dep.getId());
            System.out.println(editar_dep.getNombre());
            return "departamento.xhtml?faces-redirect=true";
        } catch (SQLException e) {
            e.printStackTrace();
            return "crear-departamento.xhtml?faces-redirect=true";
        }
    }

    public String editar( long idEditar){
        try {
            DepartamentosService departamentosService = new DepartamentosServiceJdbcImpl(ConexionBaseDatos.getConnection());
            Optional<Departamento> departamento = departamentosService.porId(idEditar);

            if(departamento.isPresent()){
                this.editar_dep = departamento.get();
                return "editar-departamento.xhtml?faces-redirect=true";
            }else{
                return "departamento.xhtml?faces-redirect=true";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return "crear-departamento.xhtml?faces-redirect=true";
        }
    }




    public List<Departamento> listar() throws SQLException {
        DepartamentosService departamentosService = new DepartamentosServiceJdbcImpl(ConexionBaseDatos.getConnection());
        return departamentosService.listar();
    }

    public String eliminar(Long id){

        try {
            DepartamentosService departamentosService = new DepartamentosServiceJdbcImpl(ConexionBaseDatos.getConnection());
            departamentosService.eliminar(id);
            return "departamento.xhtml?faces-redirect=true";
        } catch (SQLException e) {
            e.printStackTrace();
            return "departamento.xhtml?faces-redirect=true";
        }
    }
}