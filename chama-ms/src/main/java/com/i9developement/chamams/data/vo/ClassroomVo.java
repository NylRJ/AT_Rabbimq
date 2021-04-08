package com.i9developement.chamams.data.vo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

import com.i9developement.chamams.entity.Classroom;



public class ClassroomVo extends RepresentationModel<ClassroomVo> implements Serializable {

	private static final long serialVersionUID = 1L;

	
	private Long id;
	
	private String student_name;

	
	private String studentmt_matricula;
	
	private Long student_id;

	
	private String classcode;

	
	private String classname;

	
	private Date data;

	
	private Integer presence;

	
	public ClassroomVo() {

	}
	

	
	
	public ClassroomVo(Long id, String student_name, String studentmt_matricula, Long student_id, String classcode,
			String classname, Date data, Integer presence) {
		super();
		this.id = id;
		this.student_name = student_name;
		this.studentmt_matricula = studentmt_matricula;
		this.student_id = student_id;
		this.classcode = classcode;
		this.classname = classname;
		this.data = data;
		this.presence = presence;
	}




	public static ClassroomVo create(Classroom classroom) {

		return new ClassroomVo(classroom.getId() ,classroom.getStudent_name(), classroom.getStudentmt_matricula(), classroom.getStudent_id(), 
				classroom.getClasscode(), classroom.getClassname(), classroom.getData(), classroom.getPresence().getCodigo());

	}

	public Integer getPresence() {
		return presence;
	}

	public void setPresence(Integer presence) {
		this.presence = presence;
	}

	
	public Long getStudent_id() {
		return student_id;
	}

	public void setStudent_id(Long student_id) {
		this.student_id = student_id;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStudent_name() {
		return student_name;
	}

	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}

	public String getStudentmt_matricula() {
		return studentmt_matricula;
	}

	public void setStudent_id(String studentmt_matricula) {
		this.studentmt_matricula = studentmt_matricula;
	}

	public String getClasscode() {
		return classcode;
	}

	public void setClasscode(String classcode) {
		this.classcode = classcode;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	

}
