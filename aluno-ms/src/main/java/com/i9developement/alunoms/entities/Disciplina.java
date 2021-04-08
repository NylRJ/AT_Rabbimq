package com.i9developement.alunoms.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "tb_disciplina")
public class Disciplina extends RepresentationModel<Disciplina> implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String disciplinaName;
	private String disciplinaCodigo;
	
	@JsonBackReference
	@ManyToOne
	private Turma turma;

	public Disciplina() {
		super();
	}

	public Disciplina(Long id, String disciplinaName, String disciplinaCodigo, Turma turma) {
		super();
		this.id = id;
		this.disciplinaName = disciplinaName;
		this.disciplinaCodigo = disciplinaCodigo;
		this.turma = turma;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDisciplinaName() {
		return disciplinaName;
	}

	public void setDisciplinaName(String disciplinaName) {
		this.disciplinaName = disciplinaName;
	}

	public String getDisciplinaCodigo() {
		return disciplinaCodigo;
	}

	public void setDisciplinaCodigo(String disciplinaCodigo) {
		this.disciplinaCodigo = disciplinaCodigo;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
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
		Disciplina other = (Disciplina) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Disciplina [id=" + id + ", disciplinaName=" + disciplinaName + ", disciplinaCodigo=" + disciplinaCodigo
				+ ", turma=" + turma + "]";
	}
	
	
	
}
