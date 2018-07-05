package com.lam.StudentManagement3.dao;

import com.lam.StudentManagement3.student.Student;

import java.util.List;

public interface StudentDAO {

    public Student getStudent(String id);
    public List<Student> listStudent();
    public boolean removeStudent(String id);
    public boolean editStudent(Student student);
    public boolean addStudent(Student student);
}
