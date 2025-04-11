// Nome: Gustavo do Bem Ferreira, Matrícula: 202065036AC

package com.example.ListaTarefas.ListaDeTarefas.model;

import java.time.LocalDate;

// Classe abstrata
public abstract class EntidadeBase {
    // Atributos com encapsulamento adequados, protected porque qualquer classe no mesmo pacote tem acesso
    protected Long id;
    protected LocalDate dataCriacao;

    // Métodos GET e SET
    public EntidadeBase() {
        this.dataCriacao = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }
}