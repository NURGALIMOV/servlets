package tech.itpark.servlet;

import org.springframework.context.ConfigurableApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

public class MainServlet extends HttpServlet {

    private Map<String, Map<String, Handler>> routes;
    private ErrorController errorCtrl;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            final var context = (ConfigurableApplicationContext) getServletContext().getAttribute("CONTEXT");
            errorCtrl = context.getBean(ErrorController.class);
            routes = (Map<String, Map<String, Handler>>) context.getBean("routes");
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnavailableException(e.getMessage());
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        Optional.ofNullable(routes.get(req.getServletPath()))
                .map(o -> o.getOrDefault(req.getMethod(), errorCtrl::methodNotAllowed))
                .orElse(errorCtrl::notFound)
                .handle(req, resp);
    }

}
