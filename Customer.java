// Chỉ chứa dữ liệu khách hàng
public class Customer {

    private String Id;
    private String Name;
    private String Birthday;
    private String Phone_Number;
    private String Email;

    public Customer(String Id, String Name, String Birthday, String Phone_Number, String Email) {
        this.Id = Id;
        this.Name = Name;
        this.Birthday = Birthday;
        this.Phone_Number = Phone_Number;
        this.Email = Email;
    }

    public Customer(String Name, String Birthday, String Phone_Number, String Email) {
        this.Name = Name;
        this.Birthday = Birthday;
        this.Phone_Number = Phone_Number;
        this.Email = Email;
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

    @Override
    public String toString() {
        return "ID: " + this.Id + "\nHọ tên: " + this.Name + "\nNgày sinh: " + this.Birthday + "\nSố điện thoại: "
                + this.Phone_Number + "\nEmail: " + this.Email;
    }
}
