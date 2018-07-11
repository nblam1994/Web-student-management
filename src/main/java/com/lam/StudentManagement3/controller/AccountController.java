package com.lam.StudentManagement3.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.lam.StudentManagement3.service.AccountService;
import com.lam.StudentManagement3.service.StudentService;
import com.lam.StudentManagement3.student.Account;
import com.lam.StudentManagement3.student.Student;
import com.lam.StudentManagement3.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;


@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;


    @Autowired
    private StudentService studentService;

    @Autowired
    private ObjectMapper mapper;


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

            Account newAccount = new Account(id, username, password);
            accountService.addStudent(newAccount);
            ObjectMapper mapper = new ObjectMapper();
            response.addCookie(new Cookie(newAccount.getId(), mapper.writeValueAsString(newAccount)));
            response.sendRedirect("/");
        }
        else {

            response.getWriter().print("Account with this id already exist");
        }
    }

    @RequestMapping(value = "/login")
    public String RenderLogin() {

        return "login";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHome(HttpServletResponse response, @CookieValue(value = "id", required=false) String acc, Map<String, Object> model){



        try {
            if (acc != null) {

                ObjectMapper mapper = new ObjectMapper();
                Account accountExist = mapper.readValue(acc, Account.class);
                Student student = studentService.getStudent(accountExist.getId());

                if(student != null) {
                    model.put("student", studentService.getStudent(accountExist.getId()));
                    return "view-student";
                }
            }
        }
        catch (Exception e) {
            System.out.println("Exception at login page");
            return "login";
        }


        return "login";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void postLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {

        String id = request.getParameter("id");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String checked = request.getParameter("checked");


        Account account = accountService.getAccount(id);
        Student student = studentService.getStudent(id);


        ObjectMapper mapper = new ObjectMapper();

        session.setAttribute("account", account);
        session.setAttribute("student", student);

        if(account != null) {

            if(checked.equals("on")) {
                response.addCookie(new Cookie("account",
                        URLEncoder.encode(mapper.writeValueAsString(account), "UTF-8")));
            }

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
    public String editPassword(@PathVariable String id, HttpServletRequest request, Map<String, Object> model) {



        String idUser = (String) request.getSession().getAttribute("id");

        if(idUser.equals(id)) {

            Account account = accountService.getAccount(id);
            model.put("Id", account.getId());
            model.put("Username", account.getUsername());
            model.put("Password", account.getPassword());

            return "edit-account";
        }
        else {

            return "login";
        }
    }


    @RequestMapping(value = "/account-edit", method = RequestMethod.POST)
    public void editPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String Id = request.getParameter("Id");
        String Username = request.getParameter("Username");
        String Password = request.getParameter("Password");
        accountService.editStudent(new Account(Id, Username, Password));
        response.sendRedirect("/");
    }

    @RequestMapping(value = "/cookies", method = RequestMethod.GET)
    public void getCookies(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String name = request.getParameter("name");

        Cookie cookie = new Cookie("name", name);
        Cookie cookieExist = Util.getCookie(request, "name");
        response.addCookie(cookie);

        if(cookieExist != null) {

            response.getWriter().print("Cookies exist, add cookies");
        }
        else {
            response.getWriter().print("Add new cookies");
        }

    }

    @RequestMapping(value = "/session", method = RequestMethod.GET)
    public void getSession(HttpServletRequest request, HttpServletResponse response) {


        try {

            String name = request.getParameter("name");

            HttpSession session = request.getSession();

            String sessionName = (String) request.getSession().getAttribute("name");
            session.setAttribute("name", name);


            if (sessionName != null) {

                response.getWriter().print("Session exist, update session");
            } else {
                response.getWriter().print("Add new session");
            }
        }catch (IOException e) {


        }
    }


    @RequestMapping(value = "/cookie-delete")
    public void deleteCookies(HttpServletResponse response, HttpSession session) {

        try {
        ObjectMapper mapper = new ObjectMapper();
        Account account = (Account) session.getAttribute("account");
        Cookie cookie = new Cookie("account", URLEncoder.encode(mapper.writeValueAsString(account), "UTF-8"));
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        response.sendRedirect("/");
        }
        catch (Exception e) {
            System.out.println("Cookie delete exception");
            e.printStackTrace();
        }
    }



}
