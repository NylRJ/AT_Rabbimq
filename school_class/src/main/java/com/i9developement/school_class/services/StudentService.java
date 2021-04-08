package com.i9developement.school_class.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.i9developement.school_class.entity.Student;
import com.i9developement.school_class.exception.ResourceNotFoundException;
import com.i9developement.school_class.repository.StudentRepository;

@Service
public class StudentService {

	private final StudentRepository repository;

	@Autowired
	public StudentService(StudentRepository repository) {
		super();

		this.repository = repository;
	}

	public Student create(Student studentVo) {

		return repository.save(Student.create(studentVo));
	}

	public Page<Student> findAll(Pageable pageable) {
		var page = repository.findAll(pageable);

		return page.map(this::convertToStudentVo);
	}

	private Student convertToStudentVo(Student student) {

		return Student.create(student);
	}

	public Student findById(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

		return Student.create(entity);
	}

	public Student update(Student student) {
		final Optional<Student> opstudent = repository.findById(student.getId());
		if (!opstudent.isPresent()) {
			new ResourceNotFoundException("No records found for this ID");
		}

		return repository.save(student);
	}

	public void detete(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

		repository.delete(entity);
	}

}
