package co.uceva.edu.base.beans;

import co.uceva.edu.base.models.Categoria;
import co.uceva.edu.base.services.CategoriaService;
import co.uceva.edu.base.services.CategoriaServiceJdbcImpl;

import co.uceva.edu.base.util.ConexionBaseDatos;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Named
@SessionScoped
public class CategoriaForm implements Serializable {

    private  Long id;
    private  String nombre;
    private Categoria editar_categoria;

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

    public Categoria getEditar_categoria() {
        return editar_categoria;
    }

    public void setEditar_categoria(Categoria editar_categoria) {
        this.editar_categoria = editar_categoria;
    }



    public String crear() {
        try {
            CategoriaService categoriaService = new CategoriaServiceJdbcImpl(ConexionBaseDatos.getConnection());
            Categoria categoria = new Categoria();
            categoria.setId(this.getId());
            categoria.setNombre(this.getNombre());

            categoriaService.guardar(categoria);

            System.out.println(id);
            System.out.println(nombre);
            return "categoria.xhtml?faces-redirect=true";
        } catch (SQLException e) {
            e.printStackTrace();
            return "crear-categoria.xhtml?faces-redirect=true";
        }
    }

    public String guardar_edi() {
        try {
            CategoriaService categoriaService = new CategoriaServiceJdbcImpl(ConexionBaseDatos.getConnection());
            Categoria categoria = new Categoria();

            categoria.setId(this.editar_categoria.getId());
            categoria.setNombre(this.editar_categoria.getNombre());

            categoriaService.guardar(categoria);

            System.out.println(editar_categoria.getId());
            System.out.println(editar_categoria.getNombre());


            return "categoria.xhtml?faces-redirect=true";
        } catch (SQLException e) {
            e.printStackTrace();
            return "crear-categoria.xhtml?faces-redirect=true";
        }
    }


    public String editar( long idEditar){
        try {
            CategoriaService categoriaService = new CategoriaServiceJdbcImpl(ConexionBaseDatos.getConnection());
            Optional<Categoria> categoria = categoriaService.porId(idEditar);

            if(categoria.isPresent()){
                this.editar_categoria = categoria.get();
                return "editar-categoria.xhtml?faces-redirect=true";
            }else{
                return "categoria.xhtml?faces-redirect=true";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return "crear-categoria.xhtml?faces-redirect=true";
        }
    }



    private List<Categoria> Categoria;

    public List<Categoria> listar() throws SQLException {
        CategoriaService categoriaService = new CategoriaServiceJdbcImpl(ConexionBaseDatos.getConnection());
        return categoriaService.listar();
    }

    public String eliminar(Long id){

        try {
            CategoriaService categoriaService = new CategoriaServiceJdbcImpl(ConexionBaseDatos.getConnection());
            categoriaService.eliminar(id);
            return "categoria.xhtml?faces-redirect=true";
        } catch (SQLException e) {
            e.printStackTrace();
            return "categoria.xhtml?faces-redirect=true";
        }
    }
}