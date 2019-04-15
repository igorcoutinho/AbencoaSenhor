package com.checklist.api.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.checklist.api.entities.Tarefa;



public interface TarefaService {

	/**
	 * Persiste uma tarefa na base de dados.
	 * 
	 * @param tarefa
	 * @return Tarefa
	 */
	Tarefa persistir(Tarefa tarefa);
	
	/**
	 * Busca e retorna uma tarefa por ID.
	 * 
	 * @param id
	 * @return Optional<Tarefa>
	 */
	Optional<Tarefa> buscarPorId(Long id);
	
	/**
	 * Busca e retorma uma atividade por um nome.
	 * 
	 * @param nome
	 * @return Optional<Tarefa>
	 */
	Optional<Tarefa> buscarPorNome(String nome);
}
