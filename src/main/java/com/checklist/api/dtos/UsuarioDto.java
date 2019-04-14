package com.checklist.api.dtos;

public class UsuarioDto {

	private Long id;
	private String nome;
	private String cpf;
	
	public UsuarioDto() {
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCnpj(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public String toString() {
		return "UsuarioDto [id=" + id + ", nome=" + nome + ", cpf=" + cpf + "]";
	}
	
	
}
