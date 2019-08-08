package com.demo.demohib;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Student {
    
    @Id 
    private int sid;
    private StudentName sname;
    private int marks;
    @ManyToOne
    private Department department;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public StudentName getSname() {
        return sname;
    }

    public void setSname(StudentName sname) {
        this.sname = sname;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public Department getDept() {
        return department;
    }

    public void setDept(Department dept) {
        this.department = dept;
    }

    @Override
    public String toString() {
        return "Student{" + "sid=" + sid + ", sname=" + sname + ", marks=" + marks + ", department=" + department + '}';
    }
    
}
