package com.i9developement.alunoms.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i9developement.alunoms.entities.Aluno;
import com.i9developement.alunoms.repositories.AlunoRepository;

import javassist.NotFoundException;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository alunoRepository;

	public AlunoService() {

	}

	public List<Aluno> findAll() {

		return alunoRepository.findAll();
	}

	public Aluno findById(Long id) {

		Optional<Aluno> obj = alunoRepository.findById(id);

		return obj.orElse(null);
	}

	public Aluno insert(Aluno aluno) {

		aluno.setId(null);
		Aluno newAluno = alunoRepository.save(aluno);

		return newAluno;
	}

	public Aluno update(Aluno aluno) throws NotFoundException {
		if (findById(aluno.getId()) == null) {

			throw new NotFoundException("Aluno Não encontrado");
		}

		return alunoRepository.save(aluno);
	}

	public void delete(Long id) throws NotFoundException {
		if (findById(id) == null) {

			throw new NotFoundException("Aluno Não encontrado");
		}

		alunoRepository.deleteById(id);
		
	}

}
