package vn.funix.FX40619.asm02;

import vn.funix.FX40619.asm03.Transaction;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Account {
    public static final String ACC_TYPE_SAVINGS = "SAVINGS";
    public static final String ACC_TYPE_LOAN = "LOAN";
    private String accountNumber;
    private double balance;
    private String accountType;

    private List<Transaction> transactions = new ArrayList<>();

    public Account(String accountNumber, double balance) {
        this(accountNumber,balance,ACC_TYPE_SAVINGS);
    }

    public Account(String accountNumber, double balance, String accountType) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountType = accountType;
    }




    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean validateTransaction(double amount) {
        return amount >= 10000 && amount % 10000 == 0;
    }

    public boolean isPremiumAccount() {
        return balance >= 10000000; // Nếu tài khoản có số dư >= 10 triệu VND thì được coi là Premium
    }


    // Hien thi tieu de cua lich su G/D
    public static void displayTransactionHeader() {
        System.out.println("+-------------------------+---------------------+------------+--------------------------+");
        System.out.println("|        Thời gian        |    Số tiền + Phí    |   Kết quả  |          Nội dung        |");
        System.out.println("+-------------------------+---------------------+------------+--------------------------+");
    }

    public void displaydisplayAllTransactions() {
        if (getTransactions() != null) {
            ArrayList<Transaction> transactions = new ArrayList<>(getTransactions());
            int order = 1;
            System.out.println("Lich Su GD cua tai khoan " + accountNumber + ": ");
            displayTransactionHeader();
            for (Transaction transaction : transactions) {
                System.out.println("|" + String.format("%2s", order) + transaction.toString());
                order++;
            }
            System.out.println("+-------------------------+---------------------+------------+--------------------------+");
        }
    }
    public List<Transaction> getTransactions() {
        return transactions;
    }

    public String getAccountType() {
        return accountType;
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public String toString() {
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        return formatter.format(balance) + "đ";  // Chỉ trả về số dư với dấu phân cách hàng nghìn
    }



    // Thao tác rút tiền
    public boolean withdraw(double amount) {
        if (validateTransaction(amount) && amount <= balance) {
            setBalance(getBalance() - amount); // Cập nhật số dư sau khi rút tiền
            System.out.println("Transaction successful. Withdrawn: " + amount);
            System.out.println("Remaining Balance: " + getBalance());
            return true;
        } else {
            System.out.println("Transaction failed. Invalid amount or insufficient funds.");
            return false;
        }
    }


}
