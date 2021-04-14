package tech.itpark.proggerhub.middleware;

import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import tech.itpark.proggerhub.security.AuthProvider;
import tech.itpark.servlet.Middleware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class Authenticator implements Middleware {

    protected final Log logger = LogFactory.getLog(getClass());
    private final AuthProvider provider;

    @Override
    public boolean handle(HttpServletRequest request, HttpServletResponse response) {
        final var token = request.getHeader("Authorization");
        final var auth = provider.authenticate(token);
        request.setAttribute("AUTH", auth);
        return false;
    }

}
