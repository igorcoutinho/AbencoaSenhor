package com.checklist.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.checklist.api.entities.Tarefa;
import com.checklist.api.repositories.TarefaRepository;
import com.checklist.api.services.TarefaService;

@Service
public class TarefaServiceImpl implements TarefaService{
	private static final Logger log = LoggerFactory.getLogger(TarefaServiceImpl.class);
	
	@Autowired
	private TarefaRepository tarefaRepository;
	
	
	@Override
	public Tarefa persistir(Tarefa tarefa) {
		log.info("Persistindo tarefa: {}", tarefa);
		return this.tarefaRepository.save(tarefa);
	}
	
	@Override
	public Optional<Tarefa> buscarPorId(Long id) {
		log.info("Buscando tarefa pelo ID {}", id);
		return this.tarefaRepository.findById(id);
	}
	
	@Override
	public Optional<Tarefa> buscarPorNome(String nome) {
		log.info("Buscando funcionário pelo email {}", nome);
		return Optional.ofNullable(this.tarefaRepository.findByNome(nome));
	}
	
	
}
