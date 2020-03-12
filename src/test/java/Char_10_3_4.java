import char_73.Bid73;
import char_73.Item73;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Char_10_3_4 {
    @Test
    public void detached(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DB_on_work_computer");

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Item73 item73 = em.find(Item73.class, 10);

        Assert.assertTrue(em.contains(item73));
        em.detach(item73);
        Assert.assertFalse(em.contains(item73));

        em.getTransaction().commit();


        em.close();



        item73.setName("имя вне контента");



        EntityManager em2 = emf.createEntityManager();

        em2.getTransaction().begin();

        Item73 item73merged = em2.merge(item73);
        //item73merged.setName("имя в новом контенте");

        em2.getTransaction().commit();

        em2.close();



        emf.close();
    }
}
