package vn.funix.FX40619.asm02;


public class User {
    private String name;
    private String customerId;


    public User(String name, String customerId) {
        this.name = name;
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public String getCustomerId() {
        return customerId;
    }

}
