package com.i9developement.classroom.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.i9developement.classroom.data.vo.ClassroomVo;
import com.i9developement.classroom.entity.valueObject.Presence;

@Entity
@Table(name = "classroom")
public class Classroom implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "student_name")
	private String student_name;

	@Column(name = "student_matricula")
	private String studentmt_matricula;

	@Column(name = "student_id")
	private Long student_id;

	@Column(name = "classcode", nullable = false, length = 10)
	private String classcode;

	@Column(name = "classname", nullable = false, length = 255)
	private String classname;

	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@Column(name = "data", nullable = false)
	private Date data;

	@Column(name = "presence", nullable = false)
	private Integer presence;

	public Classroom() {

	}

	public Classroom(String student_name, String studentmt_matricula, Long student_id, String classcode,
			String classname, Date data, Presence presence) {
		super();

		this.student_name = student_name;
		this.studentmt_matricula = studentmt_matricula;
		this.student_id = student_id;
		this.classcode = classcode;
		this.classname = classname;
		this.data = data;
		this.presence = (presence == null) ? null : presence.getCodigo();

	}

	public Classroom(Long id, String student_name, String studentmt_matricula, Long student_id, String classcode,
			String classname, Date data, Presence presence) {
		super();
		this.id = id;
		this.student_name = student_name;
		this.studentmt_matricula = studentmt_matricula;
		this.student_id = student_id;
		this.classcode = classcode;
		this.classname = classname;
		this.data = data;
		this.presence = (presence == null) ? null : presence.getCodigo();

	}

	public static Classroom create(ClassroomVo classroomVo) {

		return new Classroom(classroomVo.getId(), classroomVo.getStudent_name(), classroomVo.getStudentmt_matricula(),
				classroomVo.getStudent_id(), classroomVo.getClasscode(), classroomVo.getClassname(),
				classroomVo.getData(), Presence.toEnum(classroomVo.getPresence()));

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

	public Presence getPresence() {
		return Presence.toEnum(this.presence);
	}

	public void setPresence(Presence presence) {
		this.presence = presence.getCodigo();
	}

	@Override
	public String toString() {
		return "Classroom [id=" + id + ", student_name=" + student_name + ", studentmt_matricula=" + studentmt_matricula
				+ ", classcode=" + classcode + ", classname=" + classname + ", data=" + data + ", presence=" + presence
				+ "]";
	}

}
