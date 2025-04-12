// Nome: Gustavo do Bem Ferreira, Matrícula: 202065036AC

package com.example.ListaTarefas.ListaDeTarefas.exception;

// Exceção personalizada para argumentos inválidos
public class ParametroInvalidoException extends RuntimeException {
    // Construtor que recebe uma mensagem personalizada sobre o erro ocorrido
    public ParametroInvalidoException(String mensagem) {
        // Chama o construtor da superclasse RuntimeException para definir a mensagem do erro
        super(mensagem);
    }
}

