import char_73.Item73;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;

public class Char_11_1 {
    @Test
    public void trueWay(){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DB_on_work_computer");

        EntityManager em = null;
        EntityTransaction tx = null;
        try{
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            Item73 item73 = em.find(Item73.class, 34);
            item73.setName("11_1 name");

            tx.commit();
        }catch (Exception e){ //Если, например, при коммите объекта Item Id=30 уже не будет в базе, то появится исключение.
            try{
                tx.rollback();
            }catch (Exception ex){
                System.err.println("rollback falled");
                throw new RuntimeException(ex);
            }
        }finally {
            if(em != null && em.isOpen()){
                em.close();
            }
        }

        emf.close();
    }
}
