package ru.restaurant.util.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import ru.restaurant.web.json.JacksonObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExceptionUtil {

    private ExceptionUtil() {
    }

    public static void handle(HttpServletRequest request, HttpServletResponse response,
                       Exception e, int statusResponse) throws IOException, ServletException {
        ObjectMapper objectMapper = JacksonObjectMapper.getMapper();
        String s = objectMapper.writeValueAsString(new ErrorInfo(request.getRequestURL(), e));
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(statusResponse);
        response.getWriter().write(s);
        response.getWriter().close();
    }
}
