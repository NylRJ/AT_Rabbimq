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

import com.i9developement.alunoms.entities.Disciplina;
import com.i9developement.alunoms.services.DisciplinaService;

import javassist.NotFoundException;
import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/disciplinas")
public class DisciplinaResource {
	
	@Autowired
	private DisciplinaService DisciplinaService;
	
	@GetMapping
	public ResponseEntity<List<Disciplina>> getAll(){
		
		List<Disciplina> disciplinas = DisciplinaService.findAll();
		List<Disciplina> listdisciplinas = new ArrayList<>();

		for (Disciplina disciplina : disciplinas) {
			Long codigo = disciplina.getId();
			disciplina.add(linkTo(methodOn(DisciplinaResource.class).findById(codigo)).withSelfRel());
			listdisciplinas.add(disciplina);
		}
		return ResponseEntity.ok(listdisciplinas);
		
	}
	@GetMapping(value = "/{id}")
	public ResponseEntity<Disciplina> findById(@PathVariable Long id){
		
		Disciplina disciplina = DisciplinaService.findById(id);
		if(disciplina == null) {
			return ResponseEntity.notFound().build();
		}
		disciplina.add(linkTo(methodOn(DisciplinaResource.class).getAll()).withRel("Listar Disciplinas"));
		return ResponseEntity.ok().body(disciplina);
	}
	
	@PostMapping
	public ResponseEntity<Disciplina> create(@RequestBody Disciplina disciplina){
		
		var newdisciplina = DisciplinaService.insert(disciplina);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newdisciplina.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Disciplina> update(@RequestBody Disciplina disciplina, @PathVariable Long id) throws ObjectNotFoundException{
		
		disciplina.setId(id);
		
		try {
			disciplina = DisciplinaService.update(disciplina);
		} catch (NotFoundException e) {
			
			return ResponseEntity.notFound().build();
	
		}
		
		return ResponseEntity.noContent().build();
		
	}
	
	

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Disciplina> delete(@PathVariable Long id) throws ObjectNotFoundException{
		
		
		
		try {
			DisciplinaService.delete(id);
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
	
		}
		
		return ResponseEntity.noContent().build();
		
	}
	
	

}
