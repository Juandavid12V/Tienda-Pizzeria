package co.uceva.edu.base.beans;

import co.uceva.edu.base.models.Consulta_9;
import co.uceva.edu.base.models.Municipio;
import co.uceva.edu.base.models.Pedido;
import co.uceva.edu.base.services.*;
import co.uceva.edu.base.util.ConexionBaseDatos;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@Named
@SessionScoped
public class Consulta_9Form implements Serializable {

    private Long id;
    private String nombre_cliente;
    private String direccion;
    private String producto;
    private String municipio;
    private String direccion_2;
    private Long telefono;
    private String nombre;

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    private List<Consulta_9> Consulta_9;

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getDireccion_2() {
        return direccion_2;
    }

    public void setDireccion_2(String direccion_2) {
        this.direccion_2 = direccion_2;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public List<co.uceva.edu.base.models.Consulta_9> getConsulta_9() {
        return Consulta_9;
    }

    public void setConsulta_9(List<co.uceva.edu.base.models.Consulta_9> consulta_9) {
        Consulta_9 = consulta_9;
    }

    public List<Consulta_9> listar() throws SQLException {
        Consulta_9Service consulta_9Service = new Consulta_9ServiceJdbcImpl(ConexionBaseDatos.getConnection());
        return consulta_9Service.listar();
    }

    public List<Consulta_9> listar_2() throws SQLException {
        Consulta_9Service consulta_9Service = new Consulta_9ServiceJdbcImpl(ConexionBaseDatos.getConnection());
        return consulta_9Service.listar_2();
    }

    public List<Consulta_9> listar_3() throws SQLException {
        Consulta_9Service consulta_9Service = new Consulta_9ServiceJdbcImpl(ConexionBaseDatos.getConnection());
        return consulta_9Service.listar_3();
    }

    public List<Consulta_9> listar_4() throws SQLException {
        Consulta_9Service consulta_9Service = new Consulta_9ServiceJdbcImpl(ConexionBaseDatos.getConnection());
        return consulta_9Service.listar_4();
    }

    public List<Consulta_9> listar_5() throws SQLException {
        Consulta_9Service consulta_9Service = new Consulta_9ServiceJdbcImpl(ConexionBaseDatos.getConnection());
        return consulta_9Service.listar_5();
    }

    public List<Consulta_9> listar_6() throws SQLException {
        Consulta_9Service consulta_9Service = new Consulta_9ServiceJdbcImpl(ConexionBaseDatos.getConnection());
        return consulta_9Service.listar_6();
    }

    public List<Consulta_9> listar_7() throws SQLException {
        Consulta_9Service consulta_9Service = new Consulta_9ServiceJdbcImpl(ConexionBaseDatos.getConnection());
        return consulta_9Service.listar_7();
    }

    public List<Consulta_9> listar_8() throws SQLException {
        Consulta_9Service consulta_9Service = new Consulta_9ServiceJdbcImpl(ConexionBaseDatos.getConnection());
        return consulta_9Service.listar_8();
    }



}