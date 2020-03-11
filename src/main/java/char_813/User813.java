package char_813;

import javax.persistence.*;

@Entity
public class User813 {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(
            fetch = FetchType.LAZY,
            //optional = false,
            cascade = CascadeType.PERSIST
    )
    @JoinColumn(unique = true)
    private Address813 address813; //лэйзи лоад энд мэй би нилл


    public void setAddress813(Address813 address813) {
        this.address813 = address813;
    }
}
