import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Booking extends JFrame {
    private static int nextId = 1;
    private String bookingId;
    private String tourId;
    private String customerId;
    private String bookingDate;
    private String bookingState;

    // Hàm khởi tạo với tất cả các tham số
    public Booking(String bookingId, String tourId, String customerId, String bookingDate, String bookingState) {
        this.bookingId = bookingId;
        this.tourId = tourId;
        this.customerId = customerId;

        // Chuẩn hóa bookingDate theo định dạng dd/mm/yyyy
        if (bookingDate != null && bookingDate.matches("\\d{2}/\\d{2}/\\d{4}")) {
            this.bookingDate = bookingDate;
        } else {
            // Nếu không đúng định dạng, gán giá trị rỗng hoặc xử lý khác tùy ý
            this.bookingDate = "";
        }

        this.bookingState = bookingState;
    }

    // Hàm khởi tạo mặc định
    public Booking() {
        this.bookingId = String.format("BK%03d", Booking.nextId++);
        this.tourId = "";
        this.customerId = "";
        this.bookingDate = "";
        this.bookingState = "Chưa xác nhận";
    }

    // Hàm khởi tạo với các tham số ngoại trừ bookingId và bookingState
    public Booking(String tourId, String customerId, String bookingDate) {
        this.bookingId = String.format("BK%03d", Booking.nextId++);
        this.tourId = tourId;
        this.customerId = customerId;

        // Chuẩn hóa bookingDate theo định dạng dd/mm/yyyy
        if (bookingDate != null && bookingDate.matches("\\d{2}/\\d{2}/\\d{4}")) {
            this.bookingDate = bookingDate;
        } else {
            // Nếu không đúng định dạng, gán giá trị rỗng hoặc xử lý khác tùy ý
            this.bookingDate = "";
        }

        this.bookingState = "Chưa xác nhận";
    }

    // Hàm khởi tạo sao chép
    public Booking(Booking other) {
        this.bookingId = other.bookingId;
        this.tourId = other.tourId;
        this.customerId = other.customerId;
        this.bookingDate = other.bookingDate;
        this.bookingState = other.bookingState;
    }

    // Hàm hiển thị thông tin đặt tour
    public void showBookingDisplay() {
        JFrame frame = new JFrame("Thông tin đặt tour");
        frame.setSize(400, 250);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        panel.add(new JLabel("Mã đặt tour: " + this.bookingId));
        panel.add(new JLabel("Mã tour: " + this.tourId));
        panel.add(new JLabel("Mã khách hàng: " + this.customerId));
        panel.add(new JLabel("Ngày đặt: " + this.bookingDate));
        panel.add(new JLabel("Trạng thái: " + this.bookingState));

        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    // Tìm kiếm Customer theo Id
    public Customer getCustomer() {
        if (this.customerId != null) {
            return Customer.getCustomerById(this.customerId);
        }
        return null;
    }

    // Tìm kiếm Tour theo Id
    // public Tour getTour() {
    // if (this.tourId != null) {
    // return Tour.getTourById(this.tourId);
    // }
    // return null;
    // }

    public String getBookingId() {
        return bookingId;
    }

    public String getTourId() {
        return tourId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public String getBookingState() {
        return bookingState;
    }
}
