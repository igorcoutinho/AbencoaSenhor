package com.checklist.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.checklist.api.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Usuario findByCpf(String cpf);
	
	Usuario findByEmail(String email);
	
	Usuario findByCpfOrEmail(String cpf, String email);
}
