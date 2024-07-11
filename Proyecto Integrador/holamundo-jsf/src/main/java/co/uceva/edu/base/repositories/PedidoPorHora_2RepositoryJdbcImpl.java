package co.uceva.edu.base.repositories;

import co.uceva.edu.base.reportes.PedidosHora_2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PedidoPorHora_2RepositoryJdbcImpl implements RepositoryReportePedidosHora_2<PedidosHora_2> {
    private Connection conn;

    public PedidoPorHora_2RepositoryJdbcImpl(Connection conn) {
        this.conn = conn;
    }


    private PedidosHora_2 getPedidoHora_2(ResultSet rs) throws SQLException {
        PedidosHora_2 p = new PedidosHora_2();

        p.setDia_del_mes(rs.getLong("dia_del_mes"));
        p.setDia(rs.getString("dia"));
        p.setCantidad(rs.getLong("cantidad"));

        return p;
    }
    public List<PedidosHora_2>porHora_2()throws SQLException {
        List<PedidosHora_2> pedidosHora_2 = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT C1.DIA_DEL_MES,\n" +
                     "       CASE WHEN C1.DIA_SEMANA='2' THEN 'LUNES'\n" +
                     "            WHEN C1.DIA_SEMANA='3' THEN 'MARTES'\n" +
                     "            WHEN C1.DIA_SEMANA='4' THEN 'MIERCOLES'\n" +
                     "            WHEN C1.DIA_SEMANA='5' THEN 'JUEVES'\n" +
                     "            WHEN C1.DIA_SEMANA='6' THEN 'VIERNES'\n" +
                     "            WHEN C1.DIA_SEMANA='7' THEN 'SABADO'\n" +
                     "            WHEN C1.DIA_SEMANA='1' THEN 'DOMINGO'\n" +
                     "\n" +
                     "           END AS DIA,\n" +
                     "       C1.cantidad\n" +
                     "\n" +
                     "       FROM (\n" +
                     "                  SELECT To_Char(fecha_hora, 'DD') DIA_DEL_MES, To_Char(fecha_hora, 'D') DIA_SEMANA, count(*) as cantidad\n" +
                     "                  from pedidos\n" +
                     "                  group by To_Char(fecha_hora, 'DD'), To_Char(fecha_hora, 'D')\n" +
                     "                  order by 1\n" +
                     "              ) AS C1\n" +
                     "ORDER BY C1.DIA_DEL_MES,C1.DIA_SEMANA;")) {
            while (rs.next()) {
                PedidosHora_2 p = getPedidoHora_2(rs);
                pedidosHora_2.add(p);
            }
        }
        return pedidosHora_2;
    }
}