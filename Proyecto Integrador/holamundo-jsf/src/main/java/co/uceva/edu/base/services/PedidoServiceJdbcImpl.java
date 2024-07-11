package co.uceva.edu.base.services;

import co.uceva.edu.base.models.Pedido;
import co.uceva.edu.base.reportes.*;
import co.uceva.edu.base.repositories.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PedidoServiceJdbcImpl implements PedidoService{
    private Repository<Pedido> repositoryJdbc;
    private RepositoryReportePedidosHora<PedidosHora> repositoryJdbcReporte;
    private RepositoryReportePedidosHora_2<PedidosHora_2> repositoryJdbcReporte_2;
    private RepositoryReportePedidosHora_3<PedidosHora_3> repositoryJdbcReporte_3;
    private RepositoryReportePedidosHora_Resultado<PedidosResultado> repositoryJdbcReporte_Resultado;
    private RepositoryReportePedidosHora_ResultadoF<PedidosResultadoF> repositoryJdbcReporte_ResultadoF;


    public PedidoServiceJdbcImpl(Connection connection) {
        this.repositoryJdbc = new PedidoRepositoryJdbcImpl(connection);
        this.repositoryJdbcReporte = new PedidoPorHoraRepositoryJdbcImpl(connection);
        this.repositoryJdbcReporte_2 = new PedidoPorHora_2RepositoryJdbcImpl(connection);
        this.repositoryJdbcReporte_3 = new PedidoPorHora_3RepositoryJdbcImpl(connection);
        this.repositoryJdbcReporte_Resultado = new PedidoPorHora_ResultadoRepositoryJdbcImpl(connection);
        this.repositoryJdbcReporte_ResultadoF = new PedidoPorHora_ResultadoFRepositoryJdbcImpl(connection);

    }


    @Override
    public List<Pedido> listar() {
        try {
            return repositoryJdbc.listar();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Pedido> porId(Long id) {
        try {
            return Optional.ofNullable(repositoryJdbc.porId(id));
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());

        }
    }

    @Override
    public void guardar(Pedido pedido) {
        try {
            repositoryJdbc.guardar(pedido);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }


    @Override
    public void eliminar(Long id) {
        try {
            repositoryJdbc.eliminar(id);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public List<PedidosHora> porHora() {
        try {
            return repositoryJdbcReporte.porHora();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public List<PedidosHora_2> porHora_2() {
        try {
            return repositoryJdbcReporte_2.porHora_2();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public List<PedidosHora_3> porHora_3() {
        try {
            return repositoryJdbcReporte_3.porHora_3();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public List<PedidosResultado> porHora_Resultado() {
        try {
            return repositoryJdbcReporte_Resultado.porHora_Resultado();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public List<PedidosResultadoF> porHora_ResultadoF() {
        try {
            return repositoryJdbcReporte_ResultadoF.porHora_ResultadoF();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }


}