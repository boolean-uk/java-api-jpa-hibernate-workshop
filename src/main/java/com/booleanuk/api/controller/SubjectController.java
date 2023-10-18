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
    private SubjectRepository repo;
    @GetMapping
    public List<Subject> getAll(){
        return this.repo.findAll();
    }
    @PostMapping
    public ResponseEntity<Subject> createSubject(@RequestBody Subject subject){
        if(subject.getLevel() == null || subject.getLevel().isEmpty() || subject.getTitle() == null || subject.getTitle().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Could not create the subject, please check all required fields are correct.");
        }
        return new ResponseEntity<Subject>(this.repo.save(subject), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubject(@PathVariable int id){
        Subject subject = null;
        subject = this.repo.findById(id).orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND, "Not subject matching that id were found"));
        return ResponseEntity.ok(subject);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Subject> updateSubject(@PathVariable int id, @RequestBody Subject subject){
        Subject temp = this.repo.findById(id).orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND, "Not subject matching that id was found"));
        if(subject.getLevel() == null || subject.getLevel().isEmpty() || subject.getTitle() == null || subject.getTitle().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Could not update the subject, please check all required fields are correct.");
        }
        temp.setLevel(subject.getLevel());
        temp.setTitle(subject.getTitle());
        return new ResponseEntity<>(this.repo.save(temp),HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Subject> deleteSubject(@PathVariable int id){
        Subject temp = this.repo.findById(id).orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND, "Not subject matching that id was found"));
        this.repo.delete(temp);
        return ResponseEntity.ok(temp);
    }

}
