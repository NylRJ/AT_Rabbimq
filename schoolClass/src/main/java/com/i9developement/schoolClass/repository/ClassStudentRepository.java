package com.i9developement.schoolClass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.i9developement.schoolClass.entity.ClassStudent;

@Repository
public interface ClassStudentRepository extends JpaRepository<ClassStudent, Long> {

}
