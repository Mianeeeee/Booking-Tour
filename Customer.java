import java.math.BigDecimal;
import java.util.*;

public class Customer extends Human {
    private String tourBookings;
    private String bookingState;
    private String bookingDate;
    private int numberOfCustomers;
    private BigDecimal price;
    private static Map<String, Customer> CustomerList = new HashMap<>();

    public static void add(Customer customer) {
        CustomerList.put(customer.getId(), customer);
    }

    public Customer() {
        super();
        this.tourBookings = "";
        this.bookingState = "";
        this.bookingDate = "";
        this.numberOfCustomers = 0;
        this.price = new BigDecimal("0.00");
    }

    public Customer(String Id, String Name, String Birthday, String PhoneNumber, String Email, String tourBookings,
            String bookingState, String bookingDate, int numberOfCustomers, BigDecimal price) {
        super(Id, Name, Birthday, PhoneNumber, Email);
        this.tourBookings = tourBookings;
        this.bookingState = bookingState;
        this.bookingDate = bookingDate;
        this.numberOfCustomers = numberOfCustomers;
        this.price = price;
    }

    // Get
    public String getTourBookings() {
        return this.tourBookings;
    }

    public String getBookingState() {
        return this.bookingState;
    }

    public String getBookingDate() {
        return this.bookingDate;
    }

    public int getNumberOfCustomers() {
        return this.numberOfCustomers;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    // Set
    public void setTourBookings(String tourBookings) {
        this.tourBookings = tourBookings;
    }

    public void setBookingState(String bookingState) {
        this.bookingState = bookingState;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

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
