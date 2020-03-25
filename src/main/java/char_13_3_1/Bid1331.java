package char_13_3_1;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Bid1331 {
    @Id
    @GeneratedValue
    private int id;

    private BigDecimal value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    protected Item1331 item1331;


    public Bid1331(BigDecimal value, Item1331 item1331) {
        this.value = value;
        this.item1331 = item1331;
    }

    private Bid1331() {
    }

    public int getId() {
        return id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Item1331 getItem1331() {
        return item1331;
    }
}
