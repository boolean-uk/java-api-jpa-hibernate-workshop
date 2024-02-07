package com.booleanuk.api.teachers;

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
    public List<Teacher> getAllTeachers() {
        return this.teacherRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getOneTeacher(@PathVariable int id) {
        return new ResponseEntity<>(this.teacherRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)),
                HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<Teacher> addOneTeacher(@RequestBody Teacher teacher) {
        try {
            return new ResponseEntity<>(this.teacherRepository.save(teacher), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateOneTeacher(@PathVariable int id, @RequestBody Teacher teacher) {
        Teacher teacherToUpdate = this.teacherRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (teacher.getEmail() == null || teacher.getLocation() == null
                || teacher.getName() == null || teacher.getSubject() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        teacherToUpdate.setName(teacher.getName());
        teacherToUpdate.setLocation(teacher.getLocation());
        teacherToUpdate.setSubject(teacher.getSubject());
        teacherToUpdate.setEmail(teacher.getEmail());

        return new ResponseEntity<>(this.teacherRepository.save(teacherToUpdate), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Teacher> deleteOneTeacher(@PathVariable int id){
        Teacher teacherToDelete = this.teacherRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        this.teacherRepository.delete(teacherToDelete);
        return new ResponseEntity<>(teacherToDelete, HttpStatus.ACCEPTED);
    }
}
