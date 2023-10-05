package com.booleanuk.api.Controller;

import com.booleanuk.api.model.Subject;
import com.booleanuk.api.repository.SubjectRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("subjects")
public class SubjectController {


    private final SubjectRepo subjectRepo;

    public SubjectController(SubjectRepo subjectRepo) {
        this.subjectRepo = subjectRepo;
    }

    @GetMapping
    public List<Subject> getAllSubjects() {
        return this.subjectRepo.findAll();
    }


    @PostMapping
    public ResponseEntity<?> createSubject(@RequestBody Subject subject) {
        try {
            return new ResponseEntity<>(this.subjectRepo.save(subject), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("bad request", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSubjectById(@PathVariable int id) {
        try {
            Subject subject;
            subject = this.subjectRepo.findById(id).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "not found"));
            return ResponseEntity.ok(subject);
        } catch (Exception e) {
            return new ResponseEntity<>("bad request", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSubject(@PathVariable int id, @RequestBody Subject subject) {
        try {
            Subject subjectToUpdate = this.subjectRepo.findById(id).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "not found"));
            subjectToUpdate.setTitle(subject.getTitle());
            subjectToUpdate.setLevel(subject.getLevel());
            return new ResponseEntity<>(this.subjectRepo.save(subjectToUpdate), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("bad request", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSubject(@PathVariable int id) {
        try {
            Subject subjectToDelete = this.subjectRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
            this.subjectRepo.delete(subjectToDelete);
            return ResponseEntity.ok(subjectToDelete);
        } catch (Exception e) {
            return new ResponseEntity<>("bad request", HttpStatus.BAD_REQUEST);
        }
    }
}
