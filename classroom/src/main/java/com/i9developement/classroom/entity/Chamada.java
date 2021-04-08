package com.i9developement.classroom.entity;

import java.util.Date;

public class Chamada {
	private Long turmaId;
	private Date data; 
	private Integer presence;
	
	public Chamada() {
		super();
		
	}
	
	public Chamada(Long turmaId, Date data, Integer presence) {
		super();
		this.turmaId = turmaId;
		this.data = data;
		this.presence = presence;
	}

	public Long getTurmaId() {
		return turmaId;
	}

	public void setTurmaId(Long turmaId) {
		this.turmaId = turmaId;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getPresence() {
		return presence;
	}

	public void setPresence(Integer presence) {
		this.presence = presence;
	}
	
}
