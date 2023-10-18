package com.booleanuk.api.extension.repository;

import com.booleanuk.api.extension.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {

}
