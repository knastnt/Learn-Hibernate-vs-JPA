import char13_2_2.AuditLogInterceptor;
import char13_2_2.Item1322;
import org.hibernate.EmptyInterceptor;
import org.hibernate.Interceptor;
import org.hibernate.Session;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.jpa.AvailableSettings;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;




//!!!!!!!! Работает только на JDK 1.7 !!!!!!!!!!!!!!!!!!




public class Char_13_2_2 {
    @Test
    public void intercept(){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DB_on_work_computer");

        Map<String, String> properties = new HashMap<>();
        properties.put(
                AvailableSettings.SESSION_INTERCEPTOR,
                AuditLogInterceptor.class.getName()
        );

        EntityManager em = null;
        EntityTransaction tx = null;
        try{
            em = emf.createEntityManager();

            Session session = em.unwrap(Session.class);
            SessionImplementor sessionImplementor = (SessionImplementor)session;

            EmptyInterceptor intCeptor = (EmptyInterceptor)sessionImplementor.getInterceptor();


            //!!!!!!!! Работает только на JDK 1.7 !!!!!!!!!!!!!!!!!!
            AuditLogInterceptor interceptor = AuditLogInterceptor.class.cast(intCeptor);

            interceptor.setCurrentSession(session);
            interceptor.setCurrentUserId(999L);

            tx = em.getTransaction();
            tx.begin();

            Item1322 item1322 = new Item1322();
            item1322.setName("item1322 name");

            tx.commit();
//        }catch (Exception e){ //Если, например, при коммите объекта Item Id=30 уже не будет в базе, то появится исключение.
//            try{
//                tx.rollback();
//            }catch (Exception ex){
//                System.err.println("rollback falled");
//                throw new RuntimeException(ex);
//            }
        }finally {
            if(em != null && em.isOpen()){
                em.close();
            }
        }

        emf.close();
    }
}
