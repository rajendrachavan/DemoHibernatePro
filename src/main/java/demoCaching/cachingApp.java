package demoCaching;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class cachingApp {
    public static void main(String[] args) {
        
        Configuration cfg = new Configuration()
                .configure()
                .addAnnotatedClass(EmpRecord.class);
                
        
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(cfg.getProperties());
        SessionFactory sf = cfg.buildSessionFactory(builder.build());
        
        EmpRecord emp;
        
        Session s1 = sf.openSession();
        s1.beginTransaction();
       
        emp = (EmpRecord) s1.get(EmpRecord.class, 101);
        System.out.println("InitialFetch: "+emp);
        
        // Using Query Caching
        Query q1 = s1.createQuery("from EmpRecord where Empid=101");
        q1.setCacheable(true);
        emp = (EmpRecord) q1.uniqueResult();
        System.out.println("InitialQuery: -> "+emp);
        
        //The db is hit only once as the same record is fetched again,
        // so hibernate checks L1 cache.
        
        emp = (EmpRecord) s1.get(EmpRecord.class, 101);
        System.out.println("L1 Cache Result: "+emp);
        s1.getTransaction().commit();
        s1.close();
        
        //The db is hit twice if the same record is fetched from other session.
        Session s2 = sf.openSession();
        s2.beginTransaction();
        
        emp = (EmpRecord) s2.get(EmpRecord.class, 101);
        System.out.println("L2 Cache Result: "+emp);
        
        // Using Query Caching
        Query q2 = s2.createQuery("from EmpRecord where Empid=101");
        q2.setCacheable(true);
        emp = (EmpRecord) q2.uniqueResult();
        System.out.println("CacheQueryFetch: -> "+emp);
        
        
        //The db is hit once again as we are fetching new entry.
        emp = (EmpRecord) s2.get(EmpRecord.class, 102);
        System.out.println("New Fetch->(l1) : "+emp);
        
        s2.getTransaction().commit();
        s2.close();
        
        // To solve Multiple hits to db problem
        // Enable Second level caching.
        
        //To Enable Second Level caching...
        /*
        1) Add Dependencies in pom.xml
        -> net.sf.ehcache 
        -> hibernate-ehcache (connector between ehcache & hibernate)
        -> hibernate-ehcache version should be same as org.hibernate version.
        
        2) Add Properties in hibernate.cfg.xml
        -> <property name="hibernate.cache.use_second_level_cache">true</property>
        -> <property name="hibernate.cache.region.factory_class">org.hibernate.cache.ehcache.EhCacheRegionFactory</property>
        
        3) Set annotations to cacheable class
        -> @Cacheable
        -> @Cache(usage = CacheConcurrencyStrategy.READ_ONLY) < -- > (caching strategy)
        -> above caching strategy just for reading data from db, For Updating use READ_WRITE.
        */
        //After Enabling & setting up Secondlevel Cache,
        // Db is hit only twice for above case.
        // first -> to cache the new record, for same entry it uses L1/L2 as per session.
        // second -> to cache new entry from record
        // i.e(102) as its record is fetched for the first time.
        
        sf.close();
    }
}
 