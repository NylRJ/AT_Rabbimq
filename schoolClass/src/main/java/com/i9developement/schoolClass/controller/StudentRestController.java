package com.i9developement.schoolClass.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.i9developement.schoolClass.data.vo.StudentVo;
import com.i9developement.schoolClass.services.StudentService;

@RestController
@RequestMapping(name = "/estudante")
public class StudentRestController {
	
	
	private final StudentService studentService;

	private final PagedResourcesAssembler<StudentVo> assembler;
	
	@Autowired
	public StudentRestController(StudentService studentService, PagedResourcesAssembler<StudentVo> assembler) {
		super();
		this.studentService = studentService;
		this.assembler = assembler;
	}
	
	
	@GetMapping(value = "/{id}")
	public StudentVo findById(@PathVariable Long id) {
		StudentVo classStudentVo = studentService.findById(id);
		classStudentVo.add(linkTo(methodOn(StudentRestController.class).findById(id)).withSelfRel());
		return classStudentVo;
	}
	

	@GetMapping()
	public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "12") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {

		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "classname"));

		Page<StudentVo> studentVos = studentService.findAll(pageable);
		studentVos.stream()
				.forEach(c -> c.add(linkTo(methodOn(StudentRestController.class).findById(c.getId())).withSelfRel()));
		PagedModel<EntityModel<StudentVo>> pageModel = assembler.toModel(studentVos);
		return new ResponseEntity<>(pageModel, HttpStatus.OK);
	}

	@PostMapping()
	public StudentVo create(@RequestBody StudentVo classStudentVo) {

		StudentVo classVo = studentService.create(classStudentVo);
		classVo.add(linkTo(methodOn(StudentRestController.class).findById(classVo.getId())).withSelfRel());
		return classVo;
	}


	@PutMapping()
	public StudentVo update(@RequestBody StudentVo studentVo) {
		
		StudentVo rStudentVo = studentService.update(studentVo);
		rStudentVo.add(linkTo(methodOn(StudentRestController.class).findById(rStudentVo.getId())).withSelfRel());
		return rStudentVo;
	}

}
