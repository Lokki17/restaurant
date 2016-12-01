package ru.restaurant.util.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFailedEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(new ErrorInfo(httpServletRequest.getRequestURL(), e));
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
        httpServletResponse.getWriter().write(s);
        httpServletResponse.getWriter().flush();
        httpServletResponse.getWriter().close();
    }
}