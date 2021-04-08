package com.i9developement.school_class.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

@Entity
@Table(name = "student")
public class Student extends RepresentationModel<Student> implements Serializable {

	
	private static final long serialVersionUID = -4153284491944932945L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String email;

	@ManyToOne
	private ClassStudent classStudent;

	public Student() {

	}

	public Student(String name, String email, ClassStudent classStudent) {
		super();
		this.name = name;
		this.email = email;
		this.classStudent = classStudent;
	}

	public static Student create(Student student) {

		return new ModelMapper().map(student, Student.class);

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
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", email=" + email + ", classStudent=" + classStudent + "]";
	}

}
