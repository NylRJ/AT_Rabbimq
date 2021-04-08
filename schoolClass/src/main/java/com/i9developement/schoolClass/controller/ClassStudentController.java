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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.i9developement.schoolClass.data.vo.ClassStudentVo;
import com.i9developement.schoolClass.services.ClassStudentService;

@RestController
@RequestMapping(name = "/schoolclass")
public class ClassStudentController {
	
	@Autowired
	private final ClassStudentService classStudentService;
	
	@Autowired
	private final PagedResourcesAssembler<ClassStudentVo> assembler;




	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public ClassStudentVo findById(@PathVariable("id") Long id) {
		ClassStudentVo classStudentVo = classStudentService.findById(id);
		classStudentVo.add(linkTo(methodOn(ClassStudentController.class).findById(id)).withSelfRel());
		return classStudentVo;
	}

	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "12") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {

		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "classname"));

		Page<ClassStudentVo> classStudents = classStudentService.findAll(pageable);
		classStudents.stream()
				.forEach(c -> c.add(linkTo(methodOn(ClassStudentController.class).findById(c.getId())).withSelfRel()));
		PagedModel<EntityModel<ClassStudentVo>> pageModel = assembler.toModel(classStudents);
		return new ResponseEntity<>(pageModel, HttpStatus.OK);
	}

	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public ClassStudentVo create(@RequestBody ClassStudentVo classStudentVo) {

		ClassStudentVo classVo = classStudentService.create(classStudentVo);
		classVo.add(linkTo(methodOn(ClassStudentController.class).findById(classVo.getId())).withSelfRel());
		return classVo;
	}

	@PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public ClassStudentVo update(@RequestBody ClassStudentVo classStudentVo) {
		
		ClassStudentVo classVo = classStudentService.update(classStudentVo);
		classVo.add(linkTo(methodOn(ClassStudentController.class).findById(classVo.getId())).withSelfRel());
		return classVo;
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		classStudentService.detete(id);
		return ResponseEntity.ok().build();
	}
}
