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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.checklist.api.dtos.TarefaDto;
import com.checklist.api.entities.Tarefa;
import com.checklist.api.responses.Response;
import com.checklist.api.services.TarefaService;


@RestController
@RequestMapping("/api/tarefas")
@CrossOrigin(origins = "*")
public class TarefaController {

	private static final Logger log = LoggerFactory.getLogger(TarefaController.class);
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	private TarefaService tarefaService;

	public TarefaController() {
	}
	
	/**
	 * Atualiza os dados de uma tarefa.
	 * 
	 * @param id
	 * @param tarefaDto
	 * @param result
	 * @return ResponseEntity<Response<TarefaDto>>
	 * @throws NoSuchAlgorithmException
	 */
	@PutMapping
	public ResponseEntity<Response<TarefaDto>> atualizar(@PathVariable("id") Long id,
			@Valid @RequestBody TarefaDto tarefaDto, BindingResult result) throws NoSuchAlgorithmException {
		log.info("Atualizando tarefa: {}", tarefaDto.toString());
		
		Response<TarefaDto> response = new Response<TarefaDto>();
	

		Optional<Tarefa> tarefa = this.tarefaService.buscarPorId(id);
		if (!tarefa.isPresent()) {
			result.addError(new ObjectError("tarefa", "Tarefa não encontrada."));
		}

		this.atualizarDadosTarefa(tarefa.get(), tarefaDto, result);

		if (result.hasErrors()) {
			log.error("Erro validando tarefa: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.tarefaService.persistir(tarefa.get());
		response.setData(this.converterTarefaDto(tarefa.get()));

		return ResponseEntity.ok(response);
	}
	
	/**
	 * Atualiza os dados da tarefa com base nos dados encontrados no DTO.
	 * 
	 * @param tarefa
	 * @param tarefaDto
	 * @param result
	 * @throws NoSuchAlgorithmException
	 */
	private void atualizarDadosTarefa(Tarefa tarefa, TarefaDto tarefaDto, BindingResult result)
			throws NoSuchAlgorithmException {
		tarefa.setNomeTarefa(tarefaDto.getNomeTarefa());

		if (!tarefa.getNomeTarefa().equals(tarefaDto.getNomeTarefa())) {
			this.tarefaService.buscarPorId(tarefaDto.getId())
					.ifPresent(func -> result.addError(new ObjectError("tarefa", "Tarefa já existente.")));
			tarefa.setNomeTarefa(tarefaDto.getNomeTarefa());
		}
		
		tarefa.setFinalizada(tarefaDto.getFinalizada());
		if(tarefaDto.getFinalizada()) {
			Date data = new Date(System.currentTimeMillis());
			tarefa.setDataRealizacao(data);
		}

	}
	
	/**
	 * Retorna um DTO com os dados de um funcionário.
	 * 
	 * @param funcionario
	 * @return FuncionarioDto
	 */
	private TarefaDto converterTarefaDto(Tarefa tarefa) {
		TarefaDto tarefaDto = new TarefaDto();
		tarefaDto.setId(tarefa.getId());
		tarefaDto.setNomeTarefa(tarefa.getNomeTarefa());
//		tarefaDto.setDataRealizacao(this.dateFormat.format(tarefa.getDataRealizacao()));
		tarefaDto.setUsuarioId(tarefa.getUsuario().getId());
		return tarefaDto;
	}

	
}
