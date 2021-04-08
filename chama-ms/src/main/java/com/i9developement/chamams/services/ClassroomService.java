package com.i9developement.chamams.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.i9developement.chamams.data.vo.ClassroomVo;
import com.i9developement.chamams.entity.Aluno;
import com.i9developement.chamams.entity.Chamada;
import com.i9developement.chamams.entity.Classroom;
import com.i9developement.chamams.entity.Turma;
import com.i9developement.chamams.exception.ResourceNotFoundException;
import com.i9developement.chamams.repository.ClassroomRepository;


@Service
public class ClassroomService {

	@Autowired
	private final ClassroomRepository repository;
	// TODO:Rabbitmq
	// private final ClassroomSendMessage classroomSendMessage;

	@Value("${aluno-ms.host}")
	private String alunoHost;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	public ClassroomService(ClassroomRepository repository) {
		super();
		
		this.repository = repository;
	}

	public ClassroomVo create(ClassroomVo classroomVo) {
				
		ClassroomVo classroomVor = ClassroomVo.create(repository.save(Classroom.create(classroomVo)));
		return classroomVor;
	}

	public List<ClassroomVo> chamada(Chamada chamada) {
		
		Map<String, String> uriVariables = new HashMap<>();
		Long id = chamada.getTurmaId();
		uriVariables.put("id", ""+id);
		
		
		Turma turma = restTemplate.getForObject(alunoHost + "/turmas/{id}", Turma.class , uriVariables);
		List<ClassroomVo> LisclassroomVor = new ArrayList<>();
		
		for (Aluno aluno : turma.getAlunos()) {

			ClassroomVo obj =	new ClassroomVo(null, aluno.getNome(),aluno.getMatricula(), aluno.getId(),aluno.getTurma().getCodigoTurma(), aluno.getTurma().getNomeTurma(),chamada.getData(),chamada.getPresence());
			ClassroomVo classroomVor = ClassroomVo.create(repository.save(Classroom.create(obj)));
			LisclassroomVor.add(classroomVor);
		}
		
		
		return LisclassroomVor;
	}

	public Page<ClassroomVo> findAll(Pageable pageable) {
		var page = repository.findAll(pageable);

		return page.map(this::convertToClassroomVo);
	}

	private ClassroomVo convertToClassroomVo(Classroom classroom) {

		return ClassroomVo.create(classroom);
	}

	public ClassroomVo findById(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

		return ClassroomVo.create(entity);
	}

	public ClassroomVo update(ClassroomVo classroomVo) {
		final Optional<Classroom> opclassroom = repository.findById(classroomVo.getId());
		if (!opclassroom.isPresent()) {
			throw new ResourceNotFoundException("No records found for this ID");
		}

		return ClassroomVo.create(repository.save(Classroom.create(classroomVo)));
	}

	public void detete(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

		repository.delete(entity);
	}

	/*
	 * public List<Classroom> attendence(String classcode, Date data, Presence
	 * precence) {
	 * 
	 * //SchoolClass classobj =
	 * classFeignClient.findByClasscode(classcode).getBody();
	 * 
	 * List<Classroom> classroomVos = new ArrayList<Classroom>(); for (Student
	 * student : classobj.getStudents()) {
	 * 
	 * Classroom classroomVo = new
	 * Classroom(student.getId(),student.getName(),student.getMatricula(),
	 * classobj.getClassname(),classobj.getClasscode(),data, precence);
	 * classroomVos.add(classroomVo); } repository.saveAll(classroomVos); return
	 * classroomVos;
	 * 
	 * }
	 */

}
