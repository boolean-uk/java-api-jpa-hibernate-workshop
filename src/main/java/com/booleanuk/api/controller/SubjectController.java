package com.booleanuk.api.controller;



import com.booleanuk.api.model.Subject;
import com.booleanuk.api.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("subjects")
public class SubjectController {

    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping
    public List<Subject> getAllSubjects() {
        return this.subjectRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Subject> createSubject(@RequestBody Subject subject) {
        return new ResponseEntity<Subject>(this.subjectRepository.save(subject), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable int id) {
        Subject subject = null;
        subject = this.subjectRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No teachers matching that id were found"));
        return ResponseEntity.ok(subject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subject> updateTeacher(@PathVariable int id, @RequestBody Subject subject) {
        Subject subjectToUpdate = this.subjectRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No teachers matching that id were found"));

        subjectToUpdate.setTitle(subject.getTitle());
        subjectToUpdate.setLevel(subject.getLevel());
        return new ResponseEntity<Subject>(this.subjectRepository.save(subjectToUpdate), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Subject> deleteTeacher(@PathVariable int id) {
        Subject teacherToDelete = this.subjectRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No teachers matching that id were found"));
        this.subjectRepository.delete(teacherToDelete);
        return ResponseEntity.ok(teacherToDelete);
    }
}
