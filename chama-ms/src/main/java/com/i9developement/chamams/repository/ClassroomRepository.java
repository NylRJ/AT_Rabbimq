package com.i9developement.chamams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.i9developement.chamams.entity.Classroom;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long>  {

}
