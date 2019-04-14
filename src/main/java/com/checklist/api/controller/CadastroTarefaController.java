package com.checklist.api.controller;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
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

import com.checklist.api.dtos.TarefaDto;
import com.checklist.api.entities.Tarefa;
import com.checklist.api.responses.Response;
import com.checklist.api.services.TarefaService;



@RestController
@RequestMapping("/api/cadastrar-tarefa")
@CrossOrigin(origins = "*")
public class CadastroTarefaController {
	
	private static final Logger log = LoggerFactory.getLogger(CadastroTarefaController.class);
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	private TarefaService tarefaService;
	
	public CadastroTarefaController(){
		
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
	public ResponseEntity<Response<TarefaDto>> cadastrar(@Valid @RequestBody TarefaDto tarefaDto, BindingResult result) throws NoSuchAlgorithmException {
		
		log.info("Cadastrando tarefa: {}", tarefaDto.toString());
		Response<TarefaDto> response = new Response<TarefaDto>();
	
		Tarefa tarefa = this.converterDtoParaTarefa(tarefaDto, result);
		
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
	

	
	private Tarefa converterDtoParaTarefa(TarefaDto tarefaDto, BindingResult result)
			throws NoSuchAlgorithmException {
		Tarefa tarefa = new Tarefa();
		tarefa.setNomeTarefa(tarefaDto.getNomeTarefa());
		tarefa.setFinalizada(tarefaDto.getFinalizada());
		tarefa.setFinalizada(tarefaDto.getFinalizada());
		if(tarefaDto.getFinalizada()) {
			Date data = new Date(System.currentTimeMillis());
			tarefa.setDataRealizacao(data);
			tarefa.setFinalizada(true);
		}
		else {
			tarefa.setFinalizada(false);
		}
		tarefa.setTipo(tarefaDto.getTipo());
		
		
		return tarefa;
	}
	
	private TarefaDto converterCadastroTarefaDto(Tarefa tarefa) {
		TarefaDto tarefaDto = new TarefaDto();
		tarefaDto.setId(tarefa.getId());
		tarefaDto.setNomeTarefa(tarefa.getNomeTarefa());
//		tarefaDto.setDataRealizacao(ttarefa.getDataRealizacao()));
		tarefaDto.setUsuarioId(tarefa.getUsuario().getId());
		tarefaDto.setTipo(tarefa.getTipo());
		return tarefaDto;
	}

}
