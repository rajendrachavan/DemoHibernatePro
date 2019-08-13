package demoHql;

import demoCaching.EmpRecord;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class hibernateGet_LoadDemo {
    public static void main(String[] args) {
        
        Configuration cfg = new Configuration()
                .configure()
                .addAnnotatedClass(EmpRecord.class);
        
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(cfg.getProperties());
        SessionFactory sf = cfg.buildSessionFactory(builder.build());
        
        Session s1 = sf.openSession();
        s1.beginTransaction();
        
        // get() -> It Gives Object
        //It hits database whenever it is called, even if the object isn't used.
        // if the record isn't present in the db, then it pass null &
        // for specific entry it throws NullPointerException.
        EmpRecord e1 = (EmpRecord) s1.get(EmpRecord.class, 40);
        System.out.println(e1);
        
        //load() -> It gives proxy object
        //It hits database only when the object is used or called.
        //if the record isn't present in the d, then it throws ObjectNotFoundException.
        // so that we can surround it with try-catch and handle the exception.
        EmpRecord e2 = (EmpRecord) s1.load(EmpRecord.class, 41);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(hibernateGet_LoadDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(e2);
        
        s1.getTransaction().commit();
    }
}
