package com.booleanuk.api.controller;

import com.booleanuk.api.model.Teacher;
import com.booleanuk.api.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Teacher> getAllTeachers() {
        return this.teacherRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable int id) {
        Teacher teacher = null;
        teacher = this.teacherRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "No teachers matching that id were found"));
        return ResponseEntity.ok(teacher);
    }

    @PostMapping
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) {
        if (teacher.getName() == null || teacher.getLocation() == null
                || teacher.getSubject() == null || teacher.getEmail() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST
                    , "Could not create the teacher, please check all required fields");
        }

        return new ResponseEntity<Teacher>(this.teacherRepository.save(teacher), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacherById(@PathVariable int id, @RequestBody Teacher teacher) {
        if (teacher.getName() == null || teacher.getLocation() == null
                || teacher.getSubject() == null || teacher.getEmail() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST
                    , "Could not update the teacher's details, please check all required fields");
        }

        Teacher teacherToUpdate = this.teacherRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "No teachers matching that id were found"));

        teacherToUpdate.setName(teacher.getName());
        teacherToUpdate.setLocation(teacher.getLocation());
        teacherToUpdate.setSubject(teacher.getSubject());
        teacherToUpdate.setEmail(teacher.getEmail());
        return new ResponseEntity<Teacher>(this.teacherRepository.save(teacherToUpdate), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Teacher> deleteTeacherById(@PathVariable int id) {
        Teacher teacherToDelete = this.teacherRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "No teachers matching that id were found"));
        this.teacherRepository.delete(teacherToDelete);
        return ResponseEntity.ok(teacherToDelete);
    }

}
