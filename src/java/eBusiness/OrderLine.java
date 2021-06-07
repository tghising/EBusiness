package eBusiness;

/*
    This entity class is used to represent an Orderline object. Orderline represents each item inside an Order.
*/

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order_line")
@NamedQueries({
    @NamedQuery(name = "OrderLine.findAll", query = "SELECT o FROM OrderLine o")
    , @NamedQuery(name = "OrderLine.findByLineNumber", query = "SELECT o FROM OrderLine o WHERE o.lineNumber = :lineNumber")
    , @NamedQuery(name = "OrderLine.findByOrder", query = "SELECT o FROM OrderLine o WHERE o.order = :order")
})
public class OrderLine implements Serializable {

    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "line_number")
    private Long lineNumber;

    @OneToOne
    private Item item;

    @ManyToOne
    private AnOrder order;

    private int quantity;

    public OrderLine() {
        this.item = new Item();
    }

    public OrderLine(Item item, AnOrder order, int quantity) {
        this.item = item;
        this.order = order;
        this.quantity = quantity;
    }

    // setters and getters
    public Long getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Long lineNumber) {
        this.lineNumber = lineNumber;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public AnOrder getOrder() {
        return order;
    }

    public void setOrder(AnOrder order) {
        this.order = order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
