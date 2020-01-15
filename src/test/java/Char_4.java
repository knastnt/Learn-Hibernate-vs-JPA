import char_4.Bid;
import char_4.Item;
import char_4.User;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class Char_4 {
    @Test
    public void writeItem(){
        Item item = new Item("имя товара");
        item.setAuctionEnd(new Date());

        User user = new User("kostya");
        user.setFirstname("Константин");
        user.setLastname("Зубрилин");

        User user2 = new User("test");
        user2.setFirstname("имя_которое_длиннее_255_символов:  Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla eros tortorulla eros tortorulla eros tortor, aliquam sit amet odi Nunc imperdiet suscipit pulvinar.  260 символов. это всё.");
        user2.setLastname("запретит ли валидатор добавлять такое в бд?...");

        Bid bid = new Bid(153.54);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DB_on_work_computer");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        em.persist(item);
        em.persist(user);
        em.persist(user2);
        em.persist(bid);

        em.getTransaction().commit();

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Item> query = cb.createQuery(Item.class);
        Root<Item> fromItem = query.from(Item.class);
        query.select(fromItem);
        List<Item> items = em.createQuery(query).getResultList();
        for (Item item1 : items) {
            System.out.println(item1.getId() + " " + item1.getName());
        }

        CriteriaQuery<User> query2 = cb.createQuery(User.class);
        Root<User> fromUser2 = query2.from(User.class);
        query2.select(fromUser2);
        List<User> users = em.createQuery(query2).getResultList();
        for (User usr : users) {
            System.out.println(usr.getUsername() + " " + usr.getFirstname() + " " + usr.getLastname());
        }

        em.close();
        emf.close();

    }
}
