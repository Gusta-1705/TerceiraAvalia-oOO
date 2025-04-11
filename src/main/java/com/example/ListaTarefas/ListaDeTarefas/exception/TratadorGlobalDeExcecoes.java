// Nome: Gustavo do Bem Ferreira, Matrícula: 202065036AC

package com.example.ListaTarefas.ListaDeTarefas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

//Tratamento de Exceções
// Classe global para tratamento de exceções
// Essa classe intercepta e lida com exceções para evitar retornos inesperados no sistema.
@ControllerAdvice
public class TratadorGlobalDeExcecoes {

    // Trata exceções de recurso não encontrado
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<String> RecursoNaoEncontradoException(RecursoNaoEncontradoException ex) {
        // Retorna uma mensagem personalizada junto ao código HTTP 404 (Not Found)
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Trata exceções gerais e inesperadas
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> GeralException(Exception ex) {
        // Retorna um erro genérico 500 (Internal Server Error) com a mensagem do erro ocorrido
        return new ResponseEntity<>("Erro interno no servidor: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}