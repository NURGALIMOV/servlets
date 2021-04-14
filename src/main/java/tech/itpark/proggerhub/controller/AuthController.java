package tech.itpark.proggerhub.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import tech.itpark.proggerhub.converter.BodyConverter;
import tech.itpark.proggerhub.dto.UserDto;
import tech.itpark.proggerhub.dto.UserIdDto;
import tech.itpark.proggerhub.dto.UserTokenDto;
import tech.itpark.proggerhub.exception.AuthException;
import tech.itpark.proggerhub.security.Authentication;
import tech.itpark.proggerhub.service.AuthService;
import tech.itpark.proggerhub.service.model.UserModel;
import tech.itpark.servlet.ContentTypes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;
    private final BodyConverter converter;

    public void register(HttpServletRequest request, HttpServletResponse response) {
        try {
            if (!converter.canRead(request.getHeader("Content-Type"), UserDto.class)) {
                response.sendError(415, "media type not supported");
                return;
            }

            final var dto = converter.read(request.getReader(), UserDto.class);
            final var id = service.register(new UserModel(dto.getLogin(), dto.getPassword()));
            response.addHeader("Content-Type", ContentTypes.APPLICATION_JSON);
            converter.write(response.getWriter(), new UserIdDto(id));
        } catch (IOException e) {
            throw new AuthException(e);
        }
    }

    public void login(HttpServletRequest request, HttpServletResponse response) {
        try {
            if (!converter.canRead(request.getHeader("Content-Type"), UserDto.class)) {
                response.sendError(415, "media type not supported");
                return;
            }

            final var dto = converter.read(request.getReader(), UserDto.class);
            final var token = service.login(new UserModel(dto.getLogin(), dto.getPassword()));

            response.addHeader("Content-Type", ContentTypes.APPLICATION_JSON);
            converter.write(response.getWriter(), new UserTokenDto(token));
        } catch (IOException e) {
            throw new AuthException(e);
        }
    }

    public void removeById(HttpServletRequest request, HttpServletResponse response) {
        final var auth = (Authentication) request.getAttribute("AUTH");
        service.removeById(auth);
    }

    public void restore(HttpServletRequest request, HttpServletResponse response) {
        // TODO:
    }

}
