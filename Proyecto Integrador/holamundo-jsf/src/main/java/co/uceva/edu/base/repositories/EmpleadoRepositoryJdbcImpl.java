package co.uceva.edu.base.repositories;

import co.uceva.edu.base.models.Empleado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoRepositoryJdbcImpl implements RepositoryEmpleado<Empleado> {
    private Connection conn;

    public EmpleadoRepositoryJdbcImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Empleado> listar() throws SQLException {
        List<Empleado> empleado = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT p.* FROM empleado as p  order by p.cedula ASC ")) {
            while (rs.next()) {
                Empleado p = getEmpleado(rs);
                empleado.add(p);
            }
        }
        return empleado;
    }

    @Override
    public Empleado porId(Long id) throws SQLException {
        Empleado empleado = null;
        try (PreparedStatement stmt = conn.prepareStatement("SELECT p.* FROM empleado as p  WHERE p.cedula = ?  ")) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    empleado = getEmpleado(rs);
                }
            }
        }
        return empleado;
    }

    @Override
    public void guardar(Empleado empleado) throws SQLException {
        String sql;
        if (empleado.getCedula() != null && empleado.getCedula() > 0) {
            //sql = "update Pedidos set nombre=?, precio=?, sku=?, categoria_id=? where id=?";
            sql = "update empleado set nombre=?, apellido=?, telefono=?, id_tienda=?, id_municipio=? where cedula=?";
        } else {
            //sql = "insert into Pedidos (nombre, precio, sku, categoria_id, fecha_registro) values (?,?,?,?,?)";
            sql = "SET TIMEZONE='America/Bogota'; insert into empleado (nombre, apellido, telefono, id_tienda, id_municipio) values (?,?,?,?,?)";        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, empleado.getNombre());
            stmt.setString(2, empleado.getApellido());
            stmt.setString(3, empleado.getTelefono());
            stmt.setLong(4, empleado.getId_tienda());
            stmt.setLong(5, empleado.getId_municipio());

            if (empleado.getCedula() != null && empleado.getCedula() > 0) {
                stmt.setLong(6, empleado.getCedula());
            }

            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "delete from empleado where cedula=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    private Empleado getEmpleado(ResultSet rs) throws SQLException {
        Empleado p = new Empleado();
        p.setCedula(rs.getLong("cedula"));
        p.setNombre(rs.getString("nombre"));
        p.setApellido(rs.getString("apellido"));
        p.setTelefono(rs.getString("telefono"));
        p.setId_tienda(rs.getLong("id_tienda"));
        p.setId_municipio(rs.getLong("id_municipio"));
        return p;
    }

}