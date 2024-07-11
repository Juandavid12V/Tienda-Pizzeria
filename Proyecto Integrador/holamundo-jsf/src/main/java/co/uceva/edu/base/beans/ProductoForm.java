package co.uceva.edu.base.beans;

import co.uceva.edu.base.models.Producto;
import co.uceva.edu.base.models.Usuario;
import co.uceva.edu.base.services.ProductoService;
import co.uceva.edu.base.services.ProductoServiceJdbcImpl;
import co.uceva.edu.base.services.UsuarioService;
import co.uceva.edu.base.services.UsuarioServiceJdbcImpl;
import co.uceva.edu.base.util.ConexionBaseDatos;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Named
@SessionScoped
public class ProductoForm implements Serializable {

    private Long id;
    private String nombre;
    private String descripcion;
    private Part imagen;
    private double precio;
    private Producto editar_producto;
    private Long id_pedido;

    public Part getImagen() {return imagen;
    }

    public void setImagen(Part imagen) {this.imagen = imagen;
    }


    public Long getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(Long id_pedido) {
        this.id_pedido = id_pedido;
    }


    public Producto getEditar_producto() {
        return editar_producto;
    }

    public void setEditar_producto(Producto editar_producto) {
        this.editar_producto = editar_producto;
    }


    public List<Producto> getProductos() {
        return Productos;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setProductos(List<Producto> Productos) {
        this.Productos = Productos;
    }

    private List<Producto> Productos;











    public String guardar(String accion) {
        String nombreFoto = null;
        System.out.println("Prodcuto Iniciar");
        File file = null;
        try {
            if(imagen!= null) {
                String header = imagen.getHeader("content-disposition");
                System.out.println("header " + header);
                for (String content : header.split(";")) {
                    if (content.trim().startsWith("filename")) {
                        nombreFoto = content.substring(content.indexOf("=") + 1).trim().replace("\"", "");
                    }
                }

                file = new File("C:\\img_upload", nombreFoto);

                try (InputStream input = imagen.getInputStream()) {
                    Files.copy(input, file.toPath());
                }

                System.out.println("Nombre foto " + nombreFoto);
            }

            ProductoService productoService = new ProductoServiceJdbcImpl(ConexionBaseDatos.getConnection());
            Producto producto = new Producto();
            if("CREA".equals(accion)) {
                producto.setNombre(this.nombre);
                producto.setDescripcion(this.descripcion);
                producto.setPrecio(this.precio);
                producto.setId_pedido(this.id_pedido);
                if (file != null) {
                    producto.setImagen(file.toPath().toString());
                }
            }
            System.out.println("guardando img" +nombreFoto);
            productoService.guardar(producto);
            /*
            if("ACTUALIZA".equals(accion)) {

                Producto.setId(this.editar_producto.getId());
                Producto.setNombre(this.editar_producto.getNombre());
                Producto.setDescripcion(this.editar_producto.getDescripcion());
                Producto.setPrecio(this.editar_producto.getPrecio());
                Producto.setId_pedido(this.editar_producto.getId_pedido());
                Producto.setImagen(this.editar_producto.getImagen());
            }

            ProductoService.guardar(Producto);

            System.out.println(id);
            System.out.println(nombre);
            System.out.println(descripcion);
            System.out.println(precio);
            System.out.println(id_pedido);
            */

            return "productos.xhtml?faces-redirect=true";
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return "crear-producto.xhtml?faces-redirect=true";
        }
    }
/*
    public String guardar_edi() {
            try {

            ProductoService ProductoService = new ProductoServiceJdbcImpl(ConexionBaseDatos.getConnection());
            Producto Producto = new Producto();
            if("CREA".equals(accion)) {
                Producto.setId(this.editar_producto.getId());
                Producto.setNombre(this.editar_producto.getNombre());
                Producto.setDescripcion(this.editar_producto.getDescripcion());
                Producto.setPrecio(this.editar_producto.getPrecio());
                Producto.setId_pedido(this.editar_producto.getId_pedido());
                if (file != null) {
                    Producto.setImagen(file.toPath().toString());
                }
            }

            ProductoService.guardar(Producto);


            System.out.println(editar_producto.getId());
            System.out.println(editar_producto.getNombre());
            System.out.println(editar_producto.getDescripcion());
            System.out.println(editar_producto.getImagen());
            System.out.println(editar_producto.getPrecio());
            System.out.println(editar_producto.getId_pedido());

            return "productos.xhtml?faces-redirect=true";
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return "crear-producto.xhtml?faces-redirect=true";
        }
    }

*/
    public List<Producto> listar() throws SQLException {
        ProductoService productoService = new ProductoServiceJdbcImpl(ConexionBaseDatos.getConnection());
        return productoService.listar();
    }

    public String editar( long idEditar){
        try {
            ProductoService productoService = new ProductoServiceJdbcImpl(ConexionBaseDatos.getConnection());
            Optional<Producto> producto = productoService.porId(idEditar);

            if(producto.isPresent()){
                this.editar_producto = producto.get();
                return "editar-productos.xhtml?faces-redirect=true";
            }else{
                return "producto.xhtml?faces-redirect=true";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return "crear-producto.xhtml?faces-redirect=true";
        }
    }




    public String eliminar(Long id){

        try {
            ProductoService productoService = new ProductoServiceJdbcImpl(ConexionBaseDatos.getConnection());
            productoService.eliminar(id);
            return "producto.xhtml?faces-redirect=true";
        } catch (SQLException e) {
            e.printStackTrace();
            return "producto.xhtml?faces-redirect=true";
        }
    }
}