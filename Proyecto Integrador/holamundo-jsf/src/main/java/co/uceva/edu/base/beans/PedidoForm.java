package co.uceva.edu.base.beans;

import co.uceva.edu.base.models.Pedido;
import co.uceva.edu.base.reportes.*;
import co.uceva.edu.base.services.*;
import co.uceva.edu.base.util.ConexionBaseDatos;
import org.chartistjsf.model.chart.*;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Named
@SessionScoped
public class PedidoForm implements Serializable {

    private Long id;
    private Date fecha_hora;
    private double precioT;
    private double cantidad;
    private double dia_del_mes;
    private Pedido editar_pedidos;
    private String dia;
    private Long id_tienda;

    public Long getId_tienda() {
        return id_tienda;
    }

    public void setId_tienda(Long id_tienda) {
        this.id_tienda = id_tienda;
    }


    public Pedido getEditar_pedidos() {
        return editar_pedidos;
    }

    public void setEditar_pedidos(Pedido editar_pedidos) {
        this.editar_pedidos = editar_pedidos;
    }

    double a = 1.2917 * Math. pow(1,4) -  18.3333 * Math. pow(1,3) +  87.415 * Math. pow(1,2) -154.8 *(1)+216.4266;
    double b = 1.2917 * Math. pow(2,4) -  18.3333 * Math. pow(2,3) +  87.415 * Math. pow(2,2) -154.8 *(2)+216.4266;
    double c = 1.2917 * Math. pow(3,4) -  18.3333 * Math. pow(3,3) +  87.415 * Math. pow(3,2) -154.8 *(3)+216.4266;
    double d = 1.2917 * Math. pow(4,4) -  18.3333 * Math. pow(4,3) +  87.415 * Math. pow(4,2) -154.8 *(4)+216.4266;
    double e = 1.2917 * Math. pow(5,4) -  18.3333 * Math. pow(5,3) +  87.415 * Math. pow(5,2) -154.8 *(5)+216.4266;
    double f = 1.2917 * Math. pow(6,4) -  18.3333 * Math. pow(6,3) +  87.415 * Math. pow(6,2) -154.8 *(6)+216.4266;
    double g = 1.2917 * Math. pow(7,4) -  18.3333 * Math. pow(7,3) +  87.415 * Math. pow(7,2) -154.8 *(7)+216.4266;


    public LineChartModel getLineChartModel() {
        return lineChartModel;
    }

    public void setLineChartModel(LineChartModel lineChartModel) {
        this.lineChartModel = lineChartModel;
    }

    public List<PedidosHora_2> getPedidos_2() {
        return Pedidos_2;
    }

    public void setPedidos_2(List<PedidosHora_2> pedidos_2) {
        Pedidos_2 = pedidos_2;
    }


    private LineChartModel lineChartModel;
    private LineChartModel lineChartModel2;


    public void setLineChartModel2(LineChartModel lineChartModel2) {
        this.lineChartModel2 = lineChartModel2;
    }
    public LineChartModel getLineChartModel2() {
        return lineChartModel2;
    }

    public PedidoForm() {
        createLineModel();
        createLineModel2();
    }


    public double getDia_del_mes() {return dia_del_mes;}

    public void setDia_del_mes(double dia_del_mes) {this.dia_del_mes = dia_del_mes;}

    public String getDia() {return dia;}

    public void setDia(String dia) {this.dia = dia;}

