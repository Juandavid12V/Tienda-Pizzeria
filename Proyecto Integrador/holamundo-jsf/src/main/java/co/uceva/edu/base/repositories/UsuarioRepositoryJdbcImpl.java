package co.uceva.edu.base.repositories;


import co.uceva.edu.base.models.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositoryJdbcImpl implements RepositoryUser<Usuario> {

    private Connection conn;

    public UsuarioRepositoryJdbcImpl(Connection conn) {
        this.conn = conn;
    }


    @Override
    public List<Usuario> listar() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT p.* FROM usuarios as p  order by p.id ASC")) {
            while (rs.next()) {
                Usuario  p= getUsuario(rs);
                usuarios.add(p);
            }
        }
        return usuarios;
    }

    @Override
    public Usuario login(String login, String password) throws SQLException {
        Usuario usuario = null;
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usuarios  WHERE login = ? and password = ?")) {
            stmt.setString(1, login);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = getUsuario(rs);
                }
            }
        }
        return usuario;
    }

    @Override
    public void guardar(Usuario usuario) throws SQLException {
        String sql;
        if (usuario.getId() != null && usuario.getId() > 0) {
            sql = "update usuarios set nombre=?, login=?, password=?, tipo=? where id=?";
        } else {

            sql = "insert into usuarios (nombre, login, password, tipo) values (?,?,?,?)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getLogin());
            stmt.setString(3, usuario.getPassword());
            stmt.setString(4,usuario.getTipo());

            if (usuario.getId() != null && usuario.getId() > 0) {
                stmt.setLong(5, usuario.getId());
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
        String sql = "delete from usuarios where id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } finally {
        if (this.conn != null && !this.conn.isClosed()) {
            this.conn.close();
        }
    }
    }

    @Override
    public Usuario porId(Long id) throws SQLException {
        Usuario usuario = null;
        try (PreparedStatement stmt = conn.prepareStatement("SELECT p.* FROM usuarios as p  WHERE p.id = ?")) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = getUsuario(rs);
                }
            }
        }
        return usuario;
    }


    private Usuario getUsuario(ResultSet rs) throws SQLException {
        Usuario p = new Usuario();
        p.setId(rs.getLong("id"));
        p.setNombre(rs.getString("nombre"));
        p.setPassword(rs.getString("password"));
        p.setLogin(rs.getString("login"));
        p.setTipo(rs.getString("tipo"));

        return p;
    }

    @Override
    public Usuario tipologin(String login, String password, String tipo) throws SQLException {
        Usuario usuario = null;
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usuarios  WHERE login = ? and password = ? and tipo = ?")) {
            stmt.setString(1, login);
            stmt.setString(2, password);
            stmt.setString(3, tipo);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = getUsuario(rs);
                }
            }
        }
        return usuario;
    }
}
