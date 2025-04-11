// Nome: Gustavo do Bem Ferreira, Matrícula: 202065036AC

package com.example.ListaTarefas.ListaDeTarefas.repository;

import org.springframework.stereotype.Repository;

import com.example.ListaTarefas.ListaDeTarefas.model.TarefaModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

// Indica que essa classe é um repositório e pode ser gerenciada pelo Spring
@Repository
public class TarefaRepository {
    // Armazena as tarefas em um mapa, associando IDs a objetos Tarefa
    private final Map<Long, TarefaModel> tarefas = new HashMap<>(); // Encapsulamento
    // Gerador automático de IDs para tarefas
    private final AtomicLong nextId = new AtomicLong(1);

    // Retorna todas as tarefas cadastradas
    // Coleções
    public List<TarefaModel> findAll() {
        return new ArrayList<>(tarefas.values());
    }

    // Busca uma tarefa pelo ID
    public TarefaModel findById(Long id) {
        return tarefas.get(id);
    }

    // Salva uma nova tarefa ou atualiza uma existente
    public TarefaModel save(TarefaModel tarefa) {
        // Associação
        if (tarefa.getId() == null) {
            tarefa.setId(nextId.getAndIncrement());
        }
        tarefas.put(tarefa.getId(), tarefa);
        return tarefa;
    }

    // Remove uma tarefa pelo ID
    public void deleteById(Long id) {
        tarefas.remove(id);
    }
}