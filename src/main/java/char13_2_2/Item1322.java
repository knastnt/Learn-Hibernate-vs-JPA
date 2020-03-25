package char13_2_2;

import char_73.Bid73;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Item1322 {
    @Id
    @GeneratedValue
    private int id;

    private String name;


    public void setName(String name) {
        this.name = name;
    }
}
