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
    protected int id;
    @NotNull
    @Size(
            min = 2,
            max = 202,
            message = "размер строки не проходит проверку"
    )
    protected String name;
    @Future
    protected Date auctionEnd;
}
