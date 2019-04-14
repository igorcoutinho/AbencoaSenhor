package com.checklist.api.controller;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.checklist.api.dtos.CadastroUsuarioDto;
import com.checklist.api.entities.Usuario;
import com.checklist.api.responses.Response;
import com.checklist.api.services.UsuarioService;


@RestController
@RequestMapping("/api/cadastrar-usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {
	
	
	private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);
	@Autowired
	private UsuarioService usuarioService;
	
	public UsuarioController(){
		
	}
	
	/**
	 * Cadastra um usuario no sistema.
	 * 
	 * @param cadastroUsuarioDto
	 * @param result
	 * @return ResponseEntity<Response<CadastroUsuarioDto>>
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<CadastroUsuarioDto>> cadastrar(@Valid @RequestBody CadastroUsuarioDto cadastroUsuarioDto, BindingResult result) throws NoSuchAlgorithmException {
		
		log.info("Cadastrando usuario: {}", cadastroUsuarioDto.toString());
		Response<CadastroUsuarioDto> response = new Response<CadastroUsuarioDto>();
		
		validarDadosExistentes(cadastroUsuarioDto, result);
		Usuario usuario = this.converterDtoParaUsuario(cadastroUsuarioDto, result);
		
		if(result.hasErrors()) {
			log.error("Erro validando dados de cadastro de usuario: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		//Optional<Usuario> usuarioNovo = this.usuarioService.buscarPorCpf(cadastroUsuarioDto.getCpf());
		
		this.usuarioService.persistir(usuario);

		response.setData(this.converterCadastroUsuarioDto(usuario));
		return ResponseEntity.ok(response);
		
	}
	
	/**
	 * Verifica se o usuario já está cadastrado na base de dados.
	 * 
	 * @param cadastroUsuarioDto
	 * @param result
	 */
	private void validarDadosExistentes(CadastroUsuarioDto cadastroUsuarioDto, BindingResult result) {
		Optional<Usuario> usuario = this.usuarioService.buscarPorCpf(cadastroUsuarioDto.getCpf());
//		if (!usuario.isPresent()) {
//			result.addError(new ObjectError("usuario", "Usuario não cadastrado."));
//		}
		
		this.usuarioService.buscarPorCpf(cadastroUsuarioDto.getCpf())
			.ifPresent(func -> result.addError(new ObjectError("usuario", "CPF já existente.")));

		this.usuarioService.buscarPorEmail(cadastroUsuarioDto.getEmail())
			.ifPresent(func -> result.addError(new ObjectError("usuario", "Email já existente.")));
	}
	
	private Usuario converterDtoParaUsuario(CadastroUsuarioDto cadastroUsuarioDto, BindingResult result)
			throws NoSuchAlgorithmException {
		Usuario usuario = new Usuario();
		usuario.setNome(cadastroUsuarioDto.getNome());
		usuario.setEmail(cadastroUsuarioDto.getEmail());
		usuario.setCpf(cadastroUsuarioDto.getCpf());
		usuario.setSenha(cadastroUsuarioDto.getSenha());
		
		return usuario;
	}
	
	/**
	 * Popula o DTO de cadastro com os dados do usuario.
	 * 
	 * @param usuario
	 * @return CadastroUsuarioDto
	 */
	private CadastroUsuarioDto converterCadastroUsuarioDto(Usuario usuario) {
		CadastroUsuarioDto cadastroUsuarioDto = new CadastroUsuarioDto();
		cadastroUsuarioDto.setId(usuario.getId());
		cadastroUsuarioDto.setNome(usuario.getNome());
		cadastroUsuarioDto.setEmail(usuario.getEmail());
		cadastroUsuarioDto.setCpf(usuario.getCpf());
		cadastroUsuarioDto.setSenha(usuario.getSenha());
		
		return cadastroUsuarioDto;
	}
}
