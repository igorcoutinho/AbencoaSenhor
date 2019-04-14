package com.checklist.api.controller;

import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Response<UsuarioDto>> cadastrar(@RequestBody UsuarioDto usuarioDto) {
		Response<UsuarioDto> response = new Response<UsuarioDto>();
		
		usuarioDto.setId(1l);
		response.setData(usuarioDto);
		
		return ResponseEntity.ok(response);
		
	}
}
