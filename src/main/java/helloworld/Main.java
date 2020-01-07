package helloworld;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.ColumnMetadata;

public class Main {
    public static void main(String[] args) {
        Message message = new Message();
        message.setText("Hello world!");
        System.out.println(message.getText());


        Configuration configuration = new Configuration().configure();
//        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory();//builder.build());

        sessionFactory.close();
    }
}
