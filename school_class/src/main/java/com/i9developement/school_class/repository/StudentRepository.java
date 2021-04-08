package com.i9developement.school_class.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.i9developement.school_class.entity.Student;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long>  {

}