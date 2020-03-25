import char13_2_2.AuditLogInterceptor;
import char13_2_2.Item1322;
import char_13_3_1.Item1331;
import org.hibernate.EmptyInterceptor;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditQuery;
import org.hibernate.jpa.AvailableSettings;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Char_13_3_1 {
    @Test
    public void enverstest1(){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DB_on_work_computer");



        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

            tx.begin();

            Item1331 item1 = new Item1331();
            item1.setName("item1 name");
            em.persist(item1);

            Item1331 item2 = new Item1331();
            item2.setName("item2 name");
            em.persist(item2);


            tx.commit();



        if(em != null && em.isOpen()){
            em.close();
        }
        emf.close();
    }

    @Test
    public void enverstest2(){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DB_on_work_computer");



        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

            tx.begin();

            List<Item1331> item1331List = em.createQuery("select i from Item1331 i").getResultList();

            for (Item1331 item1331 : item1331List) {
                item1331.setName("new name at time sec=" + String.valueOf(LocalTime.now().getSecond()));
            }

            tx.commit();



        if(em != null && em.isOpen()){
            em.close();
        }
        emf.close();
    }

    @Test
    public void enverstest3(){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DB_on_work_computer");



        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

            tx.begin();

            List<Item1331> item1331List = em.createQuery("select i from Item1331 i").getResultList();

            for (Item1331 item1331 : item1331List) {
                //em.remove(item1331);
            }

            tx.commit();



        if(em != null && em.isOpen()){
            em.close();
        }
        emf.close();
    }

    @Test
    public void enverstest4(){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DB_on_work_computer");



        EntityManager em = emf.createEntityManager();

        AuditReader auditReader = AuditReaderFactory.get(em);

        Number revision = auditReader.getRevisionNumberForDate(new Date()); //Сейчас или раньше

        List<Number> itemRevisions = auditReader.getRevisions(Item1331.class, 81);

        for (Number itemRevision : itemRevisions) {
            Date date = auditReader.getRevisionDate(itemRevision);
            System.out.println(date.getTime());
        }

        if(em != null && em.isOpen()){
            em.close();
        }
        emf.close();
    }

    @Test
    public void enverstest5(){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DB_on_work_computer");



        EntityManager em = emf.createEntityManager();

        AuditReader auditReader = AuditReaderFactory.get(em);

        AuditQuery query = auditReader.createQuery().forRevisionsOfEntity(Item1331.class, false, true);
        List<Object[]> result = query.getResultList();
        for (Object[] tuple : result) {
            Item1331 item = (Item1331) tuple[0];
            DefaultRevisionEntity revision = (DefaultRevisionEntity) tuple[1];
            RevisionType revisionType = (RevisionType) tuple[2];

            System.out.println();
        }



        if(em != null && em.isOpen()){
            em.close();
        }
        emf.close();
    }

    @Test
    public void enverstest6(){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DB_on_work_computer");



        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();


        AuditReader auditReader = AuditReaderFactory.get(em);

        //Получение старой версии экземпляра
        Item1331 item1331 = auditReader.find(Item1331.class, 81, 15);

        tx.begin();
        //Откат текущей версии экземпляра до старого состояния
        em.unwrap(Session.class).replicate(item1331, ReplicationMode.OVERWRITE);

        tx.commit();


        if(em != null && em.isOpen()){
            em.close();
        }
        emf.close();
    }
}
