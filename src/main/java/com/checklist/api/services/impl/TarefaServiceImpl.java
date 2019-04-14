package com.checklist.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.checklist.api.entities.Tarefa;
import com.checklist.api.repositories.TarefaRepository;
import com.checklist.api.services.TarefaService;

@Service
public class TarefaServiceImpl implements TarefaService{
	private static final Logger log = LoggerFactory.getLogger(TarefaServiceImpl.class);
	
	@Autowired
	private TarefaRepository tarefaRepository;
	
	public Page<Tarefa> buscarPorUsuarioId(Long usuarioId, PageRequest pageRequest) {
		log.info("Buscando tareafas para o usuario ID {}", usuarioId);
		return this.tarefaRepository.findByUsuarioId(usuarioId, pageRequest);
	}
	
	public Tarefa persistir(Tarefa tarefa) {
		log.info("Persistindo tarefa: {}", tarefa);
		return this.tarefaRepository.save(tarefa);
	}
	
	public Optional<Tarefa> buscarPorId(Long id) {
		log.info("Buscando tarefa pelo IDl {}", id);
		return this.tarefaRepository.findById(id);
	}
	
	
}