    public double getCantidad() {return cantidad;}

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(Date fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public double getPrecioT() {
        return precioT;
    }

    public void setPrecioT(double precioT) {
        this.precioT = precioT;
    }

    public List<Pedido> getPedidos() {
        return Pedidos;
    }


    public String crear() {
        try {
            PedidoService PedidoService = new PedidoServiceJdbcImpl(ConexionBaseDatos.getConnection());
            Pedido Pedido = new Pedido();
            Pedido.setId(this.id);
            Pedido.setFecha_hora((Timestamp) this.fecha_hora);
            Pedido.setPrecioT(this.precioT);
            Pedido.setId_tienda(this.id_tienda);

            PedidoService.guardar(Pedido);

            System.out.println(id);
            System.out.println(fecha_hora);
            System.out.println(precioT);
            System.out.println(id_tienda);
            return "pedidos.xhtml?faces-redirect=true";
        } catch (SQLException e) {
            e.printStackTrace();
            return "crear-pedido.xhtml?faces-redirect=true";
        }
    }

    public String guardar_edi() {
        try {
            PedidoService pedidoService = new PedidoServiceJdbcImpl(ConexionBaseDatos.getConnection());
            Pedido pedido = new Pedido();
            pedido.setId(this.editar_pedidos.getId());
            pedido.setFecha_hora(this.editar_pedidos.getFecha_hora());
            pedido.setPrecioT(this.editar_pedidos.getPrecioT());
            pedido.setId_tienda(this.editar_pedidos.getId_tienda());

            pedidoService.guardar(pedido);

            System.out.println(editar_pedidos.getId());
            System.out.println(editar_pedidos.getFecha_hora());
            System.out.println(editar_pedidos.getPrecioT());
            System.out.println(editar_pedidos.getId_tienda());

            return "pedidos.xhtml?faces-redirect=true";
        } catch (SQLException e) {
            e.printStackTrace();
            return "crear-pedido.xhtml?faces-redirect=true";
        }
    }


    public String editar( long idEditar){
        try {
            PedidoService pedidoService = new PedidoServiceJdbcImpl(ConexionBaseDatos.getConnection());
            Optional<Pedido> pedido = pedidoService.porId(idEditar);

            if(pedido.isPresent()){
                this.editar_pedidos = pedido.get();
                return "editar-pedido.xhtml?faces-redirect=true";
            }else{
                return "pedidos.xhtml?faces-redirect=true";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return "crear-pedido.xhtml?faces-redirect=true";
        }
    }

    public void setPedidos(List<Pedido> Pedidos) {
        this.Pedidos = Pedidos;
    }

    private List<Pedido> Pedidos;

    private List<PedidosHora_2> Pedidos_2;

    public List<Pedido> listar() throws SQLException {
        PedidoService pedidoService = new PedidoServiceJdbcImpl(ConexionBaseDatos.getConnection());
        return pedidoService.listar();
    }

    public List<PedidosHora> listarPorHora() throws SQLException {
        PedidoService pedidoService = new PedidoServiceJdbcImpl(ConexionBaseDatos.getConnection());
        return pedidoService.porHora();
    }

    public List<PedidosHora_2> listarPorHora_2() throws SQLException {
        PedidoService pedidoService = new PedidoServiceJdbcImpl(ConexionBaseDatos.getConnection());
        return pedidoService.porHora_2();
    }

    public List<PedidosHora_3> listarPorHora_3() throws SQLException {
        PedidoService pedidoService = new PedidoServiceJdbcImpl(ConexionBaseDatos.getConnection());
        return pedidoService.porHora_3();
    }

    public List<PedidosResultado> listarResultado() throws SQLException {
        PedidoService pedidoService = new PedidoServiceJdbcImpl(ConexionBaseDatos.getConnection());
        return pedidoService.porHora_Resultado();
    }

    public List<PedidosResultadoF> listarResultadoF() throws SQLException {
        PedidoService pedidoService = new PedidoServiceJdbcImpl(ConexionBaseDatos.getConnection());
        return pedidoService.porHora_ResultadoF();
    }

    public void createLineModel() {

        lineChartModel = new LineChartModel();
        lineChartModel.setAspectRatio(AspectRatio.GOLDEN_SECTION);

        lineChartModel.addLabel("1");
        lineChartModel.addLabel("2");
        lineChartModel.addLabel("3");
        lineChartModel.addLabel("4");
        lineChartModel.addLabel("5");
        lineChartModel.addLabel("6");
        lineChartModel.addLabel("7");
        LineChartSeries lineChartSeries1 = new LineChartSeries();
        lineChartSeries1.setName("Tiempo");


        lineChartSeries1.set(a);
        lineChartSeries1.set(b);
        lineChartSeries1.set(c);
        lineChartSeries1.set(d);
        lineChartSeries1.set(e);
        lineChartSeries1.set(f);
        lineChartSeries1.set(g);

        LineChartSeries lineChartSeries2 = new LineChartSeries();
        lineChartSeries2.setName("Resultado");


        lineChartSeries2.set(131);
        lineChartSeries2.set(124);
        lineChartSeries2.set(164);
        lineChartSeries2.set(150);
        lineChartSeries2.set(158);
        lineChartSeries2.set(128);
        lineChartSeries2.set(219);
        Axis xAxis = lineChartModel.getAxis(AxisType.X);
        lineChartModel.addSeries(lineChartSeries1);
        lineChartModel.addSeries(lineChartSeries2);
        lineChartModel.setAnimateAdvanced(true);
        lineChartModel.setShowTooltip(true);
    }

    public void createLineModel2() {

        lineChartModel2 = new LineChartModel();
        lineChartModel2.setAspectRatio(AspectRatio.GOLDEN_SECTION);


        lineChartModel2.addLabel("2");
        lineChartModel2.addLabel("3");
        lineChartModel2.addLabel("4");
        lineChartModel2.addLabel("5");
        lineChartModel2.addLabel("6");

        LineChartSeries lineChartSeries2 = new LineChartSeries();
        lineChartSeries2.setName("Cantidad prima");


        lineChartSeries2.set(16.5);
        lineChartSeries2.set(13);
        lineChartSeries2.set(-3);
        lineChartSeries2.set(-11);
        lineChartSeries2.set(30.5);



        Axis xAxis = lineChartModel2.getAxis(AxisType.X);
        lineChartModel2.addSeries(lineChartSeries2);
        lineChartModel2.setAnimateAdvanced(true);
        lineChartModel2.setShowTooltip(true);
    }

    public String eliminar(Long id){

        try {
            PedidoService pedidoService = new PedidoServiceJdbcImpl(ConexionBaseDatos.getConnection());
            pedidoService.eliminar(id);
            return "pedidos.xhtml?faces-redirect=true";
        } catch (SQLException e) {
            e.printStackTrace();
            return "pedidos.xhtml?faces-redirect=true";
        }
    }
}