package com.i9developement.chamams.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.i9developement.chamams.data.vo.ClassroomVo;
import com.i9developement.chamams.services.ClassroomService;

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



}
