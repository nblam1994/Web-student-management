package com.lam.StudentManagement3.dao;

import com.lam.StudentManagement3.student.Account;
import com.lam.StudentManagement3.student.AccountRepository;
import com.lam.StudentManagement3.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;


@Component(value = "AccountJPA")
public class AccountDAOImp implements AccountDAO {


    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account getAccount(String id) {


        try {

            Optional optional = accountRepository.findById(id);

            if (optional.isPresent()) {
                return (Account) optional.get();
            }
            else {
                return null;
            }

        }
        catch (Exception e) {

            return null;
        }
    }

    @Override
    public boolean editStudent(Account account) {


        Account acc = getAccount(account.getId());

        try {
            if (acc != null) {

                accountRepository.save(account);
                return true;
            }
        }
        catch (Exception e) {

        }

        return false;
    }

    @Override
    public boolean addStudent(Account account) {


        Account acc = getAccount(account.getId());

        System.out.print(acc);
        try {
            if (acc == null) {

                accountRepository.save(account);
                return true;
            }
        }
        catch (Exception e) {

        }

        return false;


    }
}
