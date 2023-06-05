package com.booleanuk.api.controller;

import com.booleanuk.api.model.Teacher;
import com.booleanuk.api.repository.TeacherRepository;
import com.booleanuk.api.validation.NullChecker;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@RestController
@RequestMapping("teachers")
public class TeacherController {
    @Autowired
    TeacherRepository teacherRepository;
    @PostMapping
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) {
        if (NullChecker.checkIfNullExists(teacher)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Could not create the new teacher,please check all required fields are correct.");
        }
        return new ResponseEntity<Teacher>(teacherRepository.save(teacher), HttpStatus.CREATED);
    }
    @GetMapping
    public List<Teacher> getAllTeachers() {
        List<Teacher> allTeachers = teacherRepository.findAll();
        if (allTeachers.size()==0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No teachers matching that id were found.");
        }
        return allTeachers;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Integer id) {
        Teacher teacher = null;
        teacher = teacherRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"No teachers matching that id were found."));
        return ResponseEntity.ok(teacher);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable int id, @RequestBody Teacher teacher) {
        Teacher teacherToUpdate = this.teacherRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No teachers matching that id were found."));
        if (NullChecker.checkIfNullExists(teacher)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Could not update the teacher's details,please check all required fields are correct.");
        }
        teacherToUpdate.setName(teacher.getName());
        teacherToUpdate.setLocation(teacher.getLocation());
        teacherToUpdate.setSubject(teacher.getSubject());
        teacherToUpdate.setEmail(teacher.getEmail());
        return new ResponseEntity<Teacher>(this.teacherRepository.save(teacherToUpdate), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Teacher> deleteTeacher(@PathVariable int id) {
        Teacher teacherToDelete = this.teacherRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"No teachers matching that id were found."));
        this.teacherRepository.delete(teacherToDelete);
        return ResponseEntity.ok(teacherToDelete);
    }
}
