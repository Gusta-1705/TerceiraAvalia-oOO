// Nome: Gustavo do Bem Ferreira, Matrícula: 202065036AC

// O código cobre todo o conteúdo.

/* Lista todas as tarefas, adiciona uma tarefa, obtém uma tarefa já existente, exclui uma tarefa da lista, 
obtém uma tarefa específica, orderna tarefas por data de criação e prioridade, 
marca a tarefa como concluída quando finalizada informando a data de conclusão, 
obtém a última atualização da tarefa. */

package com.example.ListaTarefas.ListaDeTarefas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Classe principal da aplicação Spring Bootd
@SpringBootApplication
public class ListaDeTarefasApplication {

	// Método principal que inicializa a aplicação
	public static void main(String[] args) {
		// Classificação / Instanciação
		SpringApplication.run(ListaDeTarefasApplication.class, args);
	}
}
