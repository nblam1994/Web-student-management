package com.lam.StudentManagement3.service;

import com.lam.StudentManagement3.dao.AccountDAO;
import com.lam.StudentManagement3.student.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "AccountService")
public class AccountServiceImp implements AccountService {


    @Autowired
    private AccountDAO accountDAO;

    @Override
    public Account getAccount(String id) {
        return accountDAO.getAccount(id);
    }

    @Override
    public boolean editStudent(Account account) {
        return accountDAO.editStudent(account);
    }

    @Override
    public boolean addStudent(Account account) {
        return accountDAO.addStudent(account);
    }
}
