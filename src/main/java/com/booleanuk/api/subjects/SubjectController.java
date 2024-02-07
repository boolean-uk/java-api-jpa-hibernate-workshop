package com.booleanuk.api.subjects;

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
    public ResponseEntity<Subject> getOneSubject(@PathVariable int id) {
        return new ResponseEntity<>(this.subjectRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)),
                HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<Subject> addOneSubject(@RequestBody Subject subject) {
        try {
            return new ResponseEntity<>(this.subjectRepository.save(subject), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subject> updateOneSubject(@PathVariable int id, @RequestBody Subject subject) {
        Subject subjectToUpdate = this.subjectRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (subject.getTitle() == null ||subject.getLevel() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        subjectToUpdate.setTitle(subject.getTitle());
        subjectToUpdate.setLevel(subject.getLevel());

        return new ResponseEntity<>(this.subjectRepository.save(subjectToUpdate), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Subject> deleteOneSubject(@PathVariable int id){
        Subject subjectToDelete = this.subjectRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        this.subjectRepository.delete(subjectToDelete);
        return new ResponseEntity<>(subjectToDelete, HttpStatus.ACCEPTED);
    }
}