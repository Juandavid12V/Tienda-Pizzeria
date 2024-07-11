package co.uceva.edu.base.repositories;

import co.uceva.edu.base.reportes.PedidosHora;
import co.uceva.edu.base.reportes.PedidosResultado;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PedidoPorHora_ResultadoRepositoryJdbcImpl implements RepositoryReportePedidosHora_Resultado<PedidosResultado> {
    private Connection conn;

    public PedidoPorHora_ResultadoRepositoryJdbcImpl(Connection conn) {
        this.conn = conn;
    }

    private PedidosResultado getPedidoHoraResultado (ResultSet rs) throws SQLException {
        PedidosResultado p = new PedidosResultado();

        p.setTiempo_resultante(rs.getDouble("tiempo_resultante"));
        p.setCantidad(rs.getLong("cantidad"));

        return p;
    }
    public List<PedidosResultado>porHora_Resultado()throws SQLException {
        List<PedidosResultado> pedidosResultados = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT tiempo_resultante,cantidad  FROM resultados_mate as p  order by tiempo ")) {
            while (rs.next()) {
                PedidosResultado p = getPedidoHoraResultado(rs);
                pedidosResultados.add(p);
            }
        }
        return pedidosResultados;
    }
}