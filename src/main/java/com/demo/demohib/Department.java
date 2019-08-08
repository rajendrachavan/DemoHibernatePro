package com.demo.demohib;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Department {
    
    @Id
    private int deptid;
    private String DeptName;
    @OneToMany(mappedBy = "department",fetch = FetchType.EAGER)
    private List<Student> student = new ArrayList<>();

    public List<Student> getStud() {
        return student;
    }

    public void setStud(List<Student> stud) {
        this.student = stud;
    }

    public int getDeptid() {
        return deptid;
    }

    public void setDeptid(int deptid) {
        this.deptid = deptid;
    }

    public String getDeptName() {
        return DeptName;
    }

    public void setDeptName(String DeptName) {
        this.DeptName = DeptName;
    }
    
}
