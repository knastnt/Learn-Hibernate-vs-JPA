package char_813;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Address813 {
    @Id
    @GeneratedValue
    private Long id;

    private String street;



    public void setStreet(String street) {
        this.street = street;
    }
}
