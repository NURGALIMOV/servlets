package tech.itpark.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@FunctionalInterface
public interface Handler {
    void handle(HttpServletRequest request, HttpServletResponse response);
}
