package eBusiness;

/*
    This EJB class is used to persist and retrieve Customer objects to and from the entity manager.
*/

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateful
public class CustomerEJB {

    // Attributes             
    @PersistenceContext(unitName = "EBusinessPU")
    private EntityManager em;

    // method return all list of customers   
    public List<Customer> findCustomers() {
        TypedQuery<Customer> query = em.createNamedQuery("Customer.findAll", Customer.class);
        return query.getResultList();
    }

    // method return customer by full name
    public List<Customer> findCustomerByFullName(String fullName) {
        TypedQuery<Customer> query = em.createNamedQuery("Customer.findByFullName", Customer.class).setParameter("fullName", fullName);
        return query.getResultList();
    }

    // method to persist customer  
    public Customer createCustomer(Customer customer) {
        em.persist(customer);
        return customer;
    }

    public Customer findCustomerById(Long id) {
        return em.find(Customer.class, id);
    }

}
