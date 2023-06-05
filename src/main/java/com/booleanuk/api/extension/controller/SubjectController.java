package com.booleanuk.api.extension.controller;

import com.booleanuk.api.extension.model.Subject;
import com.booleanuk.api.extension.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("extension/subjects")
public class SubjectController {

    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping
    public ResponseEntity<List<Subject>> getAllTeachers() {
        List<Subject> teachers = this.subjectRepository.findAll();

        if (teachers.size() == 0)
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No subjects matching that id were found"
            );

        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Subject> createTeacher(@RequestBody Subject subject) {
        return new ResponseEntity<>(this.subjectRepository.save(subject), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> getEmployeeById(@PathVariable int id) {
        return new ResponseEntity<>(
                this.subjectRepository.findById(id).orElseThrow(()->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "No subjects matching that id were found"
                        )
                ),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subject> updateEmployee(@PathVariable int id, @RequestBody Subject subject) {
        Subject subjectToUpdate = this.subjectRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No subjects matching that id were found"
                )
        );

        subjectToUpdate.setLevel(subject.getLevel());
        subjectToUpdate.setTitle(subject.getTitle());

        return new ResponseEntity<>(this.subjectRepository.save(subjectToUpdate), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Subject> deleteEmployee(@PathVariable int id) {
        Subject deletedTeacher = this.subjectRepository.findById(id).orElseThrow(()->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No subjects matching that id were found"
                )
        );

        this.subjectRepository.deleteById(id);

        return new ResponseEntity<>(deletedTeacher, HttpStatus.OK);
    }
}
