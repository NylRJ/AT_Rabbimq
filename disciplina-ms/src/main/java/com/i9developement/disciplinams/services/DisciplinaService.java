package com.i9developement.disciplinams.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i9developement.disciplinams.entities.Disciplina;
import com.i9developement.disciplinams.message.DisciplinaSendMessage;
import com.i9developement.disciplinams.repositories.DisciplinaRepository;

import javassist.NotFoundException;

@Service
public class DisciplinaService {

	
	private DisciplinaRepository disciplinaRepository;
	
	private DisciplinaSendMessage disciplinaSendMessage;
	
	
	@Autowired
	public DisciplinaService(DisciplinaRepository disciplinaRepository, DisciplinaSendMessage disciplinaSendMessage) {
	
		this.disciplinaRepository = disciplinaRepository;
		this.disciplinaSendMessage = disciplinaSendMessage;
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
		disciplinaSendMessage.sendMessage(newDisciplina);
		return newDisciplina;
	}

	public Disciplina update(Disciplina disciplina) throws NotFoundException {
		if (findById(disciplina.getId()) == null) {

			throw new NotFoundException("Aluno Não encontrado");
		}

		return disciplinaRepository.save(disciplina);
	}

	public void delete(Long id) throws NotFoundException {
		if (findById(id) == null) {

			throw new NotFoundException("Aluno Não encontrado");
		}

		disciplinaRepository.deleteById(id);
		
	}
	
	public void cadastrarAlunoDisciplina() {
		
	}

}
