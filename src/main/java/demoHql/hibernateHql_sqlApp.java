package demoHql;

import demoCaching.EmpRecord;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


public class hibernateHql_sqlApp {
    public static void main(String[] args) {
        
        Configuration cfg = new Configuration()
                .configure()
                .addAnnotatedClass(EmpRecord.class);
        
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(cfg.getProperties());
        SessionFactory sf = cfg.buildSessionFactory(builder.build());
        
        Session s1 = sf.openSession();
        s1.beginTransaction();
        
        // To Insert 50 Entries into DB.
        /*
        Random r = new Random();
        for(int i=1; i<=50;i++){
            emp = new EmpRecord();
            emp.setEmpid(i);
            emp.setEmpName("Name: "+(i*10));
            emp.setEmpscore(r.nextInt(100));
            s1.save(emp);
        }
        */
        
        //HQL Query to view all record from DB.
        Query q = s1.createQuery("from EmpRecord");
        List<EmpRecord> e = q.list();
        
        for(EmpRecord er : e)
            System.out.println(er);
        
        //Hql Query with WHERE clause.
        Query q1 = s1.createQuery("from EmpRecord where empid=7");
        EmpRecord emp1 = (EmpRecord) q1.uniqueResult();
        System.out.println("-> : "+emp1);
        
        //Hql Query to get certain Columns from db.
        Query q2 = s1.createQuery("select empid,empname,empscore from EmpRecord where empid=7");
        Object[] emp2 = (Object[]) q2.uniqueResult();
            System.out.println("[Id: "+emp2[0]+" | EmpName: "+emp2[1]+" | EmpScore: "+emp2[2]+"]");
        
        //Different ways: 
        //Hql Query to get all values in certain style from db.
        Query q3 = s1.createQuery("select empid,empname,empscore from EmpRecord");
        List<Object[]> emp3 = (List<Object[]>) q3.list();
        emp3.forEach((emp) -> {
            System.out.println("[Id: "+emp[0]+" | EmpName: "+emp[1]+" | EmpScore: "+emp[2]+"]");
        });
        
        //Hql Query with preparedStatement 
        //Just use ->(':variablename') in query
        
        Query q4 = s1.createQuery("select sum(empscore) from EmpRecord e"
                + " where e.empscore > :value");
        q4.setParameter("value", 10);
        Long marks = (Long) q4.uniqueResult();
        System.out.println("Sum(EmpScore): "+marks);
        
        
        // Native Queries......
        //SqlQuery in Hibernate
        SQLQuery sql1 = s1.createSQLQuery("select * from emp_table where empscore < 10");
        sql1.addEntity(EmpRecord.class);
        List<EmpRecord> emprec = sql1.list();
        for(EmpRecord er: emprec)
            System.out.println(er);
        
        //SqlQuery for retriving specific columns
        SQLQuery sql2 = s1.createSQLQuery("select empname, empscore from emp_table where empscore <30");
        sql2.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List emprec1 = sql2.list();
        for(Object o1: emprec1){
            Map m = (Map) o1;
            System.out.println(m.get("empname")+" : "+m.get("empscore"));
        }
            
        
         
        s1.getTransaction().commit();
    }
}
