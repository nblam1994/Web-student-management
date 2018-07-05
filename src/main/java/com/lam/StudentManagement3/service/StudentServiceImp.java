package com.lam.StudentManagement3.service;

import com.lam.StudentManagement3.dao.StudentDAO;
import com.lam.StudentManagement3.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component(value = "StudentService")
public class StudentServiceImp implements StudentService {

    @Autowired
    private StudentDAO studentDAO;

    @Override
    public Student getStudent(String id) {
        return studentDAO.getStudent(id);
    }

    @Override
    public List<Student> listStudent() {
        return studentDAO.listStudent();
    }

    @Override
    public boolean removeStudent(String id) {
        return studentDAO.removeStudent(id);
    }

    @Override
    public boolean editStudent(Student student) {
        return studentDAO.editStudent(student);
    }

    @Override
    public boolean addStudent(Student student) {
        return studentDAO.addStudent(student);
    }
}
