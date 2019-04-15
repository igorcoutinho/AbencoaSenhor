package com.checklist.api.controller;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
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

import com.checklist.api.dtos.CadastroTarefaDto;
import com.checklist.api.dtos.CadastroUsuarioDto;
import com.checklist.api.entities.Tarefa;
import com.checklist.api.entities.Usuario;
import com.checklist.api.responses.Response;
import com.checklist.api.services.TarefaService;

@RestController
@RequestMapping("/api/cadastrar-tarefa")
@CrossOrigin(origins = "*")
public class TarefaController {

	private static final Logger log = LoggerFactory.getLogger(TarefaController.class);
	
	@Autowired
	private TarefaService tarefaService;
	
	public TarefaController() {
		
	}
	
	/**
	 * Cadastra uma tarefa no sistema.
	 * 
	 * @param cadastroTarefaDto
	 * @param result
	 * @return ResponseEntity<Response<CadastroTarefaDto>>
	 * @throws NoSuchAlgorithmException
	 */
	
	@PostMapping
	public ResponseEntity<Response<CadastroTarefaDto>> cadastrar(@Valid @RequestBody CadastroTarefaDto cadastroTarefaDto, BindingResult result) throws NoSuchAlgorithmException {
		
		log.info("Cadastrando tarefa: {}", cadastroTarefaDto.toString());
		Response<CadastroTarefaDto> response = new Response<CadastroTarefaDto>();
		
		validarDadosExistentes(cadastroTarefaDto, result);
		Tarefa tarefa = this.converterDtoParaTarefa(cadastroTarefaDto, result);
		
		if(result.hasErrors()) {
			log.error("Erro validando dados de cadastro de tarefa: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		//Optional<Usuario> usuarioNovo = this.usuarioService.buscarPorCpf(cadastroUsuarioDto.getCpf());
		
		this.tarefaService.persistir(tarefa);

		response.setData(this.converterCadastroTarefaDto(tarefa));
		return ResponseEntity.ok(response);
		
	}
	
	/**
	 * Verifica se o usuario já está cadastrado na base de dados.
	 * 
	 * @param cadastroUsuarioDto
	 * @param result
	 */
	private void validarDadosExistentes(CadastroTarefaDto cadastroTarefaDto, BindingResult result) {
		
		this.tarefaService.buscarPorNome(cadastroTarefaDto.getNome())
			.ifPresent(func -> result.addError(new ObjectError("tarefa", "Já existe uma tarefa com esse nome cadastrada.")));
		
	}
	
	private Tarefa converterDtoParaTarefa(CadastroTarefaDto cadastroTarefaDto, BindingResult result)
			throws NoSuchAlgorithmException {
		Tarefa tarefa = new Tarefa();
		tarefa.setNome(cadastroTarefaDto.getNome());
		tarefa.setFinalizada(false);
//		if(cadastroTarefaDto.getFinalizada() == true) {
//			Date data = new Date(System.currentTimeMillis());
//			tarefa.setDataRealizacao(data);
//			tarefa.setDataRealizacao(data);
//		}
		tarefa.setTipo(cadastroTarefaDto.getTipo().toString());
		
		return tarefa;
	}
	
	/**
	 * Popula o DTO de cadastro com os dados do usuario.
	 * 
	 * @param usuario
	 * @return CadastroUsuarioDto
	 */
	/**
	 * Popula o DTO de cadastro com os dados do usuario.
	 * 
	 * @param usuario
	 * @return CadastroUsuarioDto
	 */
	private CadastroTarefaDto converterCadastroTarefaDto(Tarefa tarefa) {
		CadastroTarefaDto cadastroTarefaDto = new CadastroTarefaDto();
		cadastroTarefaDto.setId(tarefa.getId());
		cadastroTarefaDto.setNome(tarefa.getNome());
		cadastroTarefaDto.setTipo(tarefa.getTipo());
		
		
		return cadastroTarefaDto;
	}
}
