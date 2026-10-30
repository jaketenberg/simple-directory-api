package com.ping.simpledirectoryapi;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * Resolves the caller's client type from the X-Client-Type header before a
 * request reaches a controller, so every endpoint doesn't have to repeat it.
 * The value is passed through unvalidated; what client types exist and what
 * an unrecognized one means are decisions for the access-control policy.
 */
@Component
public class ClientTypeInterceptor implements HandlerInterceptor {

    public static final String CLIENT_TYPE_ATTRIBUTE = "clientType";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String clientType = request.getHeader("X-Client-Type");
        if (clientType == null || clientType.isBlank()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("unknown client type");
            return false;
        }
        request.setAttribute(CLIENT_TYPE_ATTRIBUTE, clientType);
        return true;
    }
}
