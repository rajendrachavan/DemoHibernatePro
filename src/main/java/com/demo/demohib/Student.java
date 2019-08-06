package com.demo.demohib;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Student {
    
    @Id
    private int sid;
    private String sname;
    private int sdeptid;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public int getSdeptid() {
        return sdeptid;
    }

    public void setSdeptid(int sdeptid) {
        this.sdeptid = sdeptid;
    }

    @Override
    public String toString() {
        return "Student{" + "sid=" + sid + ", sname=" + sname + ", sdeptid=" + sdeptid + '}';
    }
    
}
