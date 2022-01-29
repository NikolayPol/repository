package com.example.repo.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

/**
 * Класс GlobalExceptionHandler - класс для обработки исключений во всех контроллерах.
 *
 * Метод parseException обрабатывает исключение, возникающее при неправильном
 * вводе формата даты. Сообщение об исключении отправляется клиенту в формате json.
 *
 * @author Nikolay Polegaev
 * @version 1.0 29.01.2022
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(GlobalExceptionHandler.class.getSimpleName());

    private final ObjectMapper objectMapper;

    public GlobalExceptionHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @ExceptionHandler(value = {ParseException.class})
    public void parseException(Exception e, HttpServletRequest request,
                                HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() { {
            put("message", "Invalid date format entered. "
                    + "Enter date into the format yyyy-MM-dd HH:mm");
            put("details", e.getMessage());
        }}));
        LOGGER.error(e.getMessage());
    }
}

