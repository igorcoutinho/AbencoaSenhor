package com.checklist.api.dtos;

import java.util.Date;
import java.util.Optional;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.checklist.api.enums.TipoPeriodicidade;

public class TarefaDto {

	private Long id;
	private String nomeTarefa;
	private Long usuarioId;
	private Boolean finalizada;
	private String dataRealizacao;
	private TipoPeriodicidade tipo;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	
	@NotEmpty(message = "Nome é obrigatório")
	@Length(min = 5, max = 200, message = "O nome deve ter entre 5 e 200 caracteres.")
	public String getNomeTarefa() {
		return nomeTarefa;
	}
	public void setNomeTarefa(String nomeTarefa) {
		this.nomeTarefa = nomeTarefa;
	}
	
	public Long getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}
	
	public Boolean getFinalizada() {
		return finalizada;
	}
	public void setFinalizada(Boolean finalizada) {
		this.finalizada = finalizada;
	}
	
	
	public String getDataRealizacao() {
		return dataRealizacao;
	}
	public void setDataRealizacao(String dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}
	
	
	public TipoPeriodicidade getTipo() {
		return tipo;
	}
	public void setTipo(TipoPeriodicidade tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public String toString() {
		return "TarefaDto [id=" + id + ", nomeTarefa=" + nomeTarefa + ", usuarioId=" + usuarioId + ", finalizada="
				+ finalizada + ", dataRealizacao=" + dataRealizacao + "]";
	}
	
	
	
	
}
