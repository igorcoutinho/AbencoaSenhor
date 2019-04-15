package com.checklist.api.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.checklist.api.dtos.AtividadeDto;
import com.checklist.api.entities.Atividade;
import com.checklist.api.entities.Tarefa;
import com.checklist.api.enums.TipoPeriodicidade;
import com.checklist.api.responses.Response;
import com.checklist.api.services.AtividadeService;
import com.checklist.api.services.TarefaService;






@RestController
@RequestMapping("/api/atividades")
@CrossOrigin(origins = "*")
public class AtividadeController {

	private static final Logger log = LoggerFactory.getLogger(AtividadeController.class);
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	private AtividadeService atividadeService;
	
	@Autowired
	private TarefaService tarefaService;
	
	@Value("${paginacao.qtd_por_pagina}")
	private int qtdPorPagina;
	
	public AtividadeController() {
		
	}
	
	/**
	 * Retorna a listagem de atividades de uma tarefa.
	 * 
	 * @param tarefaId
	 * @return ResponseEntity<Response<AtividadeDto>>
	 */
	@GetMapping(value = "/tarefa/{tarefaId}")
	public ResponseEntity<Response<Page<AtividadeDto>>> listarPorTarefaId(
			@PathVariable("tarefaId") Long tarefaId,
			@RequestParam(value = "pag", defaultValue = "0") int pag,
			@RequestParam(value = "ord", defaultValue = "id") String ord,
			@RequestParam(value = "dir", defaultValue = "DESC") String dir) {
		log.info("Buscando atividades por ID da tarefa: {}, página: {}", tarefaId, pag);
		Response<Page<AtividadeDto>> response = new Response<Page<AtividadeDto>>();

		
		PageRequest pageRequest = new PageRequest(pag, this.qtdPorPagina , Direction.valueOf(dir), ord);
		Page<Atividade> atividades = this.atividadeService.buscarPorTarefaId(tarefaId, pageRequest);
		Page<AtividadeDto> atividadeDto = atividades.map(atividade -> this.converterAtividadeDto(atividade));

		response.setData(atividadeDto);
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Retorna uma atividade por ID.
	 * 
	 * @param id
	 * @return ResponseEntity<Response<AtividadeDto>>
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<AtividadeDto>> listarPorId(@PathVariable("id") Long id) {
		log.info("Buscando atividade por ID: {}", id);
		Response<AtividadeDto> response = new Response<AtividadeDto>();
		Optional<Atividade> atividade = this.atividadeService.buscarPorId(id);

		if (!atividade.isPresent()) {
			log.info("Atividade não encontrada para o ID: {}", id);
			response.getErrors().add("Atividade não encontrada para o id " + id);
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(this.converterAtividadeDto(atividade.get()));
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Adiciona um novo atividade.
	 * 
	 * @param atividade
	 * @param result
	 * @return ResponseEntity<Response<AtividadeDto>>
	 * @throws ParseException 
	 */
	@PostMapping
	public ResponseEntity<Response<AtividadeDto>> adicionar(@Valid @RequestBody AtividadeDto atividadeDto,
			BindingResult result) throws ParseException {
		log.info("Adicionando atividade: {}", atividadeDto.toString());
		Response<AtividadeDto> response = new Response<AtividadeDto>();
		validarTarefa(atividadeDto, result);
		Atividade atividade = this.converterDtoParaAtividade(atividadeDto, result);

		if (result.hasErrors()) {
			log.error("Erro validando atividade: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		atividade = this.atividadeService.persistir(atividade);
		response.setData(this.converterAtividadeDto(atividade));
		return ResponseEntity.ok(response);
	}

	/**
	 * Atualiza os dados de um atividade.
	 * 
	 * @param id
	 * @param atividadeDto
	 * @return ResponseEntity<Response<Atividade>>
	 * @throws ParseException 
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<AtividadeDto>> atualizar(@PathVariable("id") Long id,
			@Valid @RequestBody AtividadeDto atividadeDto, BindingResult result) throws ParseException {
		log.info("Atualizando lançamento: {}", atividadeDto.toString());
		Response<AtividadeDto> response = new Response<AtividadeDto>();
		validarTarefa(atividadeDto, result);
		atividadeDto.setId(Optional.of(id));
		Atividade atividade = this.converterDtoParaAtividade(atividadeDto, result);

		if (result.hasErrors()) {
			log.error("Erro validando atividade: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		atividade = this.atividadeService.persistir(atividade);
		response.setData(this.converterAtividadeDto(atividade));
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Remove um atividade por ID.
	 * 
	 * @param id
	 * @return ResponseEntity<Response<Lancamento>>
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<String>> remover(@PathVariable("id") Long id) {
		log.info("Removendo atividade: {}", id);
		Response<String> response = new Response<String>();
		Optional<Atividade> atividade = this.atividadeService.buscarPorId(id);

		if (!atividade.isPresent()) {
			log.info("Erro ao remover devido ao atividade ID: {} ser inválido.", id);
			response.getErrors().add("Erro ao remover atividade. Registro não encontrado para o id " + id);
			return ResponseEntity.badRequest().body(response);
		}

		this.atividadeService.remover(id);
		return ResponseEntity.ok(new Response<String>());
	}

	/**
	 * Valida uma tarefa, verificando se ela é existente e válido no
	 * sistema.
	 * 
	 * @param atividadeDto
	 * @param result
	 */
	private void validarTarefa(AtividadeDto atividadeDto, BindingResult result) {
		if (atividadeDto.getTarefaId() == null) {
			result.addError(new ObjectError("tarefa", "Tarefa não informada."));
			return;
		}

		log.info("Validando funcionário id {}: ", atividadeDto.getTarefaId());
		Optional<Tarefa> tarefa = this.tarefaService.buscarPorId(atividadeDto.getTarefaId());
		if (!tarefa.isPresent()) {
			result.addError(new ObjectError("tarefa", "Tarefa não encontrada. ID inexistente."));
		}
	}
	
	/**
	 * Converte uma entidade atividade para seu respectivo DTO.
	 * 
	 * @param atividade
	 * @return AtividadeDto
	 */
	private AtividadeDto converterAtividadeDto(Atividade atividade) {
		AtividadeDto atividadeDto = new AtividadeDto();
		atividadeDto.setId(Optional.of(atividade.getId()));
		atividadeDto.setData(this.dateFormat.format(atividade.getData()));
		atividadeDto.setTipo(atividade.getTipo().toString());
		atividadeDto.setDescricao(atividade.getDescricao());
		atividadeDto.setTarefaId(atividade.getTarefa().getId());

		return atividadeDto;
	}
	
	/**
	 * Converte uma AtividadeDto para uma entidade Atividade.
	 * 
	 * @param atividadeDto
	 * @param result
	 * @return Atividade
	 * @throws ParseException 
	 */
	private Atividade converterDtoParaAtividade(AtividadeDto atividadeDto, BindingResult result) throws ParseException {
		Atividade atividade = new Atividade();

		if (atividadeDto.getId().isPresent()) {
			Optional<Atividade> atv = this.atividadeService.buscarPorId(atividadeDto.getId().get());
			if (atv.isPresent()) {
				atividade = atv.get();
			} else {
				result.addError(new ObjectError("atividade", "Atividade não encontrada."));
			}
		} else {
			atividade.setTarefa(new Tarefa());
			atividade.getTarefa().setId(atividadeDto.getTarefaId());
		}

		atividade.setDescricao(atividadeDto.getDescricao());
		atividade.setData(this.dateFormat.parse(atividadeDto.getData()));

		if (EnumUtils.isValidEnum(TipoPeriodicidade.class, atividadeDto.getTipo())) {
			atividade.setTipo(TipoPeriodicidade.valueOf(atividadeDto.getTipo()));
		} else {
			result.addError(new ObjectError("tipo", "Tipo inválido."));
		}

		return atividade;
	}
	

	
}
