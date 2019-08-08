package com.demo.demohib;

import java.util.List;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


public class App {
    public static void main(String[] args) {
        
//        Department dept = new Department();
//        dept.setDeptid(801);
//        dept.setDeptName("Java");
//        
//        Student stud = new Student();
//        stud.setSid(103);
//        stud.setSname(new StudentName("Rajendra", "Anil", "Chavan"));
//        stud.setMarks(81);
//        stud.setDept(dept);
//        dept.getStud().add(stud);
//        
//        Student stud1 = new Student();
//        stud1.setSid(104);
//        stud1.setSname(new StudentName("Anuj", "Vithal", "Shingte"));
//        stud1.setMarks(71);
//        stud1.setDept(dept);
//        dept.getStud().add(stud1);
        
        Configuration cfg = new Configuration()
                .configure()
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Department.class);
        
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(cfg.getProperties());
        SessionFactory sf = cfg.buildSessionFactory(builder.build());
        Session s1 = sf.openSession();
        s1.beginTransaction();
        
        Department dp = (Department) s1.get(Department.class, 801);
        System.out.println(dp.getDeptName());
        /* 
        //By Default Fetch type = lazy
        //So User needs to specify the data to be fetched.
        // Else you can set the fetch type in (mappedBy = "#variable",fetch = FetchType.EAGER) )
        List<Student> std1 = dp.getStud();
        for(Student s: std1)
            System.out.println(s);
        */
        
//        s1.saveOrUpdate(dept);
//        s1.saveOrUpdate(stud);
//        s1.saveOrUpdate(stud1);
        
        s1.getTransaction().commit(); 
        
        
    }
}
