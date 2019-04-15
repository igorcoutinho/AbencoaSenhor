package com.checklist.api.repositories;

import java.util.List;

import java.util.Optional;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.checklist.api.entities.Tarefa;

@Transactional(readOnly = true)
public interface TarefaRepository extends JpaRepository<Tarefa, Long>{


	Tarefa findByNome(String nome);
	
	Optional<Tarefa> findById(Long id);
	
}
