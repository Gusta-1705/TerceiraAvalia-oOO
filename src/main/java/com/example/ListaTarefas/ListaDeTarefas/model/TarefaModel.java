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

    // Método sobrescrito da interface TarefaServico
    // Sobrescrita
    @Override
    public void concluirTarefa() {
        this.concluida = true;
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

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
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

    public void setPrioridade(int prioridade) {
        if (prioridade < 1 || prioridade > 5) {
            throw new ParametroInvalidoException("A prioridade deve estar entre 1 e 5.");
        }
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
