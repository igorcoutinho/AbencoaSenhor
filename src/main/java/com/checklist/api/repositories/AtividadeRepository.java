package com.checklist.api.repositories;


import org.springframework.data.jpa.repository.JpaRepository;


import com.checklist.api.entities.Atividade;


public interface AtividadeRepository extends JpaRepository<Atividade, Long>{

}