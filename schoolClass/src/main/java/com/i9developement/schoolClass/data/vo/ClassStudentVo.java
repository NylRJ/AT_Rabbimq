package com.i9developement.schoolClass.data.vo;

import java.io.Serializable;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.i9developement.schoolClass.entity.ClassStudent;
import com.i9developement.schoolClass.entity.Student;

public class ClassStudentVo extends RepresentationModel<ClassStudentVo> implements Serializable{
	

	
	private static final long serialVersionUID = 3158415392299929702L;

	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("ClassName")
	private String ClassName;
	
	@JsonProperty("ClassCode")
	private String ClassCode;

	@JsonProperty("students")
	private List<Student> students;

	public ClassStudentVo() {
		super();
	}

	public ClassStudentVo(String className, String classCode) {
		super();
		ClassName = className;
		ClassCode = classCode;
	}

	public ClassStudentVo(String className, String classCode, List<Student> students) {
		super();
		ClassName = className;
		ClassCode = classCode;
		this.students = students;
	}
	
	public static ClassStudentVo create(ClassStudent classStudent) {
		
		return new ModelMapper().map(classStudent, ClassStudentVo.class);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClassName() {
		return ClassName;
	}

	public void setClassName(String className) {
		ClassName = className;
	}

	public String getClassCode() {
		return ClassCode;
	}

	public void setClassCode(String classCode) {
		ClassCode = classCode;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
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
		ClassStudentVo other = (ClassStudentVo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ClassStudent [id=" + id + ", ClassName=" + ClassName + ", ClassCode=" + ClassCode + ", students="
				+ students + "]";
	}
	
}
