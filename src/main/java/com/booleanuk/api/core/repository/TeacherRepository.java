package com.booleanuk.api.core.repository;

import com.booleanuk.api.core.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

}
