import char_73.Bid73;
import char_73.Item73;
import char_813.Address813;
import char_813.User813;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class Char_813 {
    @Test
    public void writeItem(){
        User813 user813 = new User813();
        Address813 address813 = new Address813();

        //user813.setAddress813(address813);
        address813.setStreet("первак");


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DB_on_work_computer");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        em.persist(user813);

        em.getTransaction().commit();

        em.close();
        emf.close();

    }

    @Test
    public void readItem(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DB_on_work_computer");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        List<User813> user813s = em.createQuery("select m from User813 m").getResultList();
        //адрес - лэйзи лоад

        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}
