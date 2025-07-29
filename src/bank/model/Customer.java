package bank.model;

public class Customer {
    private String uuid;
    private String fullName;
    private String gender;
    private String email;
    private String phone;
    private Boolean isDeleted;

    public Customer() {}

    public Customer(String uuid, String fullName, String gender, String email, String phone, Boolean isDeleted){
        this.uuid = uuid;
        this.fullName = fullName;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.isDeleted = isDeleted;
    }

    public String getUUID() {
        return uuid;
    }

}
