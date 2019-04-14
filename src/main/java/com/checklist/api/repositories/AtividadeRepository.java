package com.checklist.api.repositories;

import java.util.List;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.checklist.api.entities.Atividade;

@Transactional(readOnly = true)
@NamedQueries({
		@NamedQuery(name = "AtividadeRepository.findByTarefaId", 
				query = "SELECT atv FROM Atividade atv WHERE atv.tarefa.id = :tarefaId") })
public interface AtividadeRepository extends JpaRepository<Atividade, Long>{

	List<Atividade> findByTarefaId(@Param("tarefaId") Long tarefaId);
	
	Page<Atividade> findByTarefaId(@Param("tarefaId") Long tarefaId, Pageable pageable);
}