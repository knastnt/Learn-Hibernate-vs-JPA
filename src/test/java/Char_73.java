import char_4.Bid;
import char_4.Item;
import char_4.User;
import char_73.Bid73;
import char_73.Item73;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class Char_73 {
    @Test
    public void writeItem(){
        Item73 item73 = new Item73();
        item73.setName("Имя итема");
        item73.getBid73s().add(new Bid73(new BigDecimal(10), item73));
        item73.getBid73s().add(new Bid73(new BigDecimal(20), item73));
        item73.getBid73s().add(new Bid73(new BigDecimal(30), item73));



        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DB_on_work_computer");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        em.persist(item73);

        em.getTransaction().commit();

        em.close();
        emf.close();

    }

    @Test
    public void readItem(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DB_on_work_computer");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

//        Set<Bid73> bid73Set = em.createQuery("select m from Item73 m", Bid73.class);
        List<Item73> item73s = em.createQuery("select m from Item73 m").getResultList();

        Item73 item73 = em.find(Item73.class, 2); //Получаю итем с id=2
        if(item73 != null){
            item73.getBid73s().clear(); //Удаляем все биды из коллекции
        }

        //item73s.forEach(em::remove); //неа, не удаляются, т.к. сначала надо удалить все bid73

        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}
