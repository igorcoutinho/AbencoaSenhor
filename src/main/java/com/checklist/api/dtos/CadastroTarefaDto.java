package com.checklist.api.dtos;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.checklist.api.enums.TipoPeriodicidade;

public class CadastroTarefaDto {

	private Long id;
	private String nome;
	private String tipo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@NotEmpty(message = "Nome é obrigatório")
	@Length(min = 5, max = 200, message = "O nome deve ter entre 5 e 200 caracteres.")
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@NotEmpty(message = "O tipo é obrigatório")
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	
	
}
