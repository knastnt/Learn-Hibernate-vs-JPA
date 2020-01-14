import char_3_3.Item;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.validation.*;
import java.util.Date;
import java.util.Set;

public class Char_3_3 {
    @Test
    public void checkItemValid(){
        Item item = new Item("Картошка");
        item.setAuctionEnd(new Date());

        Item item2 = new Item("К");

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        Set<ConstraintViolation<Item>> violations = validator.validate(item);
        Set<ConstraintViolation<Item>> violations2 = validator.validate(item2);

        Assert.assertEquals(violations.size(), 1);
        Assert.assertEquals(violations2.size(), 2);
    }

    @Test
    public void tryToWriteIncorrectItem(){
        Item item = new Item("tryToWriteIncorrectItemtryToWriteIncorrectItemtryToWriteIncorrectItemtryToWriteIncorrectItemtryToWriteIncorrectItemtryToWriteIncorrectItemtryToWriteIncorrectItemtryToWriteIncorrectItemtryToWriteIncorrectItemtryToWriteIncorrectItem");

        EntityManagerFactory emf = null;
        EntityManager em = null;
        try {
//            emf = Persistence.createEntityManagerFactory("HelloWorldPU");
            emf = Persistence.createEntityManagerFactory("DB_on_work_computer");
            em = emf.createEntityManager();

            em.getTransaction().begin();

            em.persist(item);

            em.getTransaction().commit();

            Assert.fail("Досюда не должно дойти, т.к. коммит должен кинуть ошибку валидации");
        }catch (RollbackException e){
            if (e.getCause() instanceof ConstraintViolationException){
                for (ConstraintViolation<?> violation : ((ConstraintViolationException) e.getCause()).getConstraintViolations()) {
                    System.out.println(violation.getPropertyPath() + ": " + violation.getMessage());
                }
            }else{
                throw e;
            }
        }finally {
            if (em != null && em.isOpen()){
                em.close();
            }
            if (emf != null && emf.isOpen()){
                emf.close();
            }
        }


    }
}
