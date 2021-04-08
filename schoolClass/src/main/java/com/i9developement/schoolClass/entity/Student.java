package com.i9developement.schoolClass.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import com.i9developement.schoolClass.data.vo.StudentVo;
@Entity
@Table
public class Student implements Serializable {

	

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	
	@ManyToOne()
	private ClassStudent classStudent;

	public Student() {

	}

	

}
