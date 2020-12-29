package br.com.ltst.springwebmvcbasicodigitalinnovationone.controller;

import br.com.ltst.springwebmvcbasicodigitalinnovationone.exception.JediNotFoundException;
import br.com.ltst.springwebmvcbasicodigitalinnovationone.exception.SoldadoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ResourceAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(JediNotFoundException.class)
    public void jediNotFound() {

    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(SoldadoNotFoundException.class)
    public void soldadoNotFound() {

    }
}
