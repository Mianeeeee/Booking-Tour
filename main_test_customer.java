import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class main_test_customer extends JFrame {
    public static void main(String[] args) {

// *
        String url = "jdbc:mysql://localhost:3306/customer_db_test?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String user = "root";
        String pass = "miane7755nW";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Kết nối MySQL thành công!");
            conn.close();
        } catch (Exception e) {
            System.out.println("Kết nối thất bại: " + e.getMessage());
        }
// *

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new main_test_customer();
            frame.setTitle("Quản lý khách hàng");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel content = new JPanel(new BorderLayout(10, 10));
            content.setBorder(new EmptyBorder(10, 10, 10, 10));

            JPanel form = new JPanel(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.insets = new Insets(4, 4, 4, 4);
            c.anchor = GridBagConstraints.WEST;
            c.fill = GridBagConstraints.HORIZONTAL;

            JLabel lblName = new JLabel("Name:");
            JLabel lblBirthday = new JLabel("Birthday:");
            JLabel lblPhone = new JLabel("Phone Number:");
            JLabel lblEmail = new JLabel("Email:");
            JLabel lblIdSearch = new JLabel("Search by ID:");

            JTextField txtName = new JTextField(20);
            JTextField txtBirthday = new JTextField(20);
            JTextField txtPhone = new JTextField(20);
            JTextField txtEmail = new JTextField(20);
            JTextField txtSearchId = new JTextField(20);

            c.gridx = 0;
            c.gridy = 1;
            form.add(lblName, c);
            c.gridx = 1;
            form.add(txtName, c);

            c.gridx = 0;
            c.gridy = 2;
            form.add(lblBirthday, c);
            c.gridx = 1;
            form.add(txtBirthday, c);

            c.gridx = 0;
            c.gridy = 3;
            form.add(lblPhone, c);
            c.gridx = 1;
            form.add(txtPhone, c);

            c.gridx = 0;
            c.gridy = 4;
            form.add(lblEmail, c);
            c.gridx = 1;
            form.add(txtEmail, c);

            c.gridx = 0;
            c.gridy = 5;
            form.add(lblIdSearch, c);
            c.gridx = 1;
            form.add(txtSearchId, c);

            JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JButton btnSave = new JButton("Lưu");
            JButton btnCancel = new JButton("Hủy");
            JButton btnFindId = new JButton("Tìm ID");
            JButton btnFindInfo = new JButton("Tìm thông tin");

            buttons.add(btnCancel);
            buttons.add(btnSave);
            buttons.add(btnFindId);
            buttons.add(btnFindInfo);

            content.add(form, BorderLayout.CENTER);
            content.add(buttons, BorderLayout.SOUTH);

            frame.setContentPane(content);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            btnSave.addActionListener(e -> {
                String name = txtName.getText().trim();
                String birthday = txtBirthday.getText().trim();
                String phone = txtPhone.getText().trim();
                String email = txtEmail.getText().trim();

                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Họ tên không được để trống", "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                    txtName.requestFocus();
                    return;
                }

// *
                Customer customer = new Customer(name, birthday, phone, email);
// *

                JOptionPane.showMessageDialog(frame, "Đã lưu thông tin:\n\n" + customer.toString(), "Hoàn tất",
                        JOptionPane.INFORMATION_MESSAGE);
                System.out.println(customer);
                frame.dispose();
            });

            btnFindId.addActionListener(e -> {
                String name = txtName.getText().trim();
                String birthday = txtBirthday.getText().trim();
                String phone = txtPhone.getText().trim();
                String email = txtEmail.getText().trim();

                String id = Customer.findIdByInfo(name, birthday, phone, email);
                if (id != null) {
                    JOptionPane.showMessageDialog(frame, "ID khách hàng là: " + id, "Kết quả",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Không tìm thấy khách hàng!", "Kết quả",
                            JOptionPane.WARNING_MESSAGE);
                }
            });

            btnFindInfo.addActionListener(e -> {
                String id = txtSearchId.getText().trim();
                if (id.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Vui lòng nhập ID để tìm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Customer cst = Customer.findCustomerById(id);
                if (cst != null) {
                    txtName.setText(cst.getName());
                    txtBirthday.setText(cst.getBirthday());
                    txtPhone.setText(cst.getPhoneNumber());
                    txtEmail.setText(cst.getEmail());
                    JOptionPane.showMessageDialog(frame, "Đã tải thông tin khách hàng!", "Kết quả",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Không tìm thấy khách hàng!", "Kết quả",
                            JOptionPane.WARNING_MESSAGE);
                }
            });

            btnCancel.addActionListener(e -> frame.dispose());
        });
    }
}
