package co.uceva.edu.base.repositories;


import co.uceva.edu.base.models.Departamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartamentosRepositoryJdbcImpl implements RepositoryDepartamento<Departamento> {
    private Connection conn;

    public DepartamentosRepositoryJdbcImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Departamento> listar() throws SQLException {
        List<Departamento> departamento = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT p.* FROM departamento as p  order by p.id ASC")) {
            while (rs.next()) {
                Departamento p = getDepartamentos(rs);
                departamento.add(p);
            }
        }
        return departamento;
    }


    @Override
    public Departamento porId(Long id) throws SQLException {
        Departamento departamentos = null;
        try (PreparedStatement stmt = conn.prepareStatement("SELECT p.* FROM departamento as p  WHERE p.id = ?")) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    departamentos = getDepartamentos(rs);
                }
            }
        }
        return departamentos;
    }

    @Override
    public void guardar(Departamento departamento) throws SQLException {

        String sql;
        if (departamento.getId() != null && departamento.getId() > 0) {
            //sql = "update productos set nombre=?, precio=?, sku=?, categoria_id=? where id=?";
            sql = "update departamento set nombre=? where id=?";
        } else {
            //sql = "insert into productos (nombre, precio, sku, categoria_id, fecha_registro) values (?,?,?,?,?)";
            sql = "insert into departamento (nombre) values (?)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, departamento.getNombre());


            if (departamento.getId() != null && departamento.getId() > 0) {
                stmt.setLong(2, departamento.getId());
            }

            stmt.executeUpdate();
        }
        finally {
            if (this.conn != null && !this.conn.isClosed()) {
                this.conn.close();
            }
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "delete from departamento where id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
        finally {
            if (this.conn != null && !this.conn.isClosed()) {
                this.conn.close();
            }
        }
    }


    private Departamento getDepartamentos(ResultSet rs) throws SQLException {
        Departamento p = new Departamento();
        p.setId(rs.getLong("id"));
        p.setNombre(rs.getString("nombre"));
        return p;
    }
}
