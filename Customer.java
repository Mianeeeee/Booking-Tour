import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import java.util.HashMap;
import java.util.Map;

public class Customer extends JFrame {
    private static int nextId = 1;
    private static Map<String, Customer> customersById = new HashMap<>();
    private String Id;
    private String Name;
    private String Birthday;
    private String phoneNumber;
    private String Email;

    public Customer(String Id, String Name, String Birthday, String phoneNumber, String Email) {
        setTitle("Thông tin khách hàng");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Tạo id tự động tăng dần bắt đầu từ KH001 nếu Id không được truyền vào
        this.Id = String.format("KH%03d", Customer.nextId++);
        this.Name = Name;

        // Chuẩn hóa Birthday theo định dạng dd/mm/yyyy
        if (Birthday != null && Birthday.matches("\\d{2}/\\d{2}/\\d{4}")) {
            this.Birthday = Birthday;
        } else {
            // Nếu không đúng định dạng, gán giá trị rỗng hoặc xử lý khác tùy ý
            this.Birthday = "";
        }

        this.phoneNumber = phoneNumber;
        this.Email = Email;

        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        panel.add(new JLabel("Mã khách hàng: " + this.Id));
        panel.add(new JLabel("Họ và tên: " + this.Name));
        panel.add(new JLabel("Ngày sinh: " + this.Birthday));
        panel.add(new JLabel("Số điện thoại: " + this.phoneNumber));
        panel.add(new JLabel("Email: " + this.Email));

        setContentPane(panel);
        setVisible(true);

        // Lưu Customer dựa theo Id
        Customer.customersById.put(this.Id, this);
    }

    // public void showCustomerInfo() {
    // JOptionPane.showMessageDialog(this,
    // "Mã khách hàng: " + this.Id + "\n" +
    // "Họ và tên: " + this.Name + "\n" +
    // "Ngày sinh: " + this.Birthday + "\n" +
    // "Số điện thoại: " + this.phoneNumber + "\n" +
    // "Email: " + this.Email,
    // "Thông tin khách hàng",
    // JOptionPane.INFORMATION_MESSAGE);
    // }

    // Tìm kiếm Customer theo Id
    public static Customer getCustomerById(String id) {
        return customersById.get(id);
    }

    public String getId() {
        return this.Id;
    }

    public String getName() {
        return this.Name;
    }

    public String getBirthday() {
        return this.Birthday;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getEmail() {
        return this.Email;
    }
}
