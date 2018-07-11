package com.lam.StudentManagement3.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.lam.StudentManagement3.service.StudentService;
import com.lam.StudentManagement3.student.Account;
import com.lam.StudentManagement3.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;


@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;


    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
    public String viewStudent(@PathVariable String id, Map<String, Object> model) {

        try {
                Student student = studentService.getStudent(id);
                model.put("student", student);
        }
        catch (Exception e) {

            System.out.println("Exception at student view route");
        }

        return "view-student";
    }



    @RequestMapping(value = "/student-add", method = RequestMethod.GET)
    public String addStudent() {

        return "add-student";
    }

    @RequestMapping(value = "/student-add", method = RequestMethod.POST)
    public String addStudent(HttpServletRequest request, Map<String, Object> model) {

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String className = request.getParameter("class-name");
        float score = Float.parseFloat(request.getParameter("score"));
        Student student = new  Student(id, name, className, score);
        boolean isAdd = studentService.addStudent(student);
        model.put("student", student);
        return "view-student";
    }

    @RequestMapping(value = "/student-list", method = RequestMethod.GET)
    public String listStudent(Model model) {

        Iterable<Student> studentList = studentService.listStudent();
        model.addAttribute("studentList", studentList);
        return "list-student-jstl";
    }

    @RequestMapping(value = "/student-delete/{id}")
    public void removeStudent(@PathVariable String id, HttpServletResponse resp) throws IOException {

        boolean isDelete = studentService.removeStudent(id);

        if(isDelete) {

            resp.getWriter().print("Student with id :" + id + " deleted");
        }
        else {

            resp.getWriter().print("Student does not exist");
        }

    }


    @RequestMapping(value = "/student-edit")
    public String removeStudent(){

        return "edit-student";
    }


    @RequestMapping(value = "/student-edit", method = RequestMethod.POST)
    public void removeStudent(HttpServletRequest request, HttpServletResponse resp) throws IOException{


        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String className = request.getParameter("class-name");
        float score = Float.parseFloat(request.getParameter("score"));
        Student student = new  Student(id, name, className, score);

        boolean success = studentService.editStudent(student);

        if (success) {
            resp.getWriter().print("Edit success");
        }
        else {
            resp.getWriter().print("Edit fails");
        }



    }


}
