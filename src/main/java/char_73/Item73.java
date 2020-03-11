package char_73;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Item73 {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    @OneToMany(mappedBy = "item73", fetch = FetchType.LAZY,  cascade = CascadeType.PERSIST, orphanRemoval = true) //orphanRemoval - удаляем биды из базы при удалении из коллекции, иначе при удалении из коллекции они не удаляются и в базе ничего не меняется
    private Set<Bid73> bid73s = new HashSet<>();


    public Set<Bid73> getBid73s() {
        return bid73s;
    }

    public void setName(String name) {
        this.name = name;
    }
}
