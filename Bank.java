package vn.funix.FX40619.asm02;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Customer> customers;

    // Constructor
    public Bank() {
        customers = new ArrayList<>();
    }

    // Thêm khách hàng vào ngân hàng
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    // Kiểm tra khách hàng có tồn tại không theo CCCD
    public Customer getCustomerById(String customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }

    // Kiểm tra tài khoản đã tồn tại hay chưa
    public boolean isAccountExisted(String accountNumber) {
        for (Customer customer : customers) {
            for (Account account : customer.getAccounts()) {
                if (account.getAccountNumber().equals(accountNumber)) {
                    return true;
                }
            }
        }
        return false;
    }

    // them tai khoan neu khach hang co trong danh sach
    public void addAccount(String CCCD, Account account) {
        for (int i = 0; i < customers.size(); i++) {
            if (CCCD.equals(customers.get(i).getCustomerId())) {
                customers.get(i).addAccount(account);
                return;
            }
        }
        System.out.println("Khach hang khong ton tai");
    }

    // Hiển thị danh sách tất cả khách hàng
    public void displayAllCustomers() {
        System.out.println("List of Customers:");
        for (Customer customer : customers) {
            customer.displayInformation();
        }
    }

    // Tìm khách hàng theo CCCD
    public Customer findCustomerById(String cccd) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(cccd)) {
                return customer;
            }
        }
        return null; // Nếu không tìm thấy khách hàng
    }

    // Tìm khách hàng theo tên gần đúng
    public List<Customer> findCustomersByName(String nameKeyword) {
        List<Customer> result = new ArrayList<>();
        for (Customer customer : customers) {
            if (customer.getName().toLowerCase().contains(nameKeyword.toLowerCase())) {
                result.add(customer);
            }
        }
        return result;
    }
}
