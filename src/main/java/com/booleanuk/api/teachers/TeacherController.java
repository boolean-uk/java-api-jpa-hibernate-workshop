package com.booleanuk.api.teachers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("teachers")
public class TeacherController {

    //this is 'kinda' bad because it tightly couples the classes
    @Autowired
    private TeacherRepository teacherRepository;

    @GetMapping
    public ResponseEntity<List<Teacher>> getAllEmployees() {
        return new ResponseEntity<>(this.teacherRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable int id) {
        Teacher teacher = this.teacherRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
        return ResponseEntity.ok(teacher);
    }

    @PostMapping
    public ResponseEntity<Teacher> createEmployee(@RequestBody Teacher teacher) {
        return new ResponseEntity<>(this.teacherRepository.save(teacher), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable int id, @RequestBody Teacher teacher) {
        Teacher teacherToUpdate = this.teacherRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));

        teacherToUpdate.setName(teacher.getName());
        teacherToUpdate.setLocation(teacher.getLocation());
        teacherToUpdate.setSubject(teacher.getSubject());
        teacherToUpdate.setEmail(teacher.getEmail());
        return new ResponseEntity<>(this.teacherRepository.save(teacherToUpdate), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Teacher> deleteTeacher(@PathVariable int id) {
        Teacher teacherToDelete = this.teacherRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));

        this.teacherRepository.delete(teacherToDelete);
        return ResponseEntity.ok(teacherToDelete);
    }


}
