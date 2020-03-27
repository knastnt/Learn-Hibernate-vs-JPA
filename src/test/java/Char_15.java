import char_13_3_1.Item1331;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditQuery;
import org.junit.Test;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;


public class Char_15 {
    @Test
    public void test1(){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DB_on_work_computer");

        EntityManager em = emf.createEntityManager();


        Query query = em.createQuery("select i from Item1331 i");
        List<Item1331> list = query.getResultList();


        if(em != null && em.isOpen()){
            em.close();
        }
        emf.close();
    }

    @Test
    public void test2(){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DB_on_work_computer");

        EntityManager em = emf.createEntityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery criteria = cb.createQuery(Item1331.class);
        Root<Item1331> it = criteria.from(Item1331.class);
        criteria.select(it).where(
            cb.between(it.<Long>get("id"), 90L, 95L)
        );
        TypedQuery<Item1331> query = em.createQuery(criteria);
        List<Item1331> list = query.getResultList();


        if(em != null && em.isOpen()){
            em.close();
        }
        emf.close();
    }
}
