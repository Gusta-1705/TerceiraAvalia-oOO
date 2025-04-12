// Nome: Gustavo do Bem Ferreira, Matrícula: 202065036AC

package com.example.ListaTarefas.ListaDeTarefas.model;

import java.time.LocalDate;

// Classe abstrata
public abstract class EntidadeBase {
    // Atributos com encapsulamento adequados, protected porque qualquer classe no mesmo pacote tem acesso
    protected Long id;
    protected LocalDate dataCriacao;
    // Atributo estático para contar a quantidade de IDs
    // Atributos Estáticos
    private static long contadorIds = 0;

    // Construtor para inicializar os atributos
    public EntidadeBase() {
        this.dataCriacao = LocalDate.now();
        this.id = contadorIds++;
    }

    // Métodos getters e setters
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
