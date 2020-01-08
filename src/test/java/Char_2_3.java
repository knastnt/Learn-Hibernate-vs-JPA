import helloworld.Message;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class Char_2_3 {
    @Test
    public void throwTheSession(){
        //Динамическое задание параметров для SessionFactory - аналога EntityManagerFactory
        StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
        serviceRegistryBuilder
                .applySetting("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
                .applySetting("hibernate.connection.url", "jdbc:mysql://localhost:3306/test?serverTimezone=UTC")
                .applySetting("hibernate.connection.username", "root")
                .applySetting("hibernate.connection.password", "1234")
                .applySetting("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect")
                .applySetting("hibernate.current_session_context_class", "thread")
                .applySetting("hibernate.format_sql", "true")
                .applySetting("hibernate.use_sql_comments", "true")
                .applySetting("hibernate.hbm2ddl.auto", "create");
        ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();

        MetadataSources metadataSources = new MetadataSources(serviceRegistry);
        metadataSources.addAnnotatedClass(Message.class);
        MetadataBuilder metadataBuilder = metadataSources.getMetadataBuilder();
        SessionFactory sessionFactory = metadataBuilder.build().buildSessionFactory();
        Session currentSession = sessionFactory.getCurrentSession(); //Закрывается автоматом при коммите

        currentSession.getTransaction().begin();

        Message message = new Message();
        message.setText("by hibernate");
        currentSession.persist(message);
        Message message2 = new Message();
        message2.setText("дратуте");
        currentSession.persist(message2);

//        currentSession.getTransaction().commit();
//
//        currentSession.getTransaction().begin();

        CriteriaQuery<Message> criteriaQuery = currentSession.getCriteriaBuilder().createQuery(Message.class);
        criteriaQuery.from(Message.class);

        List<Message> messages = currentSession.createQuery(criteriaQuery).getResultList();

        currentSession.getTransaction().commit();

        Assert.assertEquals(messages.size(), 2);

    }
}
