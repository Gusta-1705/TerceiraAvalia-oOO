// Nome: Gustavo do Bem Ferreira, Matrícula: 202065036AC

package com.example.ListaTarefas.ListaDeTarefas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ListaTarefas.ListaDeTarefas.exception.RecursoNaoEncontradoException;
import com.example.ListaTarefas.ListaDeTarefas.model.TarefaModel;
import com.example.ListaTarefas.ListaDeTarefas.repository.TarefaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

// Indica que esta classe é um controlador REST, permitindo manipulação de requisições HTTP
@RestController
@RequestMapping("/tarefas") // Define o caminho base para as requisições relacionadas a tarefas
public class TarefaController {
    // Injeta a dependência do repositório de tarefas automaticamente pelo Spring
    @Autowired
    private TarefaRepository tarefaRepository; // Agregação

    // Método para listar todas as tarefas cadastradas
    @GetMapping
    public ResponseEntity<List<TarefaModel>> listarTarefas() {
        List<TarefaModel> tarefas = tarefaRepository.findAll();
        return new ResponseEntity<>(tarefas, HttpStatus.OK); // Retorna as tarefas com código de status HTTP 200 (OK)
    }

    // Método para buscar uma tarefa específica pelo ID
    // Tratamento de Exceção
    @GetMapping("/{id}")
    public ResponseEntity<TarefaModel> buscarTarefa(@PathVariable Long id) {
        // Busca a tarefa no repositório pelo ID fornecido
        TarefaModel tarefa = tarefaRepository.findById(id);
        // Se a tarefa não for encontrada, lança uma exceção personalizada
        if (tarefa == null) {
            throw new RecursoNaoEncontradoException("Tarefa com ID " + id + " não encontrada.");
        }
        // Retorna a tarefa encontrada com código HTTP 200 (OK)
        return new ResponseEntity<>(tarefa, HttpStatus.OK);
    }

    // Método para adicionar uma nova tarefa
    @PostMapping
    public ResponseEntity<TarefaModel> adicionarTarefa(@RequestBody TarefaModel novaTarefa) {
        // Valida se o título está preenchido antes de cadastrar
        if (novaTarefa.getTitulo() == null || novaTarefa.getTitulo().isBlank()) {
            return ResponseEntity.badRequest().body(null); // Retorna erro 400 caso o título esteja inválido
        }
        novaTarefa.setDataUltimaAtualizacao(LocalDate.now()); // Define a data da última atualização
        TarefaModel tarefaSalva = tarefaRepository.save(novaTarefa); // Salva a tarefa no repositório
        return new ResponseEntity<>(tarefaSalva, HttpStatus.CREATED); // Retorna a tarefa criada com código 201 (Created)
    }

    // Método para atualizar o status de uma tarefa existente
    @PutMapping("/atualizarStatus/{id}")
    public ResponseEntity<TarefaModel> atualizarTarefa(@PathVariable Long id) {
        TarefaModel tarefaExistente = tarefaRepository.findById(id);
        if (tarefaExistente != null) {
            tarefaExistente.setConcluida(true); // Marca a tarefa como concluída
            TarefaModel tarefaSalva = tarefaRepository.save(tarefaExistente); // Salva a alteração no repositório
            return new ResponseEntity<>(tarefaSalva, HttpStatus.OK); // Retorna código HTTP 200
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna erro caso o ID não exista
        }
    }

    // Método para atualizar a descrição de uma tarefa
    @PutMapping("/atualizarDescricao/{id}")
    public ResponseEntity<TarefaModel> atualizarDescricao(@PathVariable Long id, @RequestBody Map<String, String> body) {
        TarefaModel recuperaTarefa = tarefaRepository.findById(id);
        if (recuperaTarefa != null) {
            recuperaTarefa.setDescricao(body.get("descricao")); // Atualiza a descrição com o novo valor
            TarefaModel tarefaSalva = tarefaRepository.save(recuperaTarefa); // Salva as alterações
            return new ResponseEntity<>(tarefaSalva, HttpStatus.OK); // Retorna a tarefa atualizada
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna erro caso a tarefa não exista
        }
    }

    // Método para excluir uma tarefa pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirTarefa(@PathVariable Long id) {
        if (tarefaRepository.findById(id) != null) {
            tarefaRepository.deleteById(id); // Remove a tarefa do repositório
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Retorna código 204 (No Content)
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna erro caso a tarefa não exista
        }
    }

    // Método para ordenar as tarefas com base na data de criação e prioridade
    @GetMapping("/ordenadas")
    public ResponseEntity<List<TarefaModel>> ordenarTarefas() {
        List<TarefaModel> tarefas = tarefaRepository.findAll().stream()
            .sorted((t1, t2) -> {
                // Ordena primeiro pela data de criação e, se forem iguais, pela prioridade
                int comparacaoData = t1.getDataCriacao().compareTo(t2.getDataCriacao());
                return comparacaoData != 0 ? comparacaoData :       Integer.compare(t1.getPrioridade(), t2.getPrioridade());
            })
            .toList();
        return new ResponseEntity<>(tarefas, HttpStatus.OK); // Retorna a lista ordenada
    }

    // Método para concluir uma tarefa e definir a data de conclusão
    @PutMapping("/concluir/{id}")
    public ResponseEntity<TarefaModel> concluirTarefa(@PathVariable Long id) {
        TarefaModel tarefaExistente = tarefaRepository.findById(id);
        if (tarefaExistente != null) {
            tarefaExistente.setConcluida(true); // Marca a tarefa como concluída
            tarefaExistente.setDataConclusao(LocalDate.now()); // Define a data de conclusão como a data atual
            tarefaExistente.atualizarData(); // Atualiza a última modificação da tarefa
            TarefaModel tarefaSalva = tarefaRepository.save(tarefaExistente); // Salva a alteração no repositório
            return new ResponseEntity<>(tarefaSalva, HttpStatus.OK); // Retorna código 200 (OK)
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna erro caso o ID não seja encontrado
        }
    }
}
