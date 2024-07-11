package co.uceva.edu.base.beans;

import co.uceva.edu.base.models.Departamento;
import co.uceva.edu.base.models.Municipio;
import co.uceva.edu.base.services.MunicipioService;
import co.uceva.edu.base.services.MunicipioServiceJdbcImpl;
import co.uceva.edu.base.util.ConexionBaseDatos;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Named
@SessionScoped
public class MunicipioForm implements Serializable {

    private Long id;
    private Long id_departamento;
    private Municipio editar_mun;
    private String nombre;
    private List<Departamento> Departamentos;



    public Long getId_departamento() {
        return id_departamento;
    }

    public void setId_departamento(Long id_departamento) {
        this.id_departamento = id_departamento;
    }

    public Municipio getEditar_mun() {
        return editar_mun;
    }
    public void setEditar_mun(Municipio editar_mun) {
        this.editar_mun = editar_mun;
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
            MunicipioService municipioService = new MunicipioServiceJdbcImpl(ConexionBaseDatos.getConnection());
            Municipio municipio = new Municipio();
            municipio.setId(this.id);
            municipio.setNombre(this.nombre);
            municipio.setId_departamento(this.id_departamento);

            municipioService.guardar(municipio);

            System.out.println(id);
            System.out.println(nombre);
            System.out.println(id_departamento);
            return "municipio.xhtml?faces-redirect=true";
        } catch (SQLException e) {
            e.printStackTrace();
            return "crear-municipio.xhtml?faces-redirect=true";
        }
    }

    public String guardar_mun() {
        try {
            MunicipioService municipioService = new MunicipioServiceJdbcImpl(ConexionBaseDatos.getConnection());
            Municipio municipio = new Municipio();
            municipio.setId(this.editar_mun.getId());
            municipio.setNombre(this.editar_mun.getNombre());
            municipio.setId_departamento(this.editar_mun.getId_departamento());

            municipioService.guardar(municipio);

            System.out.println(editar_mun.getId());
            System.out.println(editar_mun.getNombre());
            System.out.println(editar_mun.getId_departamento());
            return "municipio.xhtml?faces-redirect=true";
        } catch (SQLException e) {
            e.printStackTrace();
            return "crear-municipio.xhtml?faces-redirect=true";
        }
    }

    public String editar( long idEditar){
        try {
            MunicipioService municipioService = new MunicipioServiceJdbcImpl(ConexionBaseDatos.getConnection());
            Optional<Municipio> municipio = municipioService.porId(idEditar);

            if(municipio.isPresent()){
                this.editar_mun = municipio.get();
                return "editar-municipio.xhtml?faces-redirect=true";
            }else{
                return "municipio.xhtml?faces-redirect=true";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return "crear-municipio.xhtml?faces-redirect=true";
        }
    }




    public List<Municipio> listar() throws SQLException {
        MunicipioService municipioService = new MunicipioServiceJdbcImpl(ConexionBaseDatos.getConnection());
        return municipioService.listar();
    }

    public String eliminar(Long id){

        try {
            MunicipioService municipioService = new MunicipioServiceJdbcImpl(ConexionBaseDatos.getConnection());
            municipioService.eliminar(id);
            return "municipio.xhtml?faces-redirect=true";
        } catch (SQLException e) {
            e.printStackTrace();
            return "municipio.xhtml?faces-redirect=true";
        }
    }
}