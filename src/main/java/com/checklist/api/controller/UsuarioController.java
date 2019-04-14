package com.checklist.api.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.checklist.api.dtos.UsuarioDto;
import com.checklist.api.responses.Response;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

	@PostMapping
	public ResponseEntity<Response<UsuarioDto>> cadastrar(@Valid @RequestBody UsuarioDto usuarioDto, BindingResult result) {
		Response<UsuarioDto> response = new Response<UsuarioDto>();
		
		if(result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		usuarioDto.setId(1l);
		response.setData(usuarioDto);
		
		return ResponseEntity.ok(response);
		
	}
}
