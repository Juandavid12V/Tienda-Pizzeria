package co.uceva.edu.base.repositories;


import co.uceva.edu.base.models.Tienda;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TiendaRepositoryJdbcImpl implements RepositoryTienda<Tienda> {
    private Connection conn;

    public TiendaRepositoryJdbcImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Tienda> listar() throws SQLException {
        List<Tienda> tiendas = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT p.* FROM tienda as p  order by p.id ASC")) {
            while (rs.next()) {
                Tienda p = getTienda(rs);
                tiendas.add(p);
            }
        }
        return tiendas;
    }


    @Override
    public Tienda porId(Long id) throws SQLException {
        Tienda tienda = null;
        try (PreparedStatement stmt = conn.prepareStatement("SELECT p.* FROM tienda as p  WHERE p.id = ?")) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    tienda = getTienda(rs);
                }
            }
        }
        return tienda;
    }

    @Override
    public void guardar(Tienda tienda) throws SQLException {

        String sql;
        if (tienda.getId() != null && tienda.getId() > 0) {
            //sql = "update productos set nombre=?, precio=?, sku=?, categoria_id=? where id=?";
            sql = "update tienda set direccion=? where id=?";
        } else {
            //sql = "insert into productos (nombre, precio, sku, categoria_id, fecha_registro) values (?,?,?,?,?)";
            sql = "insert into tienda (direccion) values (?)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tienda.getDireccion());

            if (tienda.getId() != null && tienda.getId() > 0) {
                stmt.setLong(2, tienda.getId());
            }

            stmt.executeUpdate();
        }finally {
            if (this.conn != null && !this.conn.isClosed()) {
                this.conn.close();
            }
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "delete from tienda where id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }finally {
            if (this.conn != null && !this.conn.isClosed()) {
                this.conn.close();
            }
        }
    }


    private Tienda getTienda(ResultSet rs) throws SQLException {
        Tienda p = new Tienda();
        p.setId(rs.getLong("id"));
        p.setDireccion(rs.getString("direccion"));
        return p;
    }
}
