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

import com.i9developement.alunoms.entities.Turma;
import com.i9developement.alunoms.services.TurmaService;

import javassist.NotFoundException;
import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/turmas")
public class TurmaResource {

	@Autowired
	private TurmaService turmaService;

	@GetMapping
	public ResponseEntity<List<Turma>> getAll() {

		List<Turma> turmas = turmaService.findAll();
		List<Turma> listurmas = new ArrayList<>();

		for (Turma turma : turmas) {
			Long codigo = turma.getId();
			turma.add(linkTo(methodOn(DisciplinaResource.class).findById(codigo)).withSelfRel());
			listurmas.add(turma);
		}
		return ResponseEntity.ok(listurmas);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Turma> findById(@PathVariable Long id) {

		Turma turma = turmaService.findById(id);
		if (turma == null) {
			return ResponseEntity.notFound().build();
		}
		turma.add(linkTo(methodOn(TurmaResource.class).getAll()).withRel("Listar Turma"));
		return ResponseEntity.ok().body(turma);
	}

	@PostMapping
	public ResponseEntity<Turma> create(@RequestBody Turma turma) {

		var newturma = turmaService.insert(turma);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newturma.getId())
				.toUri();
		return ResponseEntity.created(uri).build();

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Turma> update(@RequestBody Turma turma, @PathVariable Long id)
			throws ObjectNotFoundException {

		turma.setId(id);

		try {
			turma = turmaService.update(turma);
		} catch (NotFoundException e) {

			return ResponseEntity.notFound().build();

		}

		return ResponseEntity.noContent().build();

	}
	
	

	@PutMapping(value = "/{turmaId}/addaluno/{alunoId}")
	public ResponseEntity<Turma> addAlunoTurma(@PathVariable Long turmaId, @PathVariable Long alunoId)
			throws ObjectNotFoundException {

		
		Turma obj =	turmaService.addAlunoTurma(alunoId, turmaId);
		
		return ResponseEntity.ok(obj);

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Turma> delete(@PathVariable Long id) throws ObjectNotFoundException {

		try {
			turmaService.delete(id);
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();

		}

		return ResponseEntity.noContent().build();

	}

}
