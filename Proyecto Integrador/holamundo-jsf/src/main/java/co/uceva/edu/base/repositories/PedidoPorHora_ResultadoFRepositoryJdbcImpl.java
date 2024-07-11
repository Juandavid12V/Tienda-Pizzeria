package co.uceva.edu.base.repositories;

import co.uceva.edu.base.reportes.PedidosHora_3;
import co.uceva.edu.base.reportes.PedidosResultado;
import co.uceva.edu.base.reportes.PedidosResultadoF;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PedidoPorHora_ResultadoFRepositoryJdbcImpl  implements RepositoryReportePedidosHora_ResultadoF<PedidosResultadoF> {
    private Connection conn;

    public PedidoPorHora_ResultadoFRepositoryJdbcImpl(Connection conn) {
        this.conn = conn;
    }
    private PedidosResultadoF getPedidoHoraResultadoF (ResultSet rs) throws SQLException {
        PedidosResultadoF p = new PedidosResultadoF();

        p.setTiempo(rs.getString("tiempo"));
        p.setCantidadPrima(rs.getDouble("cantidadprima"));

        return p;
    }
    public List<PedidosResultadoF>porHora_ResultadoF()throws SQLException {
        List<PedidosResultadoF> pedidosResultadosF = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT tiempo,cantidadprima FROM resultados_mate as p  order by tiempo")) {
            while (rs.next()) {
                PedidosResultadoF p = getPedidoHoraResultadoF(rs);
                pedidosResultadosF.add(p);
            }
        }
        return pedidosResultadosF;
    }
}