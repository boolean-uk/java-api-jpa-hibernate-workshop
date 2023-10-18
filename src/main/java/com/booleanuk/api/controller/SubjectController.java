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
        List<Subject> allSubjects = subjectRepository.findAll();
        if (allSubjects.size() == 0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No subjects matching that id were found.");
        }
        return allSubjects;
    }

    @PostMapping
    public ResponseEntity<Subject> createSubject(@RequestBody Subject subject) {
        try {
            return new ResponseEntity<>(this.subjectRepository.save(subject), HttpStatus.CREATED);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not create the new subject, please check all required fields are correct.");
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable int id) {
        Subject subject = null;
        subject = this.subjectRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No subjects matching that id were found"));
        return ResponseEntity.ok(subject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subject> updateSubject(@PathVariable int id, @RequestBody Subject subject) {
        Subject subjectToUpdate = this.subjectRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No subjects matching that id were found"));

        subjectToUpdate.setTitle(subject.getTitle());
        subjectToUpdate.setLevel(subject.getLevel());
        try {
            return new ResponseEntity<>(this.subjectRepository.save(subjectToUpdate), HttpStatus.CREATED);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not update the subject's details, please check all required fields are correct.");
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Subject> deleteSubject(@PathVariable int id) {
        Subject subjectToDelete = this.subjectRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No subjects matching that id were found"));
        this.subjectRepository.delete(subjectToDelete);
        return ResponseEntity.ok(subjectToDelete);
    }
}
