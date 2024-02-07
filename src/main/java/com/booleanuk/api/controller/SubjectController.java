package com.booleanuk.api.controller;

import com.booleanuk.api.model.Subject;
import com.booleanuk.api.model.Teacher;
import com.booleanuk.api.repository.SubjectRepository;
import com.booleanuk.api.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping
    public List<Subject> getAllSubjects() {
        return this.subjectRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable int id) {
        Subject subject = null;
        subject = this.subjectRepository.findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No subjects matching that id were found"));
        return ResponseEntity.ok(subject);
    }

    @PostMapping
    public ResponseEntity<Subject> createSubject(@RequestBody Subject subject) {
        if (subject.getTitle() == null || subject.getLevel() == null ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST
                    , "Could not create the subject, please check all required fields");
        }

        return new ResponseEntity<Subject>(this.subjectRepository.save(subject), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subject> updateSubjectById(@PathVariable int id, @RequestBody Subject subject) {
        if (subject.getTitle() == null || subject.getLevel() == null ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST
                    , "Could not update the subjects's details, please check all required fields");
        }

        Subject subjectToUpdate = this.subjectRepository.findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No subjects matching that id were found"));

        subjectToUpdate.setTitle(subject.getTitle());
        subjectToUpdate.setLevel(subject.getLevel());
        return new ResponseEntity<Subject>(this.subjectRepository.save(subjectToUpdate), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Subject> deleteSubjectbyId(@PathVariable int id) {
        Subject subjectToDelete = this.subjectRepository.findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No subjects matching that id were found"));
        this.subjectRepository.delete(subjectToDelete);
        return ResponseEntity.ok(subjectToDelete);
    }
}
