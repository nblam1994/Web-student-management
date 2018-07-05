package com.lam.StudentManagement3.service;

import com.lam.StudentManagement3.student.Account;
import com.lam.StudentManagement3.student.Student;

import java.util.List;

public interface AccountService {

    public Account getAccount(String id);
    public boolean editStudent(Account account);
    public boolean addStudent(Account account);
}
