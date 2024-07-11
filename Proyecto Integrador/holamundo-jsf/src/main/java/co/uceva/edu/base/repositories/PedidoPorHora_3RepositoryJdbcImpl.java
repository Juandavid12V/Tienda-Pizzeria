package co.uceva.edu.base.repositories;

import co.uceva.edu.base.reportes.PedidosHora_2;
import co.uceva.edu.base.reportes.PedidosHora_3;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PedidoPorHora_3RepositoryJdbcImpl implements RepositoryReportePedidosHora_3<PedidosHora_3> {
    private Connection conn;

    public PedidoPorHora_3RepositoryJdbcImpl(Connection conn) {
        this.conn = conn;
    }


    private List<PedidosHora_3> getPedidoHora_3(ResultSet rs) throws SQLException {
        List<PedidosHora_3> pedidosHora_3 = new ArrayList<>();
        PedidosHora_3 p = new PedidosHora_3();
        p.setTiempo("t1");
        p.setCantidad(rs.getLong("t1"));
        pedidosHora_3.add(p);

        p = new PedidosHora_3();
        p.setTiempo("t2");
        p.setCantidad(rs.getLong("t2"));
        pedidosHora_3.add(p);

        p = new PedidosHora_3();
        p.setTiempo("t3");
        p.setCantidad(rs.getLong("t3"));
        pedidosHora_3.add(p);

        p = new PedidosHora_3();
        p.setTiempo("t4");
        p.setCantidad(rs.getLong("t4"));
        pedidosHora_3.add(p);

        p = new PedidosHora_3();
        p.setTiempo("t5");
        p.setCantidad(rs.getLong("t5"));
        pedidosHora_3.add(p);

        p = new PedidosHora_3();
        p.setTiempo("t6");
        p.setCantidad(rs.getLong("t6"));
        pedidosHora_3.add(p);

        p = new PedidosHora_3();
        p.setTiempo("t7");
        p.setCantidad(rs.getLong("t7"));
        pedidosHora_3.add(p);



        return pedidosHora_3;
    }
    public List<PedidosHora_3>porHora_3()throws SQLException {
        List<PedidosHora_3> pedidosHora_3 = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT SUM(CASE WHEN C2.CONSECUTIVO IN (1,2,3) THEN C2.CANTIDAD END) AS T1,\n" +
                     "       SUM(CASE WHEN C2.CONSECUTIVO IN (4,5,6) THEN C2.CANTIDAD END) AS T2,\n" +
                     "       SUM(CASE WHEN C2.CONSECUTIVO IN (7,8,9) THEN C2.CANTIDAD END) AS T3,\n" +
                     "       SUM(CASE WHEN C2.CONSECUTIVO IN (10,11,12) THEN C2.CANTIDAD END) AS T4,\n" +
                     "       SUM(CASE WHEN C2.CONSECUTIVO IN (13,14,15) THEN C2.CANTIDAD END) AS T5,\n" +
                     "       SUM(CASE WHEN C2.CONSECUTIVO IN (16,17,18) THEN C2.CANTIDAD END) AS T6,\n" +
                     "       SUM(CASE WHEN C2.CONSECUTIVO IN (19,20,21) THEN C2.CANTIDAD END) AS T7\n" +
                     "FROM (\n" +
                     "\n" +
                     "SELECT row_number() OVER (ORDER BY C1.DIA_DEL_MES,C1.DIA_SEMANA) AS CONSECUTIVO,\n" +
                     "       C1.DIA_DEL_MES,\n" +
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
                     "FROM (\n" +
                     "         SELECT To_Char(fecha_hora, 'DD') DIA_DEL_MES, To_Char(fecha_hora, 'D') DIA_SEMANA, count(*) as cantidad\n" +
                     "         from pedidos\n" +
                     "         group by To_Char(fecha_hora, 'DD'), To_Char(fecha_hora, 'D')\n" +
                     "         order by 1\n" +
                     "     ) AS C1\n" +
                     "ORDER BY C1.DIA_DEL_MES,C1.DIA_SEMANA) C2")) {
            while (rs.next()) {
                pedidosHora_3 = getPedidoHora_3(rs);

            }
        }
        return pedidosHora_3;
    }
}