package char_13_3_1;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@org.hibernate.envers.Audited
public class Item1331 {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    @OneToMany(mappedBy = "item1331", fetch = FetchType.LAZY,  cascade = CascadeType.PERSIST, orphanRemoval = true) //orphanRemoval - удаляем биды из базы при удалении из коллекции, иначе при удалении из коллекции они не удаляются и в базе ничего не меняется
    @org.hibernate.envers.NotAudited
    private Set<Bid1331> bid1331s = new HashSet<>();


    public Set<Bid1331> getBid1331s() {
        return bid1331s;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
