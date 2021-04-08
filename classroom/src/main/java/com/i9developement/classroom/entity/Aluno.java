package com.i9developement.classroom.entity;

import java.io.Serializable;

public class Aluno  implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	
	private Long id;
	private String matricula;
	private String nome;
	private String email;
	
	
	private Turma turma;
	
	
	public Aluno() {
		super();
	}
	public Aluno(Long id, String matricula, String nome, String email, Turma turma) {
		super();
		this.id = id;
		this.matricula = matricula;
		this.nome = nome;
		this.email = email;
		this.turma = turma;
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
		Aluno other = (Aluno) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
