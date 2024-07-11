package co.uceva.edu.base.repositories;

import co.uceva.edu.base.models.Pedido;
import co.uceva.edu.base.reportes.PedidosHora;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoRepositoryJdbcImpl implements Repository<Pedido> {
    private Connection conn;

    public PedidoRepositoryJdbcImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Pedido> listar() throws SQLException {
        List<Pedido> pedidos = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT p.* FROM Pedidos as p  order by p.id ASC ")) {
            while (rs.next()) {
                Pedido p = getPedido(rs);
                pedidos.add(p);
            }
        }
        return pedidos;
    }

    @Override
    public Pedido porId(Long id) throws SQLException {
        Pedido pedido = null;
        try (PreparedStatement stmt = conn.prepareStatement("SELECT p.* FROM Pedidos as p  WHERE p.id = ?  ")) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    pedido = getPedido(rs);
                }
            }
        }
        return pedido;
    }

    @Override
    public void guardar(Pedido pedido) throws SQLException {
        String sql;
        if (pedido.getId() != null && pedido.getId() > 0) {
            sql = "update Pedidos set preciot=?,id_tienda=? where id=?";
        } else {
            sql = "SET TIMEZONE='America/Bogota'; insert into pedidos (precioT,id_tienda) values (?,?)";        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, pedido.getPrecioT());
            stmt.setLong(2, pedido.getId_tienda());

            if (pedido.getId() != null && pedido.getId() > 0) {
                stmt.setLong(3, pedido.getId());
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
        String sql = "delete from Pedidos where id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }finally {
            if (this.conn != null && !this.conn.isClosed()) {
                this.conn.close();
            }
        }
    }

    private Pedido getPedido(ResultSet rs) throws SQLException {
        Pedido p = new Pedido();
        p.setId(rs.getLong("id"));
        p.setFecha_hora(rs.getTimestamp("fecha_hora"));
        p.setPrecioT(rs.getDouble("preciot"));
        p.setId_tienda(rs.getLong("id_tienda"));
        return p;
    }

}