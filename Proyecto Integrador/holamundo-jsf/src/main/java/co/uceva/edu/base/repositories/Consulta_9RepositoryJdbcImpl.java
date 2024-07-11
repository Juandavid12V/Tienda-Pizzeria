package co.uceva.edu.base.repositories;


import co.uceva.edu.base.models.Consulta_9;
import co.uceva.edu.base.models.Municipio;
import co.uceva.edu.base.reportes.PedidosHora;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Consulta_9RepositoryJdbcImpl implements RepositoryConsulta_9<Consulta_9> {
    private Connection conn;

    public Consulta_9RepositoryJdbcImpl(Connection conn) {
        this.conn = conn;
    }

    /////////////////////////////////////////CONSULTA # 9/////////////////////////////////////////////


    private Consulta_9 getConsulta_9(ResultSet rs) throws SQLException {
        Consulta_9 p = new Consulta_9();

        p.setNombre_cliente(rs.getString("nombre_cliente"));
        p.setDireccion(rs.getString("direccion"));
        p.setProducto(rs.getString("producto"));


        return p;
    }
    public List<Consulta_9>listar()throws SQLException {
        List<Consulta_9> consulta_9 = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT nombre_cliente,direccion,t3.producto\n" +
                     "FROM (\tSELECT t1.id_pedidos,cedula,producto FROM (SELECT id_pedidos,cedula FROM a_domicilio) AS t1\n" +
                     "        INNER JOIN (SELECT id_pedido,nombre as producto FROM productos) AS t2\n" +
                     "        ON t1.id_pedidos=t2.id_pedido) AS t3\n" +
                     "INNER JOIN (SELECT cedula,nombre AS nombre_cliente,direccion FROM cliente) AS t4\n" +
                     "ON t3.cedula=t4.cedula")) {
            while (rs.next()) {
                Consulta_9 p = getConsulta_9(rs);
                consulta_9.add(p);
            }
        }
        return consulta_9;
    }


