package eBusiness;

/*
    This controller class is used to control the functionalities for the XHTML pages related to Order model. 
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

@Named(value = "orderController")
@RequestScoped
public class OrderController {

    // Attributes   
    @EJB
    private OrderEJB orderEJB;

    @EJB
    private GameEJB gameEJB;

    @EJB
    private MovieEJB movieEJB;

    @EJB
    private CustomerEJB customerEJB;

    private AnOrder createdOrder;

    private Customer selectedCustomer;

    private Long selectedCustomerId;

    private Long selectedItemId;

    private Item selectedItem;

    private Integer orderedQuantity;

    private OrderLine orderLine;

    private OrderLine deletedOrderLine;

    private List<Item> itemList;

    private List<OrderLine> orderLineList;

    private List<OrderLine> foundOrderLineList;

    public OrderController() {
        this.createdOrder = new AnOrder();
        this.orderLine = new OrderLine();
        this.deletedOrderLine = new OrderLine();
        this.itemList = new ArrayList<>();
        this.orderLineList = new ArrayList<>();
        this.foundOrderLineList = new ArrayList<>();
        this.selectedItem = new Item();

    }
    
    

    // Public Methods           
    public String createOrder() {
        this.selectedCustomer = customerEJB.findCustomerById(selectedCustomerId);

        // Create order items
        OrderLine orderline1 = new OrderLine();
        orderline1.setItem(this.selectedItem);

        if (selectedItemId > 0) {
            Movie selectedMovie = movieEJB.findMovieById(selectedItemId);
            Game selectedGame = gameEJB.findGameById(selectedItemId);
            if (selectedMovie != null) {
                int remainingStock = selectedMovie.getStockNumber() - orderedQuantity;
                if (remainingStock > 0) {
                    selectedMovie.setStockNumber(selectedMovie.getStockNumber() - orderedQuantity);
                    movieEJB.updateMovie(selectedMovie);
                    orderline1.setQuantity(this.orderedQuantity);
                } else {
                    orderline1.setQuantity(0);
                }
                orderline1.setItem(selectedMovie);
            } else if (selectedGame != null) {
                int remainingStock = selectedGame.getStockNumber() - orderedQuantity;
                if (remainingStock > 0) {
                    selectedGame.setStockNumber(selectedGame.getStockNumber() - orderedQuantity);
                    gameEJB.updateGame(selectedGame);
                    orderline1.setQuantity(this.orderedQuantity);
                } else {
                    orderline1.setQuantity(0);
                }
                orderline1.setItem(selectedGame);
            } else {
                System.out.println("selectedItem: " + selectedItem);
            }

            if (orderedQuantity > 0) {
                orderline1.setQuantity(this.orderedQuantity);
            }

            //Create an order
            AnOrder order = new AnOrder();
            order.setCreationDate(new Date());
            order.setCustomer(selectedCustomer);

            List<OrderLine> orderlines = new ArrayList<>();
            orderlines.add(orderline1);
            order.setOrderLines(orderlines);
            orderline1.setOrder(order);
            this.createdOrder = orderEJB.createOrder(order);

        }
        this.orderLineList = orderEJB.findOrderLines();
        return "/order/list.xhtml?faces-redirect = true";

    }    

    public int findNumberOfOrdersByCustomer(Customer customer) {
        return orderEJB.findOrderByCustomer(customer).size();
    }

    public String viewCustomerOrders() {

        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();

        String customerId = params.get("selectedCustomerId");

        this.selectedCustomer = customerEJB.findCustomerById(Long.parseLong(customerId));

        List<AnOrder> foundOrderList = orderEJB.findOrderByCustomer(selectedCustomer);
        for (AnOrder customerOrder : foundOrderList) {
            List<OrderLine> customerOrderLine = orderEJB.findOrderLineByOrder(customerOrder);
            this.foundOrderLineList.addAll(customerOrderLine);
        }

        return "/order/details.xhtml?faces-redirect = true";
    }

    public String deleteOrderLine() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        String orderLineId = params.get("orderLineId");
        OrderLine orderLine1 = orderEJB.findOrderLineById(Long.parseLong(orderLineId));
        AnOrder deleteOrder = orderLine1.getOrder();
        deletedOrderLine = orderLine1;
        orderEJB.deleteOrder(deleteOrder);
        this.orderLineList = orderEJB.findOrderLines();
        return "/order/list.xhtml?faces-redirect = true";
    }

    public String searchOrderLine() {
        this.foundOrderLineList = orderEJB.findOrderLineByLineNumber(orderLine.getLineNumber());
        return "/order/found.xhtml?faces-redirect = true";
    }

    public AnOrder getCreatedOrder() {
        return createdOrder;
    }

    public void setCreatedOrder(AnOrder createdOrder) {
        this.createdOrder = createdOrder;
    }

    public Customer getSelectedCustomer() {
        return selectedCustomer;
    }

    public void setSelectedCustomer(Customer selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
    }

    public Item getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Item selectedItem) {
        this.selectedItem = selectedItem;
    }

    public Integer getOrderedQuantity() {
        return orderedQuantity;
    }

    public void setOrderedQuantity(Integer orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }

    public OrderLine getOrderLine() {
        return orderLine;
    }

    public void setOrderLine(OrderLine orderLine) {
        this.orderLine = orderLine;
    }

    public OrderLine getDeletedOrderLine() {
        return deletedOrderLine;
    }

    public void setDeletedOrderLine(OrderLine deletedOrderLine) {
        this.deletedOrderLine = deletedOrderLine;
    }

    public List<Item> getItemList() {
        itemList.addAll(movieEJB.findMovies());
        itemList.addAll(gameEJB.findGames());
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public List<OrderLine> getOrderLineList() {
        this.orderLineList = orderEJB.findOrderLines();
        return orderLineList;
    }

    public void setOrderLineList(List<OrderLine> orderLineList) {
        this.orderLineList = orderLineList;
    }

    public List<OrderLine> getFoundOrderLineList() {
        return foundOrderLineList;
    }

    public void setFoundOrderLineList(List<OrderLine> foundOrderLineList) {
        this.foundOrderLineList = foundOrderLineList;
    }

    public Long getSelectedCustomerId() {
        return selectedCustomerId;
    }

    public void setSelectedCustomerId(Long selectedCustomerId) {
        this.selectedCustomerId = selectedCustomerId;
    }

    public Long getSelectedItemId() {
        return selectedItemId;
    }

    public void setSelectedItemId(Long selectedItemId) {
        this.selectedItemId = selectedItemId;
    }
}
