package com.i9developement.school_class.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

@Entity
@Table
public class ClassStudent extends RepresentationModel<ClassStudent> implements Serializable {

	private static final long serialVersionUID = 7177052438870851478L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String ClassName;
	private String ClassCode;

	@OneToMany(mappedBy = "classStudent")
	private List<Student> students;

	public ClassStudent() {
		super();
	}

	public ClassStudent(String className, String classCode) {
		super();
		ClassName = className;
		ClassCode = classCode;
	}

	
	public static ClassStudent create(ClassStudent classStudent) {
		
		return new ModelMapper().map(classStudent, ClassStudent.class );
		
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
	public String toString() {
		return "ClassStudent [id=" + id + ", ClassName=" + ClassName + ", ClassCode=" + ClassCode + ", students="
				+ students + "]";
	}

}
