package char_3_3;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Item {
    @Id
    @GeneratedValue
    private int id;
    @NotNull
    @Size(
            min = 2,
            max = 202,
            message = "размер строки не проходит проверку"
    )
    private String name;
    @NotNull
    @Future
    private Date auctionEnd;

    private Item() {
    }

    public Item(String name) {
        this.name = name;
    }

    public void setAuctionEnd(Date auctionEnd) {
        this.auctionEnd = auctionEnd;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getAuctionEnd() {
        return auctionEnd;
    }
}
