package char_4;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@org.hibernate.annotations.Immutable    //Неизменяемая сущность. Не делать сеттеры!
public class Bid {
    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    private Long id;

    private double price;

    public Bid(double price) {
        this.price = price;
    }
}
