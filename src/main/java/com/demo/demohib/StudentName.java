package com.demo.demohib;

import javax.persistence.Embeddable;

@Embeddable
public class StudentName {
    
    private String fname;
    private String mname;
    private String lname;

    public StudentName() {
    }

    public StudentName(String fname, String mname, String lname) {
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    @Override
    public String toString() {
        return "StudentName{" + "fname=" + fname + ", mname=" + mname + ", lname=" + lname + '}';
    }
   
}
