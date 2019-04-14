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
@NamedQueries({
		@NamedQuery(name = "TarefaRepository.findByUsuarioId", 
				query = "SELECT tar FROM Tarefa tar WHERE tar.usuario.id = :usuarioId") })
public interface TarefaRepository extends JpaRepository<Tarefa, Long>{

	List<Tarefa> findByUsuarioId(@Param("usuarioId") Long usuarioId);
	
	Page<Tarefa> findByUsuarioId(@Param("usuarioId") Long usuarioId, Pageable pageable);
	
	Optional<Tarefa> findById(Long id);
}
