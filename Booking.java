public class Booking {
    private String bookingId;
    private String tourId;
    private String customerId;
    private String bookingDate;
    private String bookingState;

    public Booking(String bookingId, String tourId, String customerId, String bookingDate, String bookingState) {
        this.bookingId = bookingId;
        this.tourId = tourId;
        this.customerId = customerId;
        this.bookingDate = bookingDate;
        this.bookingState = bookingState;
    }

    public Booking(String tourId, String customerId, String bookingDate, String bookingState) {
        this.tourId = tourId;
        this.customerId = customerId;
        this.bookingDate = bookingDate;
        this.bookingState = bookingState;
    }

    public String getBookingId() {
        return this.bookingId;
    }

    public String getTourId() {
        return this.tourId;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public String getBookingDate() {
        return this.bookingDate;
    }

    public String getBookingState() {
        return this.bookingState;
    }

    public void setId(String bookingId) {
        this.bookingId = bookingId;
    }

    public void setBookingState(String bookingState) {
        this.bookingState = bookingState;
    }

    @Override
    public String toString() {
        return "Mã đặt tour: " + this.bookingId + "\nMã tour: " + this.tourId + "\nMã khách hàng: " + this.customerId
                + "\nNgày đặt: " + this.bookingDate + "\nTrạng thái: " + this.bookingState;
    }
}
