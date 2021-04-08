package com.i9developement.alunoms.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i9developement.alunoms.entities.Disciplina;
import com.i9developement.alunoms.repositories.DisciplinaRepository;

import javassist.NotFoundException;

@Service
public class DisciplinaService {

	@Autowired
	private DisciplinaRepository disciplinaRepository;

	public DisciplinaService() {

	}

	public List<Disciplina> findAll() {

		return disciplinaRepository.findAll();
	}

	public Disciplina findById(Long id) {

		Optional<Disciplina> obj = disciplinaRepository.findById(id);

		return obj.orElse(null);
	}

	public Disciplina insert(Disciplina disciplina) {

		disciplina.setId(null);
		Disciplina newDisciplina = disciplinaRepository.save(disciplina);

		return newDisciplina;
	}

	public Disciplina update(Disciplina disciplina) throws NotFoundException {
		if (findById(disciplina.getId()) == null) {

			throw new NotFoundException("Disciplina Não encontrado");
		}

		return disciplinaRepository.save(disciplina);
	}

	public void delete(Long id) throws NotFoundException {
		if (findById(id) == null) {

			throw new NotFoundException("Disciplina Não encontrado");
		}

		disciplinaRepository.deleteById(id);
		
	}

}
