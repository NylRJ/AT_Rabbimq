package com.i9developement.alunoms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.i9developement.alunoms.entities.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

	

}
