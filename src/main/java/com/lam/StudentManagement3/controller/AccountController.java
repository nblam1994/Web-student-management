package com.lam.StudentManagement3.controller;
import com.lam.StudentManagement3.service.AccountService;
import com.lam.StudentManagement3.service.StudentService;
import com.lam.StudentManagement3.student.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;


    @Autowired
    private StudentService studentService;


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegister() {
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void postRegister(HttpServletRequest resquest, HttpServletResponse response) throws IOException {

        String id = resquest.getParameter("id");
        String username = resquest.getParameter("username");
        String password = resquest.getParameter("password");
        Account account = accountService.getAccount(id);

        if(account == null) {

            accountService.addStudent(new Account(id, username, password));
            response.sendRedirect("/");
        }
        else {

            response.getWriter().print("Account with this id already exist");
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getLogin(){

        return "login";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void postLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String id = request.getParameter("id");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Account account = accountService.getAccount(id);
        if(account != null) {

            if(username.equals("admin") && password.equals("admin")) {
                response.sendRedirect("/student-list");
            }
            else if (username.equals(account.getUsername()) && password.equals(account.getPassword())) {
                response.sendRedirect("/student/" + id);
            }
            else {
                response.getWriter().print("Account information is invalid");
            }
        }
        else {
            response.getWriter().print("No Account with this Id exist");
        }
    }


    @RequestMapping(value = "/account-edit/{id}", method = RequestMethod.GET)
    public String editPassword(@PathVariable String id, Map<String, Object> model) {

        System.out.println("Here");
        Account account = accountService.getAccount(id);
        model.put("Id", account.getId());
        model.put("Username", account.getUsername());
        model.put("Password", account.getPassword());

        return "edit-account";
    }


    @RequestMapping(value = "/account-edit", method = RequestMethod.POST)
    public void editPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String Id = request.getParameter("Id");
        String Username = request.getParameter("Username");
        String Password = request.getParameter("Password");
        accountService.editStudent(new Account(Id, Username, Password));
        response.sendRedirect("/");
    }



}
