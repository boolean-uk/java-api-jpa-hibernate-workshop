package com.booleanuk.api.extension.controller;

import com.booleanuk.api.core.model.Teacher;
import com.booleanuk.api.core.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController("extension/teachers")
@RequestMapping("extension/teachers")
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        List<Teacher> teachers = this.teacherRepository.findAll();

        if (teachers.size() == 0)
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No teachers matching that id were found"
            );

        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) {
        return new ResponseEntity<>(this.teacherRepository.save(teacher), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getEmployeeById(@PathVariable int id) {
        return new ResponseEntity<>(
                this.teacherRepository.findById(id).orElseThrow(()->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "No teachers matching that id were found"
                        )
                ),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateEmployee(@PathVariable int id, @RequestBody Teacher teacher) {
        Teacher teacherToUpdate = this.teacherRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No teachers matching that id were found"
                )
        );

        teacherToUpdate.setName(teacher.getName());
        teacherToUpdate.setLocation(teacher.getLocation());
        teacherToUpdate.setSubject(teacher.getSubject());
        teacherToUpdate.setEmail(teacher.getEmail());

        return new ResponseEntity<>(this.teacherRepository.save(teacherToUpdate), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Teacher> deleteEmployee(@PathVariable int id) {
        Teacher deletedTeacher = this.teacherRepository.findById(id).orElseThrow(()->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No teachers matching that id were found"
                )
        );

        this.teacherRepository.deleteById(id);

        return new ResponseEntity<>(deletedTeacher, HttpStatus.OK);
    }
}
