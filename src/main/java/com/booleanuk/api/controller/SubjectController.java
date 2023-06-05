package com.booleanuk.api.controller;

import com.booleanuk.api.model.Subject;
import com.booleanuk.api.repository.SubjectRepository;
import com.booleanuk.api.validation.NullChecker;
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
    SubjectRepository subjectRepository;

    @PostMapping
    public ResponseEntity<Subject> createSubject(@RequestBody Subject subject) {
        if (NullChecker.checkIfNullExists(subject)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Could not create the subject,please check all required fields are correct.");
        }
        return new ResponseEntity<Subject>(subjectRepository.save(subject), HttpStatus.CREATED);
    }
    @GetMapping
    public List<Subject> getAllSubjects() {
        List<Subject> allSubjects = subjectRepository.findAll();
        if (allSubjects.size()==0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No subjects matching that id were found.");
        }
        return allSubjects;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable Integer id) {
        Subject subject = null;
        subject = subjectRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"No subjects matching that id were found."));
        return ResponseEntity.ok(subject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subject> updateSubject(@PathVariable int id, @RequestBody Subject subject) {
        Subject subjectToUpdate = this.subjectRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No subjects matching that id were found."));
        if (NullChecker.checkIfNullExists(subject)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Could not update the subject's details,please check all required fields are correct.");
        }
        subjectToUpdate.setTitle(subject.getTitle());
        subjectToUpdate.setLevel(subject.getLevel());
        return new ResponseEntity<Subject>(this.subjectRepository.save(subjectToUpdate), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Subject> deleteSubject(@PathVariable int id) {
        Subject subjectToDelete = this.subjectRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"No subjects matching that id were found."));
        this.subjectRepository.delete(subjectToDelete);
        return ResponseEntity.ok(subjectToDelete);
    }
}
