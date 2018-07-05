package com.lam.StudentManagement3.student;



import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "student")
@EntityListeners(AuditingEntityListener.class)
public class Student implements Serializable{

    @Id
    @Column(name = "Id")
    String  studentId;

    @Column(name = "Name")
    String  studentName;

    @Column(name = "Class")
    String  className;

    @Column(name = "Score")
    float   score;

    public Student() {

    }

    public Student(String studentId, String studentName, String className, float score) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.className = className;
        this.score = score;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }


}
