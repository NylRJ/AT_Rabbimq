package com.i9developement.schoolClass.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.i9developement.schoolClass.data.vo.ClassStudentVo;
import com.i9developement.schoolClass.entity.ClassStudent;
import com.i9developement.schoolClass.exception.ResourceNotFoundException;
import com.i9developement.schoolClass.repository.ClassStudentRepository;

@Service
public class ClassStudentService {
		
	@Autowired
	private ClassStudentRepository classStudentRepository;

	public ClassStudentService() {
		
	}
	
	public ClassStudentVo create(ClassStudentVo classStudentVo) {
		ClassStudentVo classStudentVoVor = ClassStudentVo.create(classStudentRepository.save(ClassStudent.create(classStudentVo)));
		return classStudentVoVor;
	}

	public Page<ClassStudentVo> findAll(Pageable pageable) {
		var page = classStudentRepository.findAll(pageable);

		return page.map(this::convertToClassroomVo);
	}

	private ClassStudentVo convertToClassroomVo(ClassStudent classStudent) {

		return ClassStudentVo.create(classStudent);
	}

	public ClassStudentVo findById(Long id) {
		var entity = classStudentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

		return ClassStudentVo.create(entity);
	}

	public ClassStudentVo update(ClassStudentVo classStudentVo) {
		final Optional<ClassStudent> opclassroom = classStudentRepository.findById(classStudentVo.getId());
		if (!opclassroom.isPresent()) {
			new ResourceNotFoundException("No records found for this ID");
		}

		return ClassStudentVo.create(classStudentRepository.save(ClassStudent.create(classStudentVo)));
	}

	public void detete(Long id) {
		var entity = classStudentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

		classStudentRepository.delete(entity);
	}


}
