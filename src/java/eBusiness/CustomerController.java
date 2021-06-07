package eBusiness;

/*
    This controller class is used to control the functionalities for the XHTML pages related to Customer model. 
*/

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "customerController")
@RequestScoped
public class CustomerController {

    @EJB
    private CustomerEJB customerEJB;
    
     @EJB
    private OrderEJB orderEJB;

    private Customer selectedCustomer;

    private List<Customer> customerList;
    private List<Customer> foundCustomerList;

    // Constructor
    public CustomerController() {
        this.selectedCustomer = new Customer();
        this.customerList = new ArrayList<>();
        this.foundCustomerList = new ArrayList<>();
    }

    // method to create customer
    public String createCustomer() {
        this.selectedCustomer = customerEJB.createCustomer(selectedCustomer);
        this.customerList = customerEJB.findCustomers();
        return "/customer/list.xhtml?faces-redirect = true";
    }

    // method to search Game and display    
    public String searchCustomer() {
        this.foundCustomerList = customerEJB.findCustomerByFullName(selectedCustomer.getFullName());
        return "/customer/found.xhtml?faces-redirect = true";
    }

    // getters and setters
    public Customer getSelectedCustomer() {
        return selectedCustomer;
    }

    public void setSelectedCustomer(Customer selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
    }

    public List<Customer> getCustomerList() {
        this.customerList = customerEJB.findCustomers();
//        this.orderEJB.findOrderByCustomer(selectedCustomer);
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public List<Customer> getFoundCustomerList() {
        return foundCustomerList;
    }

    public void setFoundCustomerList(List<Customer> foundCustomerList) {
        this.foundCustomerList = foundCustomerList;
    }
}
