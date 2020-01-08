import helloworld.Message;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Char_2_2_3 {
    @Test
    public void writeToDBMessages(){
        Message message = new Message();
        Message message2 = new Message();
        message.setText("Hello world!");
        message2.setText("Hello world 2!");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HelloWorldPU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        em.persist(message);
        em.persist(message2);

        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}
