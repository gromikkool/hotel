package com.senlainc.gitcourses.kashko.raman.controller.exception;

import com.senlainc.gitcourses.kashko.raman.exceptions.ObjectAlreadyExistsException;
import com.senlainc.gitcourses.kashko.raman.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class ExceptionHandlerWeb {

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Error catcherOfAnyException() {
        final Error serverError = new Error();
        serverError.setCode(1);
        serverError.setMessage("Sorry. Something went wrong, try a bit later, please.");
        return serverError;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ObjectNotFoundException.class)
    public Error ObjectNotFoundCatcher() {
        final Error serverError = new Error();
        serverError.setCode(2);
        serverError.setMessage("Sorry. You are trying to get non existing resource.");
        return serverError;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ObjectAlreadyExistsException.class)
    public Error ObjectAlreadyExistsException() {
        final Error serverError = new Error();
        serverError.setCode(3);
        serverError.setMessage("Sorry, such object already exists.");
        return serverError;
    }


}
