package com.lam.StudentManagement3.service;

import com.lam.StudentManagement3.student.Student;

import java.util.List;

public interface StudentService {

    public Student getStudent(String id);
    public List<Student> listStudent();
    public boolean removeStudent(String id);
    public boolean editStudent(Student student);
    public boolean addStudent(Student student);
}
