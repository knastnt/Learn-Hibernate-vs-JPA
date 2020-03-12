import char_4.Item;
import char_73.Bid73;
import char_73.Item73;
import char_813.Address813;
import char_813.User813;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Char_10 {
    @Test
    public void tst(){

        long a = System.nanoTime();
        System.out.println(0);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DB_on_work_computer");
        long b = System.nanoTime();
        System.out.println(b-a);
        EntityManager em = emf.createEntityManager();
        long c = System.nanoTime();
        System.out.println(c-b);

        SessionFactory sessionFactory = emf.unwrap(SessionFactory.class);

        em.getTransaction().begin();

//        em.persist(user813);
//        em.persist(user813_2);

        em.getTransaction().commit();

        em.close();
        emf.close();


        long d = System.nanoTime();
        System.out.println(d-c);
    }

    @Test
    public void equale(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DB_on_work_computer");

        EntityManager em = emf.createEntityManager();

//        em.getTransaction().begin();
//        em.getTransaction().commit();

        Item73 item = em.find(Item73.class, 1);
        Item73 item2 = em.find(Item73.class, 1);

        Assert.assertTrue(item == item2);
        Assert.assertTrue(item.getId() == item2.getId());
        Assert.assertTrue(item.equals(item2));

        em.close();
        emf.close();
    }

    @Test
    public void reference(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DB_on_work_computer");

        EntityManager em = emf.createEntityManager();

        Item73 item73 = em.getReference(Item73.class, 6); //Получаем прокси объект, ленивая загрузка
        Item73 item73real = em.find(Item73.class, 10); //Получаем объект, но тоже ленивая загрузка

        System.out.println(item73.getId());
        System.out.println(item73.getBid73s().size());
        for (Bid73 bid73 : item73.getBid73s()) {
            System.out.println(bid73.getId());
            System.out.println(bid73.getValue());
        }

        System.out.println(item73real.getId());
        System.out.println(item73real.getBid73s().size());
        for (Bid73 bid73 : item73real.getBid73s()) {
            System.out.println(bid73.getId());
            System.out.println(bid73.getValue());
        }

        em.close();
        emf.close();
    }
}
