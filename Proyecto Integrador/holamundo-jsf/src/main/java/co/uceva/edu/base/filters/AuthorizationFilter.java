package co.uceva.edu.base.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AuthFilter", urlPatterns = { "*.xhtml" })
public class AuthorizationFilter implements Filter {

    public AuthorizationFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest reqt = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            HttpSession ses = reqt.getSession(false);

            String reqURI = reqt.getRequestURI();
            if (reqURI.indexOf("/login.xhtml") >= 0
                    || reqURI.indexOf("/editar-pedido.xhtml") >= 0
                    || reqURI.indexOf("/crear-usuario.xhtml") >= 0
                    || reqURI.indexOf("/crear-visitante.xhtml") >= 0
                    || reqURI.indexOf("/visitante.xhtml") >= 0
                    || reqURI.indexOf("/productos_2.xhtml") >= 0
                    || reqURI.indexOf("/crear-pedido.xhtml") >= 0
                    || reqURI.indexOf("/pedidos_Visitante.xhtml") >= 0
                    || reqURI.indexOf("/crear-pedido_visitante.xhtml") >= 0
                    || reqURI.indexOf("pedidoForm.crear()") >= 0
                    || reqURI.indexOf("/pedidoForm.crear()") >= 0
                    || (ses != null && ses.getAttribute("username") != null)
                    || reqURI.indexOf("/public/") >= 0
                    || reqURI.contains("javax.faces.resource"))
                chain.doFilter(request, response);
            else
                resp.sendRedirect(reqt.getContextPath() + "/faces/login.xhtml");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void destroy() {

    }
}