/////////////////////////////////////////CONSULTA # 10/////////////////////////////////////////////


    private Consulta_9 getConsulta_10(ResultSet rs) throws SQLException {
        Consulta_9 p = new Consulta_9();

        p.setId(rs.getLong("id"));
        p.setDireccion_3(rs.getString("direccion_3"));
        p.setMunicipio_2(rs.getString("municipio_2"));


        return p;
    }

    public List<Consulta_9>listar_2()throws SQLException {
        List<Consulta_9> consulta_9 = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT t1.id,direccion_3,municipio_2 FROM (SELECT id,direccion as direccion_3,id_municipio FROM tienda) AS t1\n" +
                     "INNER JOIN (SELECT id,nombre as municipio_2,id_departamento FROM municipio) AS t2\n" +
                     "ON t1.id_municipio=t2.id\n" +
                     "ORDER BY id_departamento\n")) {
            while (rs.next()) {
                Consulta_9 p = getConsulta_10(rs);
                consulta_9.add(p);
            }
        }
        return consulta_9;
    }


    /////////////////////////////////////////CONSULTA # 11/////////////////////////////////////////////


    private Consulta_9 getConsulta_11(ResultSet rs) throws SQLException {
        Consulta_9 p = new Consulta_9();


        p.setNombre_empleado(rs.getString("nombre_empleado"));


        return p;
    }

    public List<Consulta_9>listar_4()throws SQLException {
        List<Consulta_9> consulta_9 = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT nombre_empleado FROM (SELECT empleado as nombre_empleado,id_tienda,municipio FROM (SELECT cedula,nombre as empleado,id_tienda,id_municipio FROM empleado) AS t1\n" +
                     "INNER JOIN (SELECT id,nombre as municipio FROM municipio) AS t2\n" +
                     "ON t1.id_municipio=t2.id) AS t5\n" +
                     "INNER JOIN (SELECT t3.id,municipio FROM (SELECT id,id_municipio FROM tienda) AS t3\n" +
                     "INNER JOIN (SELECT id,nombre as municipio FROM municipio) AS t4\n" +
                     " ON t3.id_municipio=t4.id) AS t6\n" +
                     "ON t5.municipio<>t6.municipio and t5.id_tienda=t6.id")) {
            while (rs.next()) {
                Consulta_9 p = getConsulta_11(rs);
                consulta_9.add(p);
            }
        }
        return consulta_9;
    }


    /////////////////////////////////////////CONSULTA # 12/////////////////////////////////////////////


    private Consulta_9 getConsulta_12(ResultSet rs) throws SQLException {
        Consulta_9 p = new Consulta_9();

        p.setMaximo(rs.getLong("maximo"));
        p.setNombre(rs.getString("nombre"));
        p.setMunicipio(rs.getString("municipio"));


        return p;
    }

    public List<Consulta_9>listar_5()throws SQLException {
        List<Consulta_9> consulta_9 = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(" SELECT t15.nombre,t14.municipio,t15.maximo FROM (\n" +
                     "SELECT max(t13.can_tipo) as maximo,t13.municipio FROM (SELECT nombre,can_tipo,municipio FROM (SELECT nombre,can_tipo,id_municipio FROM (SELECT t7.cedula,can_tipo,id_municipio FROM (SELECT id_pedidos,t5.cedula,can_tipo,id_tienda FROM (SELECT id_pedidos,t4.cedula,can_tipo FROM (SELECT cedula FROM (SELECT id_repartidor FROM solicitar) AS t1\n" +
                     "INNER JOIN (SELECT cedula FROM repartidor) AS t2\n" +
                     "ON t1.id_repartidor=t2.cedula) AS t3\n" +
                     "INNER JOIN (SELECT id_pedidos,cedula,can_tipo FROM a_domicilio) AS t4\n" +
                     "ON t3.cedula=t4.cedula) AS t5\n" +
                     "INNER JOIN (SELECT id,id_tienda FROM pedidos) AS t6\n" +
                     "ON t5.id_pedidos=t6.id) AS t7\n" +
                     "INNER JOIN (SELECT id,id_municipio FROM tienda) AS t8\n" +
                     "ON t7.id_tienda=t8.id) AS t9\n" +
                     "INNER JOIN (SELECT cedula,nombre FROM empleado) AS t10\n" +
                     "ON t9.cedula=t10.cedula) AS t11\n" +
                     "INNER JOIN (SELECT id,nombre as municipio FROM municipio) AS t12\n" +
                     "ON t11.id_municipio=t12.id) as t13\n" +
                     " GROUP by t13.municipio ) AS t14,\n" +
                     " ( SELECT t13.nombre,max(t13.can_tipo) as maximo FROM (SELECT nombre,can_tipo,municipio FROM (SELECT nombre,can_tipo,id_municipio FROM (SELECT t7.cedula,can_tipo,id_municipio FROM (SELECT id_pedidos,t5.cedula,can_tipo,id_tienda FROM (SELECT id_pedidos,t4.cedula,can_tipo FROM (SELECT cedula FROM (SELECT id_repartidor FROM solicitar) AS t1\n" +
                     "INNER JOIN (SELECT cedula FROM repartidor) AS t2\n" +
                     " ON t1.id_repartidor=t2.cedula) AS t3\n" +
                     "INNER JOIN (SELECT id_pedidos,cedula,can_tipo FROM a_domicilio) AS t4\n" +
                     "ON t3.cedula=t4.cedula) AS t5\n" +
                     " INNER JOIN (SELECT id,id_tienda FROM pedidos) AS t6\n" +
                     "ON t5.id_pedidos=t6.id) AS t7\n" +
                     "INNER JOIN (SELECT id,id_municipio FROM tienda) AS t8\n" +
                     "ON t7.id_tienda=t8.id) AS t9\n" +
                     "INNER JOIN (SELECT cedula,nombre FROM empleado) AS t10\n" +
                     "ON t9.cedula=t10.cedula) AS t11\n" +
                     "INNER JOIN (SELECT id,nombre as municipio FROM municipio) AS t12\n" +
                     "ON t11.id_municipio=t12.id) as t13\n" +
                     " GROUP by t13.nombre ) as t15\n" +
                     "WHERE t15.maximo = t14.maximo\n" +
                     "\n")) {
            while (rs.next()) {
                Consulta_9 p = getConsulta_12(rs);
                consulta_9.add(p);
            }
        }
        return consulta_9;
    }

/////////////////////////////////////////CONSULTA # 13/////////////////////////////////////////////


    private Consulta_9 getConsulta_13(ResultSet rs) throws SQLException {
        Consulta_9 p = new Consulta_9();

        p.setTelefono(rs.getLong("telefono"));
        p.setNombre_cliente_2(rs.getString("nombre_cliente_2"));

        return p;
    }
    public List<Consulta_9>listar_3()throws SQLException {
        List<Consulta_9> consulta_9 = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT nombre_cliente_2,telefono FROM (\t\tSELECT nombre as nombre_cliente_2,telefono,id_pedidos,t5.cedula FROM (\tSELECT nombre,id_pedidos,t3.cedula FROM (SELECT cedula,nombre FROM cliente) AS t3\n" +
                     "INNER JOIN (SELECT cedula,id_pedidos FROM a_domicilio) AS t4\n" +
                     "ON t3.cedula=t4.cedula) AS t5\n" +
                     "INNER JOIN (SELECT cedula,telefono FROM telefono) AS t6\n" +
                     "ON t5.cedula=t6.cedula) AS t7\n" +
                     " INNER JOIN (SELECT id_pedido FROM (SELECT id,id_pedido FROM productos) AS t1\n" +
                     "INNER JOIN (SELECT id_productos,id_categoria FROM pizzas) AS t2\n" +
                     "ON t1.id=t2.id_productos) AS t8\n" +
                     "ON t7.id_pedidos=t8.id_pedido")) {
            while (rs.next()) {
                Consulta_9 p = getConsulta_13(rs);
                consulta_9.add(p);
            }
        }
        return consulta_9;
    }



