package co.uceva.edu.base.beans;


import co.uceva.edu.base.models.Pedido;
import co.uceva.edu.base.services.PedidoService;
import co.uceva.edu.base.services.PedidoServiceJdbcImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@Named
@RequestScoped
public class PedidoEliminarServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = (Connection) req.getAttribute("conn");
        PedidoService service = new PedidoServiceJdbcImpl(conn);
        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }
        if (id > 0) {
            Optional<Pedido> o = service.porId(id);
            if (o.isPresent()) {
                service.eliminar(id);
                resp.sendRedirect(req.getContextPath()+ "/pedidos.xhtml");
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No existe el Pedido en la base de datos!");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Error el id es null, se debe enviar como parametro en la url!");
        }
    }
}
