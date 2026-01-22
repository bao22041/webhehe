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

    // Yêu cầu 5: Get All
    @GetMapping
    public List<Student> getAll() {
        return service.findAll();
    }

    // Yêu cầu 4: Get by ID
    @GetMapping("/{id}")
    public Student getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    // Yêu cầu 1: Add Student
    @PostMapping
    public Student add(@RequestBody Student student) {
        return service.save(student);
    }

    // Yêu cầu 2: Delete Student (POST /delete/{id})
    @PostMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    // Yêu cầu 6: Update Student (POST /update/{id})
    @PostMapping("/update/{id}")
    public Student update(@PathVariable Integer id, @RequestBody Student student) {
        return service.update(id, student);
    }

    // Yêu cầu 3: Search
    @GetMapping("/search")
    public List<Student> search(@RequestParam String name) {
        return service.search(name);
    }
}
