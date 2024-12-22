package vn.funix.FX40619.asm02;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private List<Account> accounts;



    // Constructor
    public Customer(String name, String customerId) {
        super(name, customerId);
        this.accounts = new ArrayList<>();
    }



    public List<Account> getAccounts() {
        return accounts;
    }



    // Thêm tài khoản cho khách hàng
    public void addAccount(Account newAccount) {
        for (int i = 0; i < accounts.size(); i++) {
            if (newAccount.getAccountNumber().equals(accounts.get(i).getAccountNumber())) {
                System.out.println("Account number is duplicated");
                return;
            }
        }
        accounts.add(newAccount);
        System.out.println("Them tai khoan thanh cong!!");
    }


    // Kiểm tra xem khách hàng có tài khoản Premium không
    public boolean isPremiumAccount() {
        for (Account account : accounts) {
            if (account.isPremiumAccount()) {
                return true;
            }
        }
        return false;
    }

    // Tính tổng số dư của khách hàng
    public double getTotalBalance() {
        double totalBalance = 0;
        for (Account account : accounts) {
            totalBalance += account.getBalance();
        }
        return totalBalance;
    }

    // Lay account co ton tai cua khach hang
    public Account getAccountByAccountNumber(String accountNumer) {
        for (Account acc : accounts) {
            // Kiem tra account co ton tai hay khong
            if (acc.getAccountNumber().equals(accountNumer)) {
                return acc;
            }
        }
        return null;
    }



    public void displayInformation() {
        String isPre = "normal";
        if (isPremiumAccount()) {
            isPre = "Premium";
        }

        // Tạo đối tượng DecimalFormat để định dạng số với dấu phân cách hàng nghìn
        DecimalFormat formatter = new DecimalFormat("#,###,###");

        // Định dạng số dư của khách hàng
        String formattedBalance = formatter.format(getTotalBalance());

        // In thông tin khách hàng
        System.out.printf("%-15s | %-20s | %-15s | %sđ\n", getCustomerId(), getName(), isPre, formattedBalance);

        // Duyệt qua các tài khoản của khách hàng và in thông tin tài khoản
        for (int i = 0; i < this.accounts.size(); i++) {
            // In thông tin tài khoản với số dư đã được định dạng từ phương thức toString() của Account
            System.out.printf("%-15s | %-20s | %s\n", i + 1, this.accounts.get(i).getAccountNumber(), this.accounts.get(i).toString());
        }
    }
}



