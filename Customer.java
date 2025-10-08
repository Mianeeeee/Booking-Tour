import java.sql.*;

public class Customer {
    private static final String URL = "jdbc:mysql://localhost:3306/customer_db_test?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "miane7755nW";

    private String Id;
    private String Name;
    private String Birthday;
    private String Phone_Number;
    private String Email;

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private static String generateNextId(Connection conn) throws SQLException {
        String sql = "SELECT MAX(id) AS max_id FROM customers";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            if (rs.next() && rs.getString("max_id") != null) {
                String lastId = rs.getString("max_id");
                int num = Integer.parseInt(lastId.substring(2));
                return String.format("KH%03d", num + 1);
            } else {
                return "KH001";
            }
        }
    }

    private static String normalizeBirthday(String birthday) {
        if (birthday == null || birthday.isEmpty()) {
            return null;
        }

        birthday = birthday.trim().replace("/", "-");

        if (birthday.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return birthday;
        }

        if (birthday.matches("\\d{2}-\\d{2}-\\d{4}")) {
            String[] parts = birthday.split("-");
            return parts[2] + "-" + parts[1] + "-" + parts[0];
        }

        if (birthday.matches("\\d{8}")) {
            String day = birthday.substring(0, 2);
            String month = birthday.substring(2, 4);
            String year = birthday.substring(4);
            return year + "-" + month + "-" + day;
        }

        return null;
    }

    public Customer(String Name, String Birthday, String Phone_Number, String Email) {
        this.Name = Name;

        this.Birthday = normalizeBirthday(Birthday);

        this.Phone_Number = Phone_Number;

        this.Email = Email;

        String insertSql = "INSERT INTO customer_db_test.customers (id, name, birthday, phoneNumber, email) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(insertSql)) {

            this.Id = generateNextId(conn);

            stmt.setString(1, this.Id);
            stmt.setString(2, this.Name);
            stmt.setString(3, this.Birthday);
            stmt.setString(4, this.Phone_Number);
            stmt.setString(5, this.Email);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Thêm khách hàng thành công.");
            } else {
                System.out.println("Không thể thêm khách hàng.");
            }

        } catch (SQLException e) {
            System.out.println("Lỗi khi thêm khách hàng: " + e.getMessage());
        }
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
        return this.Phone_Number;
    }

    public String getEmail() {
        return this.Email;
    }
}
