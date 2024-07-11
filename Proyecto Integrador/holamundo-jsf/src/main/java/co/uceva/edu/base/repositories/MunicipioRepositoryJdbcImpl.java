package co.uceva.edu.base.repositories;


import co.uceva.edu.base.models.Municipio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MunicipioRepositoryJdbcImpl implements RepositoryMunicipio<Municipio> {
    private Connection conn;

    public MunicipioRepositoryJdbcImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Municipio> listar() throws SQLException {
        List<Municipio> municipio = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT p.* FROM municipio as p  order by p.id ASC")) {
            while (rs.next()) {
                Municipio p = getMunicipio(rs);
                municipio.add(p);
            }

        }
        return municipio;

    }


    @Override
    public Municipio porId(Long id) throws SQLException {
        Municipio municipio = null;
        try (PreparedStatement stmt = conn.prepareStatement("SELECT p.* FROM municipio as p  WHERE p.id = ?")) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    municipio = getMunicipio(rs);
                }
            }
        }
        return municipio;
    }

    @Override
    public void guardar(Municipio municipio) throws SQLException {

        String sql;
        if (municipio.getId() != null && municipio.getId() > 0) {
            sql = "update municipio set nombre=?,  id_departamento=? where id=?";
        } else {
            sql = "insert into municipio (nombre,id_departamento) values (?,?)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, municipio.getNombre());
            stmt.setLong(2, municipio.getId_departamento());


            if (municipio.getId() != null && municipio.getId() > 0) {
                stmt.setLong(3, municipio.getId());
            }

            stmt.executeUpdate();
        } finally {
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


    private Municipio getMunicipio(ResultSet rs) throws SQLException {
        Municipio p = new Municipio();
        p.setId(rs.getLong("id"));
        p.setNombre(rs.getString("nombre"));
        p.setId_departamento(rs.getLong("id_departamento"));
        return p;

    }



}
