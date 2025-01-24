package com.booleanuk.api.teachers;

import org.springframework.data.jpa.repository.JpaRepository;

//Integer because the PK is an int
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {}