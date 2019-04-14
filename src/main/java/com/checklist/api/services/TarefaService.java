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
	 * Retorna uma lista paginada de tarefas de um determinado usuario.
	 * 
	 * @param usuarioId
	 * @param pageRequest
	 * @return Page<Lancamento>
	 */
	Page<Tarefa> buscarPorUsuarioId(Long usuarioId, PageRequest pageRequest);
}
