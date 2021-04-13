package tech.itpark.proggerhub.middleware;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import tech.itpark.servlet.Middleware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class Logger implements Middleware {
    protected final Log logger = LogFactory.getLog(getClass());

    // true - я обработал, дальше обрабатывать не надо
    @Override
    public boolean handle(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        logger.info(request.getServletPath());
        return false;
    }
}
