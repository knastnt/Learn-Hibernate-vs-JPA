package char_4;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/*Мы можем переименовать эту сущность и обращаться к ней из SQL запросов по этому имени. Если её не переименовать, то будет две сущности Item (+1 из пакета char
_3_3)*/
@Entity (name = "Item_char_4")
//Можем поменять просто так таблицу
@Table(name = "Iten_custon_table_name")
public class Item {
    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    private int id;
    @NotNull
    @Size(
            min = 2,
            max = 200
    )
    private String name;
    @NotNull
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
