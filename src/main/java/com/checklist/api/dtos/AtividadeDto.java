package com.checklist.api.dtos;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;

public class AtividadeDto {

	private Optional<Long> id = Optional.empty();
	private String data;
	private String tipo;
	private String descricao;
	private Long tarefaId;
	
	
	public Optional<Long> getId() {
		return id;
	}
	public void setId(Optional<Long> id) {
		this.id = id;
	}
	
	@NotEmpty(message = "Data não pode ser vaia.")
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	public Long getTarefaId() {
		return tarefaId;
	}
	public void setTarefaId(Long tarefaId) {
		this.tarefaId = tarefaId;
	}
	@Override
	public String toString() {
		return "AtividadeDto [id=" + id + ", data=" + data + ", tipo=" + tipo + ", descricao=" + descricao
				+ ", tarefaId=" + tarefaId + "]";
	}
	
	
	
}
