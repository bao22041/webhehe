package com.example.schoolmanager.controller;

import com.example.schoolmanager.model.Student;
import com.example.schoolmanager.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Student> getAll() {
        return service.findAll();
    }

    @PostMapping
    public Student add(@RequestBody Student student) {
        return service.save(student);
    }

    // ✅ XÓA: /api/students/1
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    // ✅ CẬP NHẬT: /api/students/1
    @PutMapping("/{id}")
    public Student update(@PathVariable Integer id, @RequestBody Student student) {
        return service.update(id, student);
    }

    @GetMapping("/search")
    public List<Student> search(@RequestParam String name) {
        return service.search(name);
    }
}
