import java.math.BigDecimal;
import java.sql.*;
import java.time.format.*;
import java.time.LocalDate;

public class CustomerDB {
    private static final String URL = "jdbc:mysql://localhost:3306/booking_tour_test?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "miane7755nW";

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static String generateNextId() {
        try (Connection conn = getConnection()) {
            String sql = "SELECT MAX(id) AS max_id FROM customer";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            String nextId;
            if (rs.next() && rs.getString("max_id") != null) {
                String lastId = rs.getString("max_id");
                int num = Integer.parseInt(lastId.substring(2));
                nextId = String.format("KH%03d", num + 1);
            } else {
                nextId = "KH001";
            }

            rs.close();
            stmt.close();
            return nextId;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String normalizeDate(String date) {
        if (date == null || date.trim().isEmpty())
            return null;

        date = date.trim().replace("/", "-");

        String[] patterns = {
                "yyyy-MM-dd",
                "dd-MM-yyyy",
                "d-M-yyyy",
                "ddMMyyyy",
                "dMyyyy"
        };

        for (String pattern : patterns) {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(pattern);
            LocalDate res = LocalDate.parse(date, inputFormatter);
            return res.format(DateTimeFormatter.ISO_LOCAL_DATE);
        }
        return null;
    }

    public static String findTourId(String name, String date) {
        String tourId = null;
        try (Connection conn = getConnection()) {
            String sql = "SELECT tourId FROM tour WHERE tourName = ? AND dayStart = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, normalizeDate(date));

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                tourId = rs.getString("tourId");
            } else {
                System.out.println("Không tìm thấy tour phù hợp.");
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi truy vấn findTourId: " + e.getMessage());
        }
        return tourId;
    }

    public static BigDecimal findPrice(String Id) {
        BigDecimal price = null;
        try (Connection conn = getConnection()) {
            String sql = "SELECT price FROM tour WHERE tourId = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, Id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                price = rs.getBigDecimal("price");
            } else {
                System.out.println("Không tìm thấy tour phù hợp.");
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi truy vấn findPrice: " + e.getMessage());
        }
        return price;
    }

    public static void setBookingState(String tourId) {
        try (Connection conn = getConnection()) {
            String sql = "UPDATE customer SET bookingState = 'Confirmed' WHERE tourId = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, tourId);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Thay đổi giá trị thành công!");
            } else {
                System.out.println("Không tìm thấy tour phù hợp.");
            }

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi khi cập nhật bookingState: " + e.getMessage());
        }
    }

    public static void addCustomer(Customer cst) {
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO customer (id, name, birthday, phoneNumber, email, tourBookings, " +
                    "bookingState, bookingDate, numberOfCustomers, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, cst.getId());
            stmt.setString(2, cst.getName());
            stmt.setString(3, normalizeDate(cst.getBirthDay()));
            stmt.setString(4, cst.getPhoneNumber());
            stmt.setString(5, cst.getEmail());
            stmt.setString(6, cst.getTourBookings());
            stmt.setString(7, cst.getBookingState());
            stmt.setString(8, normalizeDate(cst.getBookingDate()));
            stmt.setInt(9, cst.getNumberOfCustomers());
            stmt.setBigDecimal(10, cst.getPrice());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Thêm khách hàng thành công!");
            } else {
                System.out.println("Không thể thêm khách hàng.");
            }

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi khi thêm khách hàng: " + e.getMessage());
        }
    }

    public static void deleteCustomerById(String id) {
        try (Connection conn = getConnection()) {
            String sql = "DELETE FROM customer WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Xóa khách hàng thành công!");
            } else {
                System.out.println("Không tìm thấy khách hàng với ID: " + id);
            }

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi khi xóa khách hàng: " + e.getMessage());
        }
    }
}

