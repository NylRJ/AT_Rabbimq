package com.i9developement.alunoms.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i9developement.alunoms.entities.Aluno;
import com.i9developement.alunoms.entities.Turma;
import com.i9developement.alunoms.repositories.AlunoRepository;
import com.i9developement.alunoms.repositories.TurmaRepository;

import javassist.NotFoundException;

@Service
public class TurmaService {

	@Autowired
	private TurmaRepository turmaRepository;
	
	@Autowired
	private AlunoRepository alunoRepository;

	public TurmaService() {

	}

	public List<Turma> findAll() {

		return turmaRepository.findAll();
	}

	public Turma findById(Long id) {

		Optional<Turma> obj = turmaRepository.findById(id);

		return obj.orElse(null);
	}

	public Turma insert(Turma turma) {

		turma.setId(null);
		Turma newTurma = turmaRepository.save(turma);

		return newTurma;
	}

	public Turma update(Turma turma) throws NotFoundException {
		if (findById(turma.getId()) == null) {

			throw new NotFoundException("Turma Não encontrado");
		}

		return turmaRepository.save(turma);
	}

	public void delete(Long id) throws NotFoundException {
		if (findById(id) == null) {

			throw new NotFoundException("Turma Não encontrado");
		}

		turmaRepository.deleteById(id);
		
	}
	
	public Turma addAlunoTurma(Long alunoId, Long turmaId) {
		
		Aluno aluno = alunoRepository.findById(alunoId).get();

		Turma obj = turmaRepository.findById(turmaId).get();
		
		obj.getAlunos().add(aluno);
		aluno.setTurma(obj);
		turmaRepository.save(obj);

		return obj;
	}

}