/////////////////////////////////////////CONSULTA # 14/////////////////////////////////////////////


    private Consulta_9 getConsulta_14(ResultSet rs) throws SQLException {
        Consulta_9 p = new Consulta_9();

        p.setCantidad_domicilio(rs.getLong("cantidad_domicilio"));
        p.setCantidad_recoger(rs.getLong("cantidad_recoger"));
        p.setNombre_producto(rs.getString("nombre_producto"));

        return p;
    }
    public List<Consulta_9>listar_6()throws SQLException {
        List<Consulta_9> consulta_9 = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT nombre_producto,cantidad_domicilio,cantidad_recoger FROM (SELECT C1.id_tienda,cantidad_domicilio,cantidad_recoger FROM (\n" +
                     "SELECT t2.id_tienda,SUM(can_tipo)  AS cantidad_domicilio\n" +
                     "FROM a_domicilio t1, pedidos t2\n" +
                     "where t1.id_pedidos = t2.id AND\n" +
                     "t2.fecha_hora >= '2021-10-01 00:0:00'::timestamp and t2.fecha_hora <= '2021-10-31 23:59:59'::timestamp\n" +
                     "GROUP BY t2.id_tienda) AS C1, (\n" +
                     "SELECT t2.id_tienda,SUM(can_tipo)  AS cantidad_recoger\n" +
                     "FROM recoger t1, pedidos t2\n" +
                     "where t1.id_pedidos = t2.id AND\n" +
                     "t2.fecha_hora >= '2021-10-01 00:0:00'::timestamp and t2.fecha_hora <= '2021-10-31 23:59:59'::timestamp\n" +
                     "GROUP BY t2.id_tienda) C2\n" +
                     "WHERE C1.id_tienda = C2.id_tienda) AS t5\n" +
                     "INNER JOIN (SELECT t4.id_tienda,nombre as nombre_producto FROM (SELECT id,nombre,id_pedido FROM productos) AS t3\n" +
                     "INNER JOIN (SELECT id,id_tienda FROM pedidos) AS t4\n" +
                     "ON t3.id_pedido=t4.id) AS t6\n" +
                     "ON t5.id_tienda=t6.id_tienda")) {
            while (rs.next()) {
                Consulta_9 p = getConsulta_14(rs);
                consulta_9.add(p);
            }
        }
        return consulta_9;
    }


    /////////////////////////////////////////CONSULTA # 15/////////////////////////////////////////////


    private Consulta_9 getConsulta_15(ResultSet rs) throws SQLException {
        Consulta_9 p = new Consulta_9();

        p.setCantidad_2(rs.getLong("cantidad_2"));
        p.setNombre_producto_2(rs.getString("nombre_producto_2"));

        return p;
    }
    public List<Consulta_9>listar_7()throws SQLException {
        List<Consulta_9> consulta_9 = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT t9.nombre_producto_2,max(t9.can_tipo+t10.can_tipo) as cantidad_2 FROM (SELECT nombre as nombre_producto_2,id_pedido,can_tipo FROM (SELECT nombre,id_pedido FROM (SELECT id_productos,id_categoria FROM pizzas\t) AS t3\n" +
                     "INNER JOIN (SELECT id,nombre,id_pedido FROM productos) AS t4\n" +
                     " ON t3.id_productos=t4.id) AS t5\n" +
                     "INNER JOIN (SELECT id_pedidos,can_tipo FROM a_domicilio) AS t6\n" +
                     "ON t5.id_pedido=t6.id_pedidos) AS t9,\n" +
                     "(SELECT nombre,id_pedido,can_tipo FROM (SELECT nombre,id_pedido FROM (SELECT id_productos,id_categoria FROM pizzas\t) AS t3\n" +
                     "INNER JOIN (SELECT id,nombre,id_pedido FROM productos) AS t4\n" +
                     "ON t3.id_productos=t4.id) AS t5\n" +
                     "INNER JOIN (SELECT id_pedidos,can_tipo FROM a_domicilio) AS t6\n" +
                     "ON t5.id_pedido=t6.id_pedidos) as t10\n" +
                     "GROUP BY t9.nombre_producto_2")) {
            while (rs.next()) {
                Consulta_9 p = getConsulta_15(rs);
                consulta_9.add(p);
            }
        }
        return consulta_9;
    }


    /////////////////////////////////////////CONSULTA # 16/////////////////////////////////////////////


    private Consulta_9 getConsulta_16(ResultSet rs) throws SQLException {
        Consulta_9 p = new Consulta_9();

        p.setId_2(rs.getLong("id_2"));
        p.setPreciot(rs.getLong("preciot"));

        return p;
    }
    public List<Consulta_9>listar_8()throws SQLException {
        List<Consulta_9> consulta_9 = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT t1.id_2,preciot FROM (SELECT id as id_2,preciot FROM pedidos) AS t1\n" +
                     "INNER JOIN (SELECT max(preciot) as maxi FROM pedidos) AS t2\n" +
                     "ON t1.preciot=t2.maxi")) {
            while (rs.next()) {
                Consulta_9 p = getConsulta_16(rs);
                consulta_9.add(p);
            }
        }
        return consulta_9;
    }



}