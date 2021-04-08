package com.i9developement.disciplinams.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i9developement.disciplinams.valueobject.Staus;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "tb_disciplina")
public class Disciplina extends RepresentationModel<Disciplina>  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String disciplinaName;
	private String disciplinaCodigo;
	private String matricula;
	private String nomeAluno;
	
	private Integer status;
	

	public Disciplina() {
		super();
	}
	
	public Disciplina(Long id, String disciplinaName, String disciplinaCodigo) {
		super();
		this.id = id;
		this.disciplinaName = disciplinaName;
		this.disciplinaCodigo = disciplinaCodigo;
	}


	public Disciplina(Long id, String disciplinaName, String disciplinaCodigo, Staus status) {
		super();
		this.id = id;
		this.disciplinaName = disciplinaName;
		this.disciplinaCodigo = disciplinaCodigo;
		this.status = (status == null) ? null : status.getCodigo();
	}

	

	public Staus getStatus() {
		
		return Staus.toEnum(this.status);
	}


	public void setStatus(Staus status) {
		this.status = status.getCodigo();
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

	
	

	


	public String getMatricula() {
		return matricula;
	}


	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}


	public String getNomeAluno() {
		return nomeAluno;
	}


	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
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
				+ ", matricula=" + matricula + ", nomeAluno=" + nomeAluno + "]";
	}


	

	
	
	
	
}
