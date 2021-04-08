package com.i9developement.school_class.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.i9developement.school_class.entity.ClassStudent;
import com.i9developement.school_class.exception.ResourceNotFoundException;
import com.i9developement.school_class.repository.ClassStudentRepository;

@Service
public class ClassStudentService {

	private final ClassStudentRepository repository;

	
	@Autowired
	public ClassStudentService(ClassStudentRepository repository) {
		super();

		this.repository = repository;
	}

	public Page<ClassStudent> findAll(Pageable pageable) {
		var page = repository.findAll(pageable);

		return page.map(this::convertToClassStudent);
	}

	private ClassStudent convertToClassStudent(ClassStudent classStudent) {

		return ClassStudent.create(classStudent);
	}
	
	
	public ClassStudent create(ClassStudent classStudent) {
		
		return repository.save(classStudent);
	}

	public ClassStudent findById(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

		return ClassStudent.create(entity);
	}

	public ClassStudent update(ClassStudent classStudent) {
		final Optional<ClassStudent> opclassStudent = repository.findById(classStudent.getId());
		if (!opclassStudent.isPresent()) {
			new ResourceNotFoundException("No records found for this ID");
		}

		return repository.save(classStudent);
	}

	public void detete(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

		repository.delete(entity);
	}

}
