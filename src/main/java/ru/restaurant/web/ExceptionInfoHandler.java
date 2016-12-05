package ru.restaurant.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import ru.restaurant.util.exception.ErrorInfo;
import ru.restaurant.util.exception.NotFoundException;
import ru.restaurant.util.exception.UserExistsException;
import ru.restaurant.util.exception.WrongTimeException;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice(annotations = RestController.class)
@ControllerAdvice
public class ExceptionInfoHandler {
    Logger LOG = LoggerFactory.getLogger(ExceptionInfoHandler.class);

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ErrorInfo notFound(HttpServletRequest req, NotFoundException e) {
        return logAndGetErrorInfo(req, e, true);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseBody
    @Order(Ordered.HIGHEST_PRECEDENCE + 1)
    public ErrorInfo handleError(HttpServletRequest req, UsernameNotFoundException e) {
        return logAndGetErrorInfo(req, e, true);
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)  // 409
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    @Order(Ordered.HIGHEST_PRECEDENCE + 2)
    public ErrorInfo conflict(HttpServletRequest req, DataIntegrityViolationException e) {
        return logAndGetErrorInfo(req, e, true);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WrongTimeException.class)
    @ResponseBody
    @Order(Ordered.HIGHEST_PRECEDENCE + 3)
    public ErrorInfo wrongTime(HttpServletRequest req, WrongTimeException e) {
        return logAndGetErrorInfo(req, e, true);
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(UserExistsException.class)
    @ResponseBody
    @Order(Ordered.HIGHEST_PRECEDENCE + 4)
    public ErrorInfo runTime(HttpServletRequest req, UserExistsException e) {
        return logAndGetErrorInfo(req, e, true);
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    @Order(Ordered.HIGHEST_PRECEDENCE + 5)
    public ErrorInfo handleError405(HttpServletRequest req, Exception e) {
        return logAndGetErrorInfo(req, e, true);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    @Order(Ordered.HIGHEST_PRECEDENCE + 6)
    public ErrorInfo handleAccessDenied(HttpServletRequest req, Exception e) {
        return logAndGetErrorInfo(req, e, true);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @Order(Ordered.LOWEST_PRECEDENCE)
    public ErrorInfo handleError(HttpServletRequest req, Exception e) {
        return logAndGetErrorInfo(req, e, true);
    }


    public ErrorInfo logAndGetErrorInfo(HttpServletRequest req, Exception e, boolean logException) {
        if (logException) {
            LOG.error("Exception at request " + req.getRequestURL(), e);
        } else {
            LOG.warn("Exception at request " + req.getRequestURL() + ": " + e.toString());
        }
        return new ErrorInfo(req.getRequestURL(), e);
    }
}
