public class main_test {
    public static void main(String[] args) {
        Customer customer = new Customer(null, "Nguyen Van A", "15/08/1990", "0123456789", "nguyenvana@example.com");
        customer.CustomerDisplay();

        Booking booking = new Booking("", "T001", customer.getId(), "15/08/2023", "Đã xác nhận");
        booking.showBookingDisplay();
    }
}
