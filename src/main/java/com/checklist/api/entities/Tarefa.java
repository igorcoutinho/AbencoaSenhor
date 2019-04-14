package com.checklist.api.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.checklist.api.enums.TipoPeriodicidade;




@Entity
@Table(name = "tarefa")
public class Tarefa implements Serializable {
	private static final long serialVersionUID = -5754246207015712518L;
	
	private Long id;
	private String nomeTarefa;
	private Date dataCriacao;
	private Date dataAtualizacao;
	private Usuario usuario;
	private TipoPeriodicidade tipo;
	private Date dataRealizacao;
	private Boolean finalizada;
	private List<Atividade> atividades;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "nome_tarefa", nullable = false)
	public String getNomeTarefa() {
		return nomeTarefa;
	}
	public void setNomeTarefa(String nomeTarefa) {
		this.nomeTarefa = nomeTarefa;
	}
	
	@Column(name = "data_criacao", nullable = false)
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	@Column(name = "data_atualizacao", nullable = false)
	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}
	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo", nullable = false)
	public TipoPeriodicidade getTipo() {
		return tipo;
	}
	public void setTipo(TipoPeriodicidade tipo) {
		this.tipo = tipo;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_realizacao", nullable = true)
	public Date getDataRealizacao() {
		return dataRealizacao;
	}
	public void setDataRealizacao(Date dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}
	
	public Boolean getFinalizada() {
		return finalizada;
	}
	public void setFinalizada(Boolean finalizada) {
		this.finalizada = finalizada;
	}
	
	@OneToMany(mappedBy = "tarefa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Atividade> getAtividades() {
		return atividades;
	}
	public void setAtividades(List<Atividade> atividades) {
		this.atividades = atividades;
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
		return "Tarefa [id=" + id + ", nomeTarefa=" + nomeTarefa + ", dataCriacao=" + dataCriacao + ", dataAtualizacao="
				+ dataAtualizacao + ", usuario=" + usuario + ", tipo=" + tipo + ", dataRealizacao=" + dataRealizacao
				+ ", finalizada=" + finalizada + "]";
	}
    
    
	
}
