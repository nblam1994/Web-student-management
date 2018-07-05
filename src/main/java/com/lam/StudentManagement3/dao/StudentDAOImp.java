package com.lam.StudentManagement3.dao;

import com.lam.StudentManagement3.student.Student;
import com.lam.StudentManagement3.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;


@Component(value = "StudentJPA")
public class StudentDAOImp implements StudentDAO {

    @Autowired
    private StudentRepository repo;

    @Override
    public Student getStudent(String id) {


        try {

            Optional optional = repo.findById(id);

            if (optional.isPresent()) {
                return (Student) optional.get();
            }
            else {
                return null;
            }

        }
        catch (EntityNotFoundException e) {

            return null;
        }
    }

    @Override
    public List<Student> listStudent() {

        return repo.findAll();
    }

    @Override
    public boolean removeStudent(String id) {

        try {
            repo.deleteById(id);
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean editStudent(Student student) {

        Student std = getStudent(student.getStudentId());



        if(std != null) {

            try {
                repo.save(student);
            }
            catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return  true;
        }

        return false;
    }

    @Override
    public boolean addStudent(Student student) {


        Student std = getStudent(student.getStudentId());

        System.out.print(std);

        if(std == null) {

            try {
                repo.save(student);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return  true;
        }

        return false;
    }
}
