package com.example.schoolmanager.service;

import com.example.schoolmanager.model.Student;
import com.example.schoolmanager.respository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repository;
    public Student update(Integer id, Student student) {
    Student existing = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy sinh viên"));

    existing.setName(student.getName());
    existing.setEmail(student.getEmail());

    return repository.save(existing);
}

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<Student> findAll() {
        return repository.findAll();
    }

    public Student save(Student student) {
        if (repository.existsById(student.getId())) {
            throw new RuntimeException("ID đã tồn tại");
        }
        return repository.save(student);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public List<Student> search(String name) {
        if (name == null || name.trim().isEmpty()) {
            return repository.findAll();
        }
        return repository.findByNameContainingIgnoreCase(name);
    }
}
