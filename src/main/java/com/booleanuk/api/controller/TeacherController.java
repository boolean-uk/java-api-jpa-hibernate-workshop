package com.booleanuk.api.controller;

import com.booleanuk.api.model.Employee;
import com.booleanuk.api.model.Teacher;
import com.booleanuk.api.repository.EmployeeRepository;
import com.booleanuk.api.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@RestController
@RequestMapping("teachers")
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    @GetMapping
    public List<Teacher> getAllTeachers() {
        return this.teacherRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) {
        if(teacher.getEmail() == null || teacher.getName() == null || teacher.getLocation() == null || teacher.getSubject() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Could not create the new teacher, please check all required fields are correct.");
        }
        if(teacher.getEmail().isEmpty() || teacher.getName().isEmpty() || teacher.getLocation().isEmpty() || teacher.getSubject().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Could not create the new teacher, please check all required fields are correct.");
        }
        return new ResponseEntity<Teacher>(this.teacherRepository.save(teacher), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable long id) {
        Teacher teacher = null;
        teacher = this.teacherRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not teachers matching that is were found"));
        return ResponseEntity.ok(teacher);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable long id, @RequestBody Teacher teacher) {
        Teacher teacherToUpdate = this.teacherRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not teachers matching that is were found"));
        if(teacher.getEmail() == null || teacher.getName() == null || teacher.getLocation() == null || teacher.getSubject() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Could not update the teacher, please check all required fields are correct.");
        }
        if(teacher.getEmail().isEmpty() || teacher.getName().isEmpty() || teacher.getLocation().isEmpty() || teacher.getSubject().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Could not update the teacher, please check all required fields are correct.");
        }
        teacherToUpdate.setName(teacher.getName());
        teacherToUpdate.setEmail(teacher.getEmail());
        teacherToUpdate.setLocation(teacher.getLocation());
        return new ResponseEntity<Teacher>(this.teacherRepository.save(teacherToUpdate), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Teacher> deleteTeacher(@PathVariable long id) {
        Teacher teacherToDelete = this.teacherRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not teachers matching that is were found"));
        this.teacherRepository.delete(teacherToDelete);
        return ResponseEntity.ok(teacherToDelete);
    }
}
