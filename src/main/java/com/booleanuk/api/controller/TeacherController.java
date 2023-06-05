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
@RequestMapping("teachers")
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    // ------------ ENDPOINTS ------------ //

    //region // POST //
    @PostMapping
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) {
        try {
            return new ResponseEntity<Teacher>(this.teacherRepository.save(teacher), HttpStatus.CREATED);
        }
        catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Could not create teacher, please check all required fields are correct."
            );
        }
    }
    //endregion

    //region // GET //
    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        List<Teacher> teachers = this.teacherRepository.findAll();
        if(teachers.isEmpty()) throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "No teachers matching that id were found."
        );
        return ResponseEntity.ok(teachers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable int id) {
        Teacher teacher = null;
        teacher = this.teacherRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "No teachers matching that id were found."
        ));
        return ResponseEntity.ok(teacher);
    }
    //endregion

    //region // PUT //
    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable int id, @RequestBody Teacher teacher) {
        Teacher teacherToUpdate = this.teacherRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "No teachers matching that id were found."
        ));
        teacherToUpdate.setName(teacher.getName());
        teacherToUpdate.setLocation(teacher.getLocation());
        teacherToUpdate.setSubject(teacher.getSubject());
        teacherToUpdate.setEmail(teacher.getEmail());

        try {
            return new ResponseEntity<Teacher>(this.teacherRepository.save(teacherToUpdate), HttpStatus.CREATED);
        }
        catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Could not update the teacher's details, please check all required fields are correct."
            );
        }
    }
    //endregion

    //region // DELETE //
    @DeleteMapping("/{id}")
    public ResponseEntity<Teacher> deleteTeacher(@PathVariable int id) {
        Teacher teacherToDelete = this.teacherRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "No teachers matching that id were found."
        ));
        this.teacherRepository.delete(teacherToDelete);
        return ResponseEntity.ok(teacherToDelete);
    }
    //endregion
}
