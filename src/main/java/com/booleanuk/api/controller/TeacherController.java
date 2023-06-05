package com.booleanuk.api.controller;

import com.booleanuk.api.model.Teacher;
import com.booleanuk.api.repository.TeacherRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("teachers")
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        return new ResponseEntity<>(this.teacherRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) {
        return new ResponseEntity<>(this.teacherRepository.save(teacher), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getEmployeeById(@PathVariable int id) {
        return new ResponseEntity<>(this.teacherRepository.findById(id).orElse(null), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateEmployee(@PathVariable int id, @RequestBody Teacher teacher) {
        Optional<Teacher> requestedTeacher = this.teacherRepository.findById(id);

        if (requestedTeacher.isEmpty()) return null;

        Teacher teacherToUpdate = requestedTeacher.get();
        teacher.update(teacherToUpdate);

        return new ResponseEntity<>(this.teacherRepository.save(teacherToUpdate), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Teacher> deleteEmployee(@PathVariable int id) {
        Optional<Teacher> teacherToDelete = this.teacherRepository.findById(id);
        this.teacherRepository.deleteById(id);
        return new ResponseEntity<>(teacherToDelete.orElse(null), HttpStatus.OK);
    }
}
