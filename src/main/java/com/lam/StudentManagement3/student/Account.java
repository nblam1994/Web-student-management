package com.lam.StudentManagement3.student;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@EntityListeners(EntityListeners.class)
@Table(name = "account")
public class Account implements Serializable{


    @Id
    @Column(name = "Id")
    private String id;

    @Column(name = "Username")
    private String username;

    @Column(name = "Password")
    private String password;

    public Account() {

    }

    public Account(String id, String username, String password) {
        id = id;
        username = username;
        password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
