import helloworld.Message;
import org.junit.*;
import org.junit.runners.MethodSorters;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Char_2_2_3 {
    private EntityManagerFactory emf;
    private EntityManager em;

    @Before
    public void init(){
//        emf = Persistence.createEntityManagerFactory("HelloWorldPU");
        emf = Persistence.createEntityManagerFactory("DB_on_work_computer");
        em = emf.createEntityManager();
    }
    @After
    public void finish(){
        em.close();
        emf.close();
    }

    @Test
    public void writeToDBMessages(){
        Message message = new Message();
        Message message2 = new Message();
        message.setText("Hello world!");
        message2.setText("Hello world 2!");


        em.getTransaction().begin();

        em.persist(message);
        em.persist(message2);

        em.getTransaction().commit();
    }
    @Test
    public void writeToDBMessagesAndReadIt(){
        Message message = new Message();
        Message message2 = new Message();
        message.setText("First Message");
        message2.setText("Second Message");

        em.getTransaction().begin();

        em.persist(message);
        em.persist(message2);

        em.getTransaction().commit();



        em.getTransaction().begin();

        List<Message> messages = em.createQuery("select m from Message m").getResultList();

        Assert.assertTrue(messages.size() > 0);


//        Assert.assertEquals(messages.size(), 2);
//        Assert.assertEquals(messages.get(0).getText(), "First Message");

        messages.get(0).setText("Text changed!");
        message2.setText("Даже так! Текст меняется");

        em.getTransaction().commit();
    }
}
