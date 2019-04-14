package com.checklist.api.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.checklist.api.entities.Usuario;

@Service
public interface UsuarioService {

	/**
	 * Retorna um usuario dado um CPF.
	 * 
	 * @param cpf
	 * @return Optional<Usuario>
	 */
	Optional<Usuario> buscarPorCpf(String cpf);
	
	/**
	 * Retorna um usuario dado um email.
	 * 
	 * @param cpf
	 * @return Optional<Usuario>
	 */
	Optional<Usuario> buscarPorEmail(String email);
	
	/**
	 * Cadastra um usuario na base de dados.
	 * 
	 * @param usuario
	 * @return Usuario
	 */
	Usuario persistir(Usuario usuario);
	
}
