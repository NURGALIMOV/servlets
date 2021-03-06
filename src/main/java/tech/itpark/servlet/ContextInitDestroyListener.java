package tech.itpark.servlet;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextInitDestroyListener implements ServletContextListener {

    private ConfigurableApplicationContext context;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        context = new AnnotationConfigApplicationContext("tech.itpark.proggerhub");
        sce.getServletContext().setAttribute("CONTEXT", context);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        context.close();
    }

}
