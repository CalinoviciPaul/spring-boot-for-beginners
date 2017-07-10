package com.in28minutes.springboot.error;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IrianLaptop on 7/10/2017.
 */

@ControllerAdvice
public class CustomErrorHandler {


    @ExceptionHandler(value = {RuntimeException.class})

    public void handleRuntimeExpression(){
        System.out.println("Runtime Exception");
    }

    @ExceptionHandler(value = {AuthorizationError.class})
    public void handleAuthorizationExpression(AuthorizationError ex, HttpServletResponse response) throws IOException {
        response.sendRedirect("/authorizationError");
        System.out.println("Authorization Exception");
    }

    @ExceptionHandler(value = {OtherError.class})
    public void handleOtherExpression(){
        System.out.println("Other Exception");
    }
}
