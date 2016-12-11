package ru.restaurant.util.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import ru.restaurant.web.ExceptionInfoHandler;
import ru.restaurant.web.json.JacksonObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ExceptionUtil.handle(request, response, accessDeniedException, HttpServletResponse.SC_FORBIDDEN);
/*        ObjectMapper objectMapper = JacksonObjectMapper.getMapper();
//        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(new ErrorInfo(request.getRequestURL(), accessDeniedException));
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().write(s);
//        response.getWriter().flush();
        response.getWriter().close();*/

    }

}
