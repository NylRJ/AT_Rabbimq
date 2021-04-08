package com.i9developement.schoolClass.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.i9developement.schoolClass.data.vo.StudentVo;
import com.i9developement.schoolClass.entity.Student;
import com.i9developement.schoolClass.exception.ResourceNotFoundException;
import com.i9developement.schoolClass.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;

	public StudentService() {
		
	}
	
	public StudentVo create(StudentVo studentVo) {
		StudentVo StudentVoVor = StudentVo.create(studentRepository.save(Student.create(studentVo)));
		return StudentVoVor;
	}

	public Page<StudentVo> findAll(Pageable pageable) {
		var page = studentRepository.findAll(pageable);

		return page.map(this::convertToStudentVo);
	}

	private StudentVo convertToStudentVo(Student student) {

		return StudentVo.create(student);
	}

	public StudentVo findById(Long id) {
		var entity = studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

		return StudentVo.create(entity);
	}

	public StudentVo update(StudentVo studentVo) {
		final Optional<Student> opclassroom = studentRepository.findById(studentVo.getId());
		if (!opclassroom.isPresent()) {
			new ResourceNotFoundException("No records found for this ID");
		}

		return StudentVo.create(studentRepository.save(Student.create(studentVo)));
	}

	public void detete(Long id) {
		var entity = studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

		studentRepository.delete(entity);
	}

}
