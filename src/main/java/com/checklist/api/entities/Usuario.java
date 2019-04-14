package com.checklist.api.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {


	private static final long serialVersionUID = -6897559866605736879L;
	
	private Long id;
	private String nome;
	private String Cpf;
	private Date dataCriacao;
	private Date dataAtualizacao;
	private String email;
	private String senha;
	private List<Tarefa> tarefas;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "nome", nullable = false)
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Column(name = "cpf", nullable = false)
	public String getCpf() {
		return Cpf;
	}
	public void setCpf(String cpf) {
		Cpf = cpf;
	}
	
	@Column(name = "data_criacao", nullable = false)
	public Date getDataCriaca() {
		return dataCriacao;
	}
	public void setDataCriaca(Date dataCriaca) {
		this.dataCriacao = dataCriaca;
	}
	
	@Column(name = "data_atualizacao", nullable = false)
	public Date getDataAtualizaca() {
		return dataAtualizacao;
	}
	public void setDataAtualizaca(Date dataAtualizaca) {
		this.dataAtualizacao = dataAtualizaca;
	}
	
	@Column(name = "email", nullable = false)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "senha", nullable = false)
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Tarefa> getTarefas() {
		return tarefas;
	}
	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	} 
	
	@PreUpdate
    public void preUpdate() {
        dataAtualizacao = new Date();
    }
     
    @PrePersist
    public void prePersist() {
        final Date atual = new Date();
        dataCriacao = atual;
        dataAtualizacao = atual;
    }
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", Cpf=" + Cpf + ", dataCriacao=" + dataCriacao
				+ ", dataAtualizacao=" + dataAtualizacao + ", email=" + email + ", senha=" + senha + "]";
	}
	
    
}
