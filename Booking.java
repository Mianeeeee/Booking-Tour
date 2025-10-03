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

    public Booking(String bookingId, String tourId, String customerId, String bookingDate, String bookingState) {
        setTitle("Thông tin đặt tour");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

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

        this.bookingState = bookingState;

        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        panel.add(new JLabel("Mã đặt tour: " + this.bookingId));
        panel.add(new JLabel("Mã tour: " + this.tourId));
        panel.add(new JLabel("Mã khách hàng: " + this.customerId));
        panel.add(new JLabel("Ngày đặt: " + this.bookingDate));
        panel.add(new JLabel("Trạng thái: " + this.bookingState));

        setContentPane(panel);
        setVisible(true);

        // Tìm kiếm Customer theo Id
        if (customerId != null) {
            Customer customer = Customer.getCustomerById(customerId);
        }

        // Tìm kiếm Tour theo Id
        // if (tourId != null) {
        // Tour tour = Tour.getTourById(tourId);
        // }
    }

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
