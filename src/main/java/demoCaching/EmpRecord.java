package demoCaching;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "emp_table")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class EmpRecord {
    
    @Id
    private int empid;
    private String empname;
    private int empscore;

    public int getEmpid() {
        return empid;
    }

    public void setEmpid(int empid) {
        this.empid = empid;
    }

    public String getEmpName() {
        return empname;
    }

    public void setEmpName(String empName) {
        this.empname = empName;
    }

    public int getEmpscore() {
        return empscore;
    }

    public void setEmpscore(int empscore) {
        this.empscore = empscore;
    }

    @Override
    public String toString() {
        return "EmpRecord{" + "empid=" + empid + ", empName=" + empname + ", empscore=" + empscore + '}';
    }
    
}
