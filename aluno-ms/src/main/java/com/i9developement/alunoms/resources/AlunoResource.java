package com.i9developement.alunoms.resources;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.i9developement.alunoms.entities.Aluno;
import com.i9developement.alunoms.services.AlunoService;

import javassist.NotFoundException;
import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/alunos")
public class AlunoResource {
	
	@Autowired
	private AlunoService alunoService;
	
	@GetMapping
	public ResponseEntity<List<Aluno>> getAll(){
		
		List<Aluno> alunos = alunoService.findAll();
		List<Aluno> listAlunos = new ArrayList<>();

		for (Aluno aluno : alunos) {
			Long codigo = aluno.getId();
			aluno.add(linkTo(methodOn(AlunoResource.class).findById(codigo)).withSelfRel());
			listAlunos.add(aluno);
		}
		return ResponseEntity.ok(listAlunos);
	}
	@GetMapping(value = "/{id}")
	public ResponseEntity<Aluno> findById(@PathVariable Long id){
		
		Aluno aluno = alunoService.findById(id);
		if(aluno == null) {
			return ResponseEntity.notFound().build();
		}
		aluno.add(linkTo(methodOn(AlunoResource.class).getAll()).withRel("Listar Alunos"));
		return ResponseEntity.ok().body(aluno);
	}
	
	@PostMapping
	public ResponseEntity<Aluno> create(@RequestBody Aluno aluno){
		
		var newaluno = alunoService.insert(aluno);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newaluno.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Aluno> update(@RequestBody Aluno aluno, @PathVariable Long id) throws ObjectNotFoundException{
		
		aluno.setId(id);
		
		try {
			aluno = alunoService.update(aluno);
		} catch (NotFoundException e) {
			
			return ResponseEntity.notFound().build();
	
		}
		
		return ResponseEntity.noContent().build();
		
	}
	
	

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Aluno> delete(@PathVariable Long id) throws ObjectNotFoundException{
		
		
		
		try {
			alunoService.delete(id);
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
	
		}
		
		return ResponseEntity.noContent().build();
		
	}
	
	

}
