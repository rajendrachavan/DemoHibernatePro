package BLOBDemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class BlobApp {
    public static void main(String[] args) throws IOException {
        
        Configuration cfg = new Configuration()
            .configure()
            .addAnnotatedClass(BlobClass.class);
        
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(cfg.getProperties());
        SessionFactory sf = cfg.buildSessionFactory(builder.build());
        Session s1 = sf.openSession();
        s1.beginTransaction();
        
        BlobClass b = new BlobClass();
        
        //To insert Blob file into database use insertBlob().
        //insertBlob(s1, b);
        
        //To Fetch Blob file from database use fetchBlob().
        //fetchBlob(s1,b);
        
        s1.getTransaction().commit();
        s1.close();
    }
    
    static void insertBlob(Session s1, BlobClass blob){
        String pathname = "/home/webwerks/NetBeansProjects/DemoHib/src/main/java/BLOBDemo/cityscape.png";
        File file1 = new File(pathname);
        byte[] imgdata = new byte[(int)file1.length()];
        
        try {
            FileInputStream fis = new FileInputStream(file1);
            fis.read(imgdata);
        } catch (Exception ex) {
            Logger.getLogger(BlobApp.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        
        blob.setImgname("cityscape3.png");
        blob.setData(imgdata);
        
        s1.save(blob);
    }
    
    static void fetchBlob(Session s1, BlobClass b){
        
        String pathname = "/home/webwerks/NetBeansProjects/DemoHib/src/main/java/BLOBDemo/cityscapeOutput.png";
        File file1 = new File(pathname);
        
        b = (BlobClass) s1.get(BlobClass.class, 1);
        byte[] imgAvatar = b.getData();
        
        try {
            FileOutputStream fout = new FileOutputStream(file1);
            fout.write(imgAvatar);
            fout.close();
        } catch (Exception ex) {
            Logger.getLogger(BlobApp.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
}
