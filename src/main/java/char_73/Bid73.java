package char_73;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Bid73 {
    @Id
    @GeneratedValue
    private int id;

    private BigDecimal value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    protected Item73 item73;


    public Bid73(BigDecimal value, Item73 item73) {
        this.value = value;
        this.item73 = item73;
    }

    private Bid73() {
    }

    public int getId() {
        return id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Item73 getItem73() {
        return item73;
    }
}
