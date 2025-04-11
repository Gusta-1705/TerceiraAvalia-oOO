// Nome: Gustavo do Bem Ferreira, Matrícula: 202065036AC

package com.example.ListaTarefas.ListaDeTarefas.exception;

//Tratamento de Exceção
// Exceção personalizada para recursos não encontrados
public class RecursoNaoEncontradoException extends RuntimeException {
    // Construtor que recebe uma mensagem personalizada sobre o erro ocorrido
    public RecursoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
