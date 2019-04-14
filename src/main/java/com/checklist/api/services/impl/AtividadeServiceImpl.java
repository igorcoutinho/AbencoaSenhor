package com.checklist.api.services.impl;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.checklist.api.entities.Atividade;
import com.checklist.api.repositories.AtividadeRepository;
import com.checklist.api.services.AtividadeService;



@Service
public class AtividadeServiceImpl implements AtividadeService{

	private static final Logger log = LoggerFactory.getLogger(AtividadeServiceImpl.class);
	
	@Autowired
	private AtividadeRepository atividadeRepository;
	
	public Page<Atividade> buscarPorTarefaId(Long tarefaId, PageRequest pageRequest){
		log.info("Buscando atividades para a tarefa ID{}", tarefaId);
		return this.atividadeRepository.findByTarefaId(tarefaId, pageRequest);
		
	}
	
	@Cacheable("atividadePorId")
	public Optional<Atividade> buscarPorId(Long id) {
		log.info("Buscando uma atividade pelo ID {}", id);
		return this.atividadeRepository.findById(id);
	}
	
	@CachePut("atividadePorId")
	public Atividade persistir(Atividade atividade) {
		log.info("Persistindo a atividade: {}", atividade);
		return this.atividadeRepository.save(atividade);
	}
	
	public void remover(Long id) {
		log.info("Removendo a atividade po ID {}", id);
		this.atividadeRepository.deleteById(id);
	}
	
}
