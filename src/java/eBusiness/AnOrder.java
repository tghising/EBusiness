package eBusiness;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
    This entity class is used to represent an Order object. 
*/

@Entity
@NamedQueries({
    @NamedQuery(name = "AnOrder.findAll", query = "SELECT o FROM AnOrder o")
    , @NamedQuery(name = "AnOrder.findById", query = "SELECT o FROM AnOrder o WHERE o.id = :id")
    , @NamedQuery(name = "AnOrder.findByCustomerId", query = "SELECT o FROM AnOrder o WHERE o.customer = :customer")
})
public class AnOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @ManyToOne
    private Customer customer;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OrderLine> orderLines;

    public AnOrder() {
        this.orderLines = new ArrayList<>();
        this.creationDate = new Date();
        this.customer = new Customer();
    }

    // getters and setters  
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "AnOrder{" + "id=" + id + ", creationDate=" + creationDate + ", customer=" + customer + ", orderLines=" + orderLines + '}';
    }

}
