import java.math.BigDecimal;
import java.util.*;

public class Customer extends Human {
    private int numberOfCustomers;
    private BigDecimal price;
    private static Map<String, Customer> CustomerList = new HashMap<>();

    public static void add(Customer customer) {
        CustomerList.put(customer.getId(), customer);
    }

    public Customer() {
        super();
        this.numberOfCustomers = 0;
        this.price = new BigDecimal("0.00");
    }

    // Get

    public int getNumberOfCustomers() {
        return this.numberOfCustomers;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    // Set

    public void setNumberOfCustomers(int numberOfCustomers) {
        this.numberOfCustomers = numberOfCustomers;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    private static Customer findById(String id) {
        return CustomerList.get(id);
    }
}
