package tech.itpark.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Middleware {

    boolean handle(HttpServletRequest request, HttpServletResponse response);

}
