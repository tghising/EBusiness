package eBusiness;

/*
    This EJB class is used to persist and retrieve Order objects to and from the entity manager.
*/

import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateful
public class OrderEJB {

    // Attributes             
    @PersistenceContext(unitName = "EBusinessPU")
    private EntityManager em;

    public List<AnOrder> findOrders() {
        TypedQuery<AnOrder> query = em.createNamedQuery("AnOrder.findAll", AnOrder.class);
        return query.getResultList();
    }

    public List<OrderLine> findOrderLines() {
        TypedQuery<OrderLine> query = em.createNamedQuery("OrderLine.findAll", OrderLine.class);
        return query.getResultList();
    }

    // method return orders by id
    public List<AnOrder> findOrderById(Long id) {
        TypedQuery<AnOrder> query = em.createNamedQuery("AnOrder.findById", AnOrder.class).setParameter("id", id);
        return query.getResultList();
    }

    public List<OrderLine> findOrderLineByLineNumber(Long lineNumber) {
        TypedQuery<OrderLine> query = em.createNamedQuery("OrderLine.findByLineNumber", OrderLine.class).setParameter("lineNumber", lineNumber);
        return query.getResultList();
    }
    
     public OrderLine findOrderLineById(Long lineNumber) {
        return em.find(OrderLine.class, lineNumber);
    }

    public List<AnOrder> findOrderByCustomer(Customer customer) {
        TypedQuery<AnOrder> query = em.createNamedQuery("AnOrder.findByCustomerId", AnOrder.class).setParameter("customer", customer);
        return query.getResultList();
    }

    public List<OrderLine> findOrderLineByOrder(AnOrder order) {
        TypedQuery<OrderLine> query = em.createNamedQuery("OrderLine.findByOrder", OrderLine.class).setParameter("order", order);
        return query.getResultList();
    }

    public AnOrder createOrder(AnOrder order) {
        em.persist(order);
        return order;
    }

    public OrderLine createOrderLine(OrderLine OrderLine) {
        em.persist(OrderLine);
        return OrderLine;
    }

    public OrderLine deleteOrderLine(OrderLine line) {
        em.remove(em.merge(line));
        return line;
    }
    
    public AnOrder deleteOrder(AnOrder order) {
        em.remove(em.merge(order));
        return order;
    }
}
