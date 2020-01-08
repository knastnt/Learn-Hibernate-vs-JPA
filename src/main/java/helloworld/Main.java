package helloworld;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.tool.hbm2ddl.ColumnMetadata;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class Main {
    public static void main(String[] args) {
        Message message = new Message();
        Message message2 = new Message();
        message.setText("Hello world!");
        message2.setText("Hello world 2!");
        System.out.println(message.getText());


        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();



        EntityManager entityManager = sessionFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        entityManager.persist(message);
        entityManager.persist(message2);
        entityTransaction.commit();

        sessionFactory.close();
    }
}
