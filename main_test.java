import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class main_test_customer extends JFrame {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/customer_db_test?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String user = "root";
        String pass = "miane7755nW";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(url, user, pass);
            System.out.println("✅ Kết nối MySQL thành công!");
            conn.close();
        } catch (Exception e) {
            System.out.println("❌ Kết nối thất bại: " + e.getMessage());
        }

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new main_test_customer();
            frame.setTitle("Nhập thông tin Customer");
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

            JTextField txtName = new JTextField(20);
            JTextField txtBirthday = new JTextField(20);
            JTextField txtPhone = new JTextField(20);
            JTextField txtEmail = new JTextField(20);

            c.gridx = 0;
            c.gridy = 1;
            c.weightx = 0;
            form.add(lblName, c);
            c.gridx = 1;
            c.weightx = 1.0;
            form.add(txtName, c);

            c.gridx = 0;
            c.gridy = 2;
            c.weightx = 0;
            form.add(lblBirthday, c);
            c.gridx = 1;
            c.weightx = 1.0;
            form.add(txtBirthday, c);

            c.gridx = 0;
            c.gridy = 3;
            c.weightx = 0;
            form.add(lblPhone, c);
            c.gridx = 1;
            c.weightx = 1.0;
            form.add(txtPhone, c);

            c.gridx = 0;
            c.gridy = 4;
            c.weightx = 0;
            form.add(lblEmail, c);
            c.gridx = 1;
            c.weightx = 1.0;
            form.add(txtEmail, c);

            JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JButton btnSave = new JButton("Lưu");
            JButton btnCancel = new JButton("Hủy");
            buttons.add(btnCancel);
            buttons.add(btnSave);

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

                Customer customer = new Customer(name, birthday, phone, email);
                JOptionPane.showMessageDialog(frame, "Đã lưu thông tin:\n\n" + customer.toString(), "Hoàn tất",
                        JOptionPane.INFORMATION_MESSAGE);
                System.out.println(customer);
                frame.dispose();
            });

            btnCancel.addActionListener(e -> frame.dispose());
        });
    }
}
