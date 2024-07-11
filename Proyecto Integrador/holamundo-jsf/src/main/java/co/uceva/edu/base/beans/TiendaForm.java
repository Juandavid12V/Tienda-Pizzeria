package co.uceva.edu.base.beans;
import co.uceva.edu.base.models.Tienda;
import co.uceva.edu.base.models.Usuario;
import co.uceva.edu.base.services.*;
import co.uceva.edu.base.util.ConexionBaseDatos;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Named
@SessionScoped
public class TiendaForm implements Serializable {

    private Long id;
    private String direccion;
    private Tienda editar_tienda;

    public Tienda getEditar_tienda() {
        return editar_tienda;
    }

    public void setEditar_tienda(Tienda editar_tienda) {
        this.editar_tienda = editar_tienda;
    }

    public List<Tienda> getTiendas() {
        return Tiendas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


    public void setTiendas(List<Tienda> Tiendas) {
        this.Tiendas = Tiendas;
    }

    private List<Tienda> Tiendas;

    public String crear() {
        try {
            TiendaService TiendaService = new TiendaServiceJdbcImpl(ConexionBaseDatos.getConnection());
            Tienda tienda = new Tienda();
            tienda.setId(this.id);
            tienda.setDireccion(this.direccion);

            TiendaService.guardar(tienda);

            System.out.println(id);
            System.out.println(direccion);
            return "tiendas.xhtml?faces-redirect=true";
        } catch (SQLException e) {
            e.printStackTrace();
            return "crear-tienda.xhtml?faces-redirect=true";
        }
    }

    public List<Tienda> listar() throws SQLException {
        TiendaService tiendaService = new TiendaServiceJdbcImpl(ConexionBaseDatos.getConnection());
        return tiendaService.listar();
    }

    public String guardar_edi() {
        try {
            TiendaService TiendaService = new TiendaServiceJdbcImpl(ConexionBaseDatos.getConnection());
            Tienda tienda= new Tienda();
            tienda.setId(this.editar_tienda.getId());
            tienda.setDireccion(this.editar_tienda.getDireccion());

            TiendaService.guardar(tienda);

            System.out.println(editar_tienda.getId());
            System.out.println(editar_tienda.getDireccion());
            return "tiendas.xhtml?faces-redirect=true";
        } catch (SQLException e) {
            e.printStackTrace();
            return "crear-tienda.xhtml?faces-redirect=true";
        }
    }

    public String editar( long idEditar){
        try {
            TiendaService tiendaService = new TiendaServiceJdbcImpl(ConexionBaseDatos.getConnection());
            Optional<Tienda> tienda = tiendaService.porId(idEditar);
            if(tienda.isPresent()){
                this.editar_tienda = tienda.get();
                return "editar-tienda.xhtml?faces-redirect=true";
            }else{
                return "tienda.xhtml?faces-redirect=true";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return "crear-tienda.xhtml?faces-redirect=true";
        }
    }


    public String eliminar(Long id){

        try {
            TiendaService tiendaService = new TiendaServiceJdbcImpl(ConexionBaseDatos.getConnection());
            tiendaService.eliminar(id);
            return "tiendas.xhtml?faces-redirect=true";
        } catch (SQLException e) {
            e.printStackTrace();
            return "tiendas.xhtml?faces-redirect=true";
        }
    }
}