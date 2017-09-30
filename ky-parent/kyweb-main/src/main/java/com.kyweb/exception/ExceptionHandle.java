package com.kyweb.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Caomr on 2017/9/12.
 */
@ControllerAdvice
@Slf4j
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    public String defaultErrorHandler(HttpServletRequest req, Exception e)
    {
        log.error("request for {} but failed by {}",req.getRequestURI(),e.getMessage(),e);
        return "redirect:/err?errMsg="+e.getMessage();
    }
}
