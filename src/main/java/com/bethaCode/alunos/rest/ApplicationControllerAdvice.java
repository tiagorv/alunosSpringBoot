package com.bethaCode.alunos.rest;

import com.bethaCode.alunos.rest.exception.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors tratarRetornoEntidade(MethodArgumentNotValidException validacao){
        BindingResult retornoServico = validacao.getBindingResult();

        List<String> mensagensDeErro = retornoServico
                                            .getAllErrors()
                                            .stream()
                                            .map(objetoComErro -> objetoComErro.getDefaultMessage())
                                            .collect(Collectors.toList());
        return new ApiErrors(mensagensDeErro);
    }

    @ExceptionHandler(ResponseStatusException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors tratarRetornoNotFound(ResponseStatusException validacao){
        return new ApiErrors(validacao.getMessage());
    }

}
