package tech.itpark.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Middleware {
    // true - я обработал, дальше обрабатывать не надо
    boolean handle(
            HttpServletRequest request,
            HttpServletResponse response
    );
}
