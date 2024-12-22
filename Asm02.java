package vn.funix.FX40619.asm02;

import vn.funix.FX40619.asm01.Asm01;

import java.util.List;
import java.util.Scanner;

public class Asm02 {
    public static void main(String[] args) {
        Bank bank = new Bank(); // Tạo đối tượng ngân hàng
        Scanner scanner = new Scanner(System.in); // Khởi tạo Scanner để đọc đầu vào từ người dùng
        int choice; // Biến để lưu lựa chọn của người dùng

        do {
            // Hiển thị menu cho người dùng chọn
            System.out.println("""
              +--------------+--------------+-----------+
              | NGAN HANG SO  | FX40619@v2.0.0          |
              +--------------+--------------+-----------+
               1. Them khach hang
               2. Them tai khoan cho khach hang
               3. Hien thi danh sach khach hang
               4. Tim theo CCCD
               5. Tim theo ten khach hang
               0. Thoat
              +--------------+--------------+-----------+
              """);
            System.out.print("Chon chuc nang: ");

            // Kiểm tra nếu đầu vào của người dùng là một số nguyên, nếu không thì yêu cầu nhập lại
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Đọc và bỏ qua đầu vào không hợp lệ
            }
            choice = scanner.nextInt(); // Lấy lựa chọn của người dùng
            scanner.nextLine(); // Đọc bỏ dòng mới sau khi nhập số

            switch (choice) {
                case 1:
                    System.out.println("+-----------------------------------------+");
                    addCustomer(bank, scanner); // Gọi hàm thêm khách hàng
                    break;
                case 2:
                    addAccountForCustomer(bank, scanner); // Gọi hàm thêm tài khoản cho khách hàng
                    break;
                case 3:
                    bank.displayAllCustomers(); // Hiển thị danh sách tất cả khách hàng
                    break;
                case 4:
                    searchCustomerByCCCD(bank, scanner); // Tìm kiếm khách hàng theo CCCD
                    break;
                case 5:
                    searchCustomerByName(bank, scanner); // Tìm kiếm khách hàng theo tên
                    break;
                case 0:
                    System.out.println("Exiting..."); // Thoát chương trình
                    break;
                default:
                    System.out.println("Invalid choice. Try again."); // Xử lý nếu lựa chọn không hợp lệ
                    break;
            }
        } while (choice != 0); // Lặp lại cho đến khi người dùng chọn thoát

