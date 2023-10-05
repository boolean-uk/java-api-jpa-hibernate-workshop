package com.booleanuk.api.Controller;

import com.booleanuk.api.model.Teacher;
import com.booleanuk.api.repository.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("teachers")
public class TeacherController {

    private final TeacherRepo teacherRepo;

    public TeacherController(TeacherRepo teacherRepo) {
        this.teacherRepo = teacherRepo;
    }

    @GetMapping
    public List<Teacher> getAllTeachers() {
        return this.teacherRepo.findAll();
    }

    @PostMapping
    public ResponseEntity<?> createTeacher(@RequestBody Teacher teacher) {
        try {
            return new ResponseEntity<>(this.teacherRepo.save(teacher), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("bad request", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTeacherById(@PathVariable int id) {
        try {
            Teacher teacher;
            teacher = this.teacherRepo.findById(id).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "not found"));
            return ResponseEntity.ok(teacher);
        } catch (Exception e) {
            return new ResponseEntity<>("bad request", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTeacher(@PathVariable int id, @RequestBody Teacher teacher) {
        try {
            Teacher teacherToUpdate = this.teacherRepo.findById(id).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "not found"));
            teacherToUpdate.setName(teacher.getName());
            teacherToUpdate.setLocation(teacher.getLocation());
            teacherToUpdate.setSubject(teacher.getSubject());
            teacherToUpdate.setEmail(teacher.getEmail());
            return new ResponseEntity<>(this.teacherRepo.save(teacherToUpdate), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("bad request", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable int id) {
        try {
            Teacher teacherToDelete = this.teacherRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
            this.teacherRepo.delete(teacherToDelete);
            return ResponseEntity.ok(teacherToDelete);
        } catch (Exception e) {
            return new ResponseEntity<>("bad request", HttpStatus.BAD_REQUEST);
        }
    }
}
