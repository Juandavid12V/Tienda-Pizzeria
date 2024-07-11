package co.uceva.edu.base.repositories;

import co.uceva.edu.base.reportes.PedidosHora;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoPorHoraRepositoryJdbcImpl implements RepositoryReportePedidosHora<PedidosHora> {
    private Connection conn;

    public PedidoPorHoraRepositoryJdbcImpl(Connection conn) {
        this.conn = conn;
    }


    private PedidosHora getPedidoHora(ResultSet rs) throws SQLException {
        PedidosHora p = new PedidosHora();

        p.setHora(rs.getTimestamp("fecha_hora"));
        p.setCantidad(rs.getLong("cantidad"));

        return p;
    }
    public List<PedidosHora>porHora()throws SQLException {
        List<PedidosHora> pedidosHora = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT fecha_hora,count(*) as cantidad from pedidos group by fecha_hora order by fecha_hora")) {
            while (rs.next()) {
                PedidosHora p = getPedidoHora(rs);
                pedidosHora.add(p);
            }
        }
        return pedidosHora;
    }
}