        scanner.close(); // Đóng Scanner để giải phóng tài nguyên
    }

    // Thêm khách hàng vào ngân hàng
    private static void addCustomer(Bank bank, Scanner scanner) {
        System.out.print("Nhap ten khach hang: ");
        String name = scanner.nextLine().trim(); // Nhập tên khách hàng và loại bỏ khoảng trắng thừa

        // Kiểm tra tên khách hàng chỉ chứa các ký tự chữ cái và không rỗng
        while (name.isEmpty() || !name.matches("[a-zA-Z ]+")) {
            System.out.println("Invalid name. Please enter a valid name containing only letters.");
            name = scanner.nextLine().trim();
        }

        String cccd;
        while (true) {
            System.out.print("Nhap so CCCD: ");
            cccd = scanner.nextLine().trim(); // Nhập số CCCD và loại bỏ khoảng trắng thừa
            if (cccd.equalsIgnoreCase("No")) {
                System.out.println("You chose to exit.");
                return;
            }
            // Kiểm tra tính hợp lệ của CCCD
            if (!Asm01.isValidCCCD(cccd)) {
                System.out.println("So CCCD khong hop le,\nVui long nhap lai hoac nhap 'No' de thoat:");
            } else {
                break; // Thoát vòng lặp nếu CCCD hợp lệ
            }
        }

        // Kiểm tra nếu khách hàng đã tồn tại trong ngân hàng
        if (bank.getCustomerById(cccd) == null) {
            Customer customer = new Customer(name, cccd); // Tạo đối tượng khách hàng mới
            bank.addCustomer(customer); // Thêm khách hàng vào ngân hàng
            System.out.println("Da them khach hang "+cccd+" vao danh sach");
        } else {
            System.out.println("Customer with this CCCD already exists."); // Thông báo nếu khách hàng đã tồn tại
        }
    }

    // Thêm tài khoản cho khách hàng
    private static void addAccountForCustomer(Bank bank, Scanner scanner) {
        System.out.print("Nhap CCCD khach hang: ");
        String cccd = scanner.nextLine().trim(); // Nhập CCCD khách hàng và loại bỏ khoảng trắng thừa

        // Kiểm tra nếu khách hàng tồn tại hay không
        Customer customer = bank.getCustomerById(cccd);
        if (customer == null) {
            System.out.println("Customer with this CCCD does not exist. Please try again.");
            return;
        }

        String accountNumber;
        while (true) {
            System.out.print("Nhap ma STK gom 6 chu so: ");
            accountNumber = scanner.nextLine().trim(); // Nhập số tài khoản và loại bỏ khoảng trắng thừa
            // Kiểm tra nếu số tài khoản hợp lệ và chưa tồn tại
            if (accountNumber.length() != 6 || !accountNumber.matches("\\d{6}")) {
                System.out.println("Account number must be 6 digits. Please try again.");
            } else if (bank.isAccountExisted(accountNumber)) {
                System.out.println("Account number already exists. Please try again.");
            } else {
                break; // Thoát vòng lặp nếu số tài khoản hợp lệ
            }
        }

        double balance;
        while (true) {
            System.out.print("Nhap so du: ");
            while (!scanner.hasNextDouble()) { // Kiểm tra đầu vào là số thực
                System.out.println("Invalid input. Please enter a valid number for the balance.");
                scanner.next(); // Đọc và bỏ qua đầu vào không hợp lệ
            }
            balance = scanner.nextDouble();
            if (balance < 50000) { // Kiểm tra số dư phải lớn hơn hoặc bằng 50,000 VND
                System.out.println("Balance must be greater than or equal to 50,000 VND. Please try again.");
            } else {
                break; // Thoát vòng lặp nếu số dư hợp lệ
            }
        }

        // Tạo và thêm tài khoản mới vào khách hàng
        Account newAccount = new Account(accountNumber, balance);
        customer.addAccount(newAccount);
        System.out.println("Account added successfully.");
    }

    // Tìm kiếm khách hàng theo CCCD
    private static void searchCustomerByCCCD(Bank bank, Scanner scanner) {
        System.out.print("Enter customer CCCD to search: ");
        String cccd = scanner.nextLine().trim(); // Nhập CCCD và loại bỏ khoảng trắng thừa

        // Tìm kiếm khách hàng theo CCCD
        Customer customer = bank.findCustomerById(cccd);
        if (customer != null) {
            customer.displayInformation(); // Hiển thị thông tin khách hàng nếu tìm thấy
        } else {
            System.out.println("Customer with CCCD " + cccd + " not found."); // Thông báo nếu không tìm thấy khách hàng
        }
    }

    // Tìm kiếm khách hàng theo tên
    private static void searchCustomerByName(Bank bank, Scanner scanner) {
        System.out.print("Enter customer name to search: ");
        String nameKeyword = scanner.nextLine().trim(); // Nhập từ khóa tên và loại bỏ khoảng trắng thừa

        // Kiểm tra tên chỉ chứa các ký tự chữ cái và khoảng trắng
        while (!nameKeyword.matches("[a-zA-Z ]*")) {
            System.out.println("Invalid name. Please enter a name containing only letters.");
            nameKeyword = scanner.nextLine().trim();
        }

        List<Customer> customers = bank.findCustomersByName(nameKeyword);
        if (customers.isEmpty()) {
            System.out.println("No customers found with name containing: " + nameKeyword);
        } else {
            // Hiển thị thông tin của tất cả khách hàng tìm thấy
            for (Customer customer : customers) {
                customer.displayInformation();
            }
        }
    }
}
