// Nome: Gustavo do Bem Ferreira, Matrícula: 202065036AC

package com.example.ListaTarefas.ListaDeTarefas.model;

import java.time.LocalDate;

import com.example.ListaTarefas.ListaDeTarefas.exception.ParametroInvalidoException;

// Classe que representa uma tarefa, herdando de EntidadeBase e implementando TarefaServico
public class TarefaModel extends EntidadeBase implements TarefaServico{
    // Atributos privados para garantir encapsulamento
    // Encapsulamento
    private String titulo;
    private String descricao;
    private boolean concluida;
    private LocalDate dataConclusao;
    private LocalDate dataUltimaAtualizacao;
    private int prioridade;

    // Construtor que recebe parâmetros para inicializar uma tarefa
    public TarefaModel(Long id, String titulo, String descricao, LocalDate dataConclusao, int prioridade) {
        super(id);
        this.titulo = titulo;
        this.descricao = descricao;
        this.concluida = false;
        this.dataUltimaAtualizacao = LocalDate.now();
        this.dataConclusao = dataConclusao;
        this.prioridade = prioridade;
    }

    // Método para atualizar a data de última modificação
    // Polimorfismo
    public void atualizarData() {
        this.dataUltimaAtualizacao = LocalDate.now();
    }

    // Sobrecarga do método atualizarData, permitindo definir uma data específica
    // Sobrecarga
    public void atualizarData(LocalDate novaData) {
        this.dataUltimaAtualizacao = novaData;
    }

    // Método sobrescrito da interface TarefaServico para concluir a tarefa
    // Sobrescrita
    @Override
    public void concluirTarefa() {
        // Define o status da tarefa como concluída
        this.concluida = true;
        // Atualiza a data da última modificação, garantindo que a alteração seja registrada
        this.atualizarData();
    }

    // Métodos Getters e Setters para acesso seguro aos atributos encapsulados
    // Encapsulamento
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean getConcluida() {
        return concluida;
    }

    // Método para definir se a tarefa foi concluída
    public void setConcluida(boolean concluida) {
        // Atualiza o status de conclusão da tarefa
        this.concluida = concluida;
        // Chama o método para atualizar a data da última modificação,
        // garantindo que qualquer alteração seja registrada corretamente
        atualizarData();
    }

    public LocalDate getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(LocalDate dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public int getPrioridade() {
        return prioridade;
    }

    // Método para alterar e definir a prioridade da tarefa
    public void setPrioridade(int prioridade) {
        // Verifica se a prioridade está dentro do intervalo permitido (1 a 5)
        if (prioridade < 1 || prioridade > 5) {
            // Caso contrário, lança uma exceção personalizada informando o erro
            throw new ParametroInvalidoException("A prioridade deve estar entre 1 e 5.");
        }
        // Se a prioridade for válida, atribui o valor ao atributo da classe
        this.prioridade = prioridade;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getDataUltimaAtualizacao() {
        return dataUltimaAtualizacao;
    }

    public void setDataUltimaAtualizacao(LocalDate dataUltimaAtualizacao) {
        this.dataUltimaAtualizacao = dataUltimaAtualizacao;
    }
}

