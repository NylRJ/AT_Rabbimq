package com.i9developement.classroom.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

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

import com.i9developement.classroom.data.vo.ClassroomVo;
import com.i9developement.classroom.entity.Chamada;
import com.i9developement.classroom.services.ClassroomService;

@RestController
@RequestMapping(value = "/classrooms")
public class ClassStudentController {
	
	
	private final ClassroomService classroomService;
	private final PagedResourcesAssembler<ClassroomVo> assembler;

	@Autowired
	public ClassStudentController(ClassroomService classroomService, PagedResourcesAssembler<ClassroomVo> assembler) {
		super();
		this.classroomService = classroomService;
		this.assembler = assembler;
	}

	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public ClassroomVo findById(@PathVariable("id") Long id) {
		ClassroomVo classroomVo = classroomService.findById(id);
		classroomVo.add(linkTo(methodOn(ClassStudentController.class).findById(id)).withSelfRel());
		return classroomVo;
	}

	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "12") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {

		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "classname"));

		Page<ClassroomVo> classrooms = classroomService.findAll(pageable);
		classrooms.stream()
				.forEach(c -> c.add(linkTo(methodOn(ClassStudentController.class).findById(c.getId())).withSelfRel()));
		PagedModel<EntityModel<ClassroomVo>> pageModel = assembler.toModel(classrooms);
		return new ResponseEntity<>(pageModel, HttpStatus.OK);
	}


	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public List<ClassroomVo> chamada(@RequestBody Chamada chamada) {

		List<ClassroomVo> classVos = classroomService.chamada(chamada);
		List<ClassroomVo> ListclassVos = new ArrayList<>();
		
		for (ClassroomVo classVo : classVos) {
			Long codigo = classVo.getId();
			
			classVo.add(linkTo(methodOn(ClassStudentController.class).findById(codigo)).withSelfRel());
			
			ListclassVos.add(classVo);
		}
		
		return ListclassVos;
	}


	@PutMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public ClassroomVo update(@RequestBody ClassroomVo classroomVo, @PathVariable("id") Long id) {
		classroomVo.setId(id);
		ClassroomVo classVo = classroomService.update(classroomVo);
		classVo.add(linkTo(methodOn(ClassStudentController.class).findById(classVo.getId())).withSelfRel());
		return classVo;
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		classroomService.detete(id);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping(value = "/{school_class}", produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public ClassroomVo attendence(@RequestBody ClassroomVo classroomVo) {

		ClassroomVo classVo = classroomService.create(classroomVo);
		classVo.add(linkTo(methodOn(ClassStudentController.class).findById(classVo.getId())).withSelfRel());
		return classVo;
	}

}
