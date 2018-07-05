package com.lam.StudentManagement3.dao;

import com.lam.StudentManagement3.student.Account;


import java.util.List;

public interface AccountDAO {

    public Account getAccount(String id);
    public boolean editStudent(Account account);
    public boolean addStudent(Account account);
}
