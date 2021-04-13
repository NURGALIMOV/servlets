package tech.itpark.servlet;

import org.springframework.context.ApplicationContext;
import tech.itpark.proggerhub.security.AuthProvider;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter extends HttpFilter {
    private AuthProvider provider;

    @Override
    public void init(FilterConfig config) throws ServletException {
        super.init(config);
        provider = ((ApplicationContext) getServletContext().getAttribute("CONTEXT"))
                .getBean(AuthProvider.class);
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res,
                            FilterChain chain) throws IOException, ServletException {
        final var token = req.getHeader("Authorization");
        final var auth = provider.authenticate(token);
        req.setAttribute("AUTH", auth);

        super.doFilter(req, res, chain);
    }
}
