package com.lam.StudentManagement3.student;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@EntityListeners(EntityListeners.class)
@Table(name = "account")
public class Account implements Serializable{


    @Id
    @Column(name = "Id")
    private String Id;

    @Column(name = "Username")
    private String Username;

    @Column(name = "Password")
    private String Password;

    public Account() {

    }

    public Account(String id, String username, String password) {
        Id = id;
        Username = username;
        Password = password;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    @Override
    public String toString() {
        return "Account{" +
                "Id='" + Id + '\'' +
                ", Username='" + Username + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }
}
