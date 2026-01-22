package com.example.schoolmanager.service;

import com.example.schoolmanager.model.Student;
import com.example.schoolmanager.respository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<Student> findAll() {
        return repository.findAll();
    }

    public Student getById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sinh viên với ID = " + id));
    }

    public Student save(Student student) {
        if (student.getId() == null) {
            throw new RuntimeException("ID không được để trống (vì bảng SQL không auto ID)");
        }
        if (repository.existsById(student.getId())) {
            throw new RuntimeException("ID đã tồn tại");
        }
        return repository.save(student);
    }

    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy sinh viên để xóa");
        }
        repository.deleteById(id);
    }

    public Student update(Integer id, Student student) {
        Student existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sinh viên để cập nhật"));

        existing.setName(student.getName());
        existing.setEmail(student.getEmail());
        // ✅ Giữ ID theo path (không đổi khóa chính)
        return repository.save(existing);
    }

    public List<Student> search(String name) {
        if (name == null || name.trim().isEmpty()) {
            return repository.findAll();
        }
        return repository.findByNameContainingIgnoreCase(name);
    }
}
