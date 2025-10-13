import java.math.BigDecimal;
import java.sql.*;
import java.time.format.*;
import java.time.LocalDate;

public class CustomerDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/booking_tour_test?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "miane7755nW";

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Chuẩn hóa ngày
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
            try {
                DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(pattern);
                LocalDate res = LocalDate.parse(date, inputFormatter);
                return res.format(DateTimeFormatter.ISO_LOCAL_DATE);
            } catch (Exception ignore) {
            }
        }

        System.err.println("Không thể parse ngày: " + date);
        return null;
    }

    // Tìm tourId khi biết tourName và dayStart và numberOfDays
    public static String findTourId(String tourName, String dayStart, double numberOfDay) {
        String tourId = null;
        try (Connection conn = getConnection()) {
            String sql = "SELECT tourId FROM tour WHERE tourName = ? AND dayStart = ? AND numberOfDays = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, tourName);
            stmt.setString(2, normalizeDate(dayStart));
            stmt.setDouble(3, numberOfDay);

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

    public static void addCustomer(Customer cst) {
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO customer (name, birthday, phoneNumber, email, tourBooking, " +
                    "bookingState, bookingDate, numberOfCustomers, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, cst.getName());
            stmt.setString(2, normalizeDate(cst.getBirthDay()));
            stmt.setString(3, cst.getPhoneNumber());
            stmt.setString(4, cst.getEmail());
            stmt.setString(5, cst.getTourBooking());
            stmt.setString(6, cst.getBookingState());
            stmt.setString(7, normalizeDate(cst.getBookingDate()));
            stmt.setInt(8, cst.getNumberOfCustomers());
            stmt.setBigDecimal(9, cst.getPrice());

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
}
