package co.uceva.edu.base.repositories;

import co.uceva.edu.base.models.Categoria;
import co.uceva.edu.base.models.Departamento;
import co.uceva.edu.base.models.Pedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaRepositoryJdbcImpl implements RepositoryCategoria<Categoria> {
    private Connection conn;

    public CategoriaRepositoryJdbcImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Categoria> listar() throws SQLException {
        List<Categoria>  categorias = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT p.* FROM categoria as p  order by p.id ASC")) {
            while (rs.next()) {
                Categoria p = getCategoria(rs);
                categorias.add(p);
            }
        }
        return categorias;
    }

    @Override
    public Categoria porId(Long id) throws SQLException {
        Categoria categoria = null;
        try (PreparedStatement stmt = conn.prepareStatement("SELECT p.* FROM categoria as p  WHERE p.id = ?  ")) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    categoria = getCategoria(rs);
                }
            }
        }
        return categoria;
    }

    @Override
    public void guardar(Categoria categorias) throws SQLException {


        String sql;
        if (categorias.getId() != null && categorias.getId() > 0) {
            sql = "update categoria set nombre=? where id=?";
        } else {
            sql = "insert into categoria (nombre) values (?)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, categorias.getNombre());

            if (categorias.getId() != null && categorias.getId() > 0) {
                stmt.setLong(2, categorias.getId());
            }
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "delete from categoria where id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }finally {
            if (this.conn != null && !this.conn.isClosed()) {
                this.conn.close();
            }
        }
    }




    private Categoria getCategoria(ResultSet rs) throws SQLException {
        Categoria p = new Categoria();
        p.setId(rs.getLong("id"));
        p.setNombre(rs.getString("nombre"));

        return p;
    }
}