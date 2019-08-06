package com.demo.demohib;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {
        
        Student stud = new Student();
        stud.setSid(101);
        stud.setSname("Rajendra");
        stud.setSdeptid(3162);
        
        Configuration cfg = new Configuration().configure().addAnnotatedClass(Student.class);
        
        SessionFactory sf = cfg.buildSessionFactory();
        
        Session session = sf.openSession();
        
        Transaction tx = session.beginTransaction();
        
        session.save(stud);
        
        tx.commit();
    }
}
