package com.i9developement.schoolClass.data.vo;

import java.io.Serializable;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.i9developement.schoolClass.entity.ClassStudent;
import com.i9developement.schoolClass.entity.Student;

public class StudentVo extends RepresentationModel<StudentVo> implements Serializable {

	
	
	private static final long serialVersionUID = 3512791118467993435L;
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("classStudent")
	private ClassStudent classStudent;

	public StudentVo() {

	}

	public StudentVo(String name, String email, ClassStudent classStudent) {
		super();
		this.name = name;
		this.email = email;
		this.classStudent = classStudent;
	}

	public static StudentVo create(Student student) {

		return new ModelMapper().map(student, StudentVo.class);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ClassStudent getClassStudent() {
		return classStudent;
	}

	public void setClassStudent(ClassStudent classStudent) {
		this.classStudent = classStudent;
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
		StudentVo other = (StudentVo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", email=" + email + ", classStudent=" + classStudent + "]";
	}

}