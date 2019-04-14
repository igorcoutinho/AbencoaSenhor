package com.checklist.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.checklist.api.entities.Usuario;
import com.checklist.api.repositories.UsuarioRepository;
import com.checklist.api.services.UsuarioService;



@Service
public class UsuarioServiceImpl  implements UsuarioService {
	
	private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public Optional<Usuario> buscarPorCpf(String cpf){
		log.info("Buscando um usuario para o CPF {}", cpf);
		return Optional.ofNullable(usuarioRepository.findByCpf(cpf));
	}
	
	@Override
	public Optional<Usuario> buscarPorEmail(String email){
		log.info("Buscando um usuario para o email {}", email);
		return Optional.ofNullable(usuarioRepository.findByCpf(email));
	}
	
	@Override
	public Usuario persistir(Usuario usuario) {
		log.info("Persistindo usuario: {}", usuario);
		return this.usuarioRepository.save(usuario);
	}

	
}
