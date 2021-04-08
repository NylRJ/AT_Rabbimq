package com.i9developement.alunoms.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "tb_turma")
public class Turma extends RepresentationModel<Turma> implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nomeTurma;
	private String codigoTurma;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "turma")
	List<Aluno> alunos;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "turma")
	List<Disciplina> disciplinas;
	
	
	
	public Turma() {
		super();
	}



	public Turma(Long id, String nomeTurma, String codigoTurma, List<Aluno> alunos, List<Disciplina> disciplinas) {
		super();
		this.id = id;
		this.nomeTurma = nomeTurma;
		this.codigoTurma = codigoTurma;
		this.alunos = alunos;
		this.disciplinas = disciplinas;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getNomeTurma() {
		return nomeTurma;
	}



	public void setNomeTurma(String nomeTurma) {
		this.nomeTurma = nomeTurma;
	}



	public String getCodigoTurma() {
		return codigoTurma;
	}



	public void setCodigoTurma(String codigoTurma) {
		this.codigoTurma = codigoTurma;
	}



	public List<Aluno> getAlunos() {
		return alunos;
	}



	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}



	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}



	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Turma other = (Turma) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Turma [id=" + id + ", nomeTurma=" + nomeTurma + ", codigoTurma=" + codigoTurma + ", alunos=" + alunos
				+ ", disciplinas=" + disciplinas + "]";
	}
	
	
	
}
