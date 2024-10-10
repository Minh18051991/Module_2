package bai_thi_module_2.view;

import bai_thi_module_2.controller.MobileController;
import bai_thi_module_2.model.AuthMobile;
import bai_thi_module_2.model.ImportedMobile;
import bai_thi_module_2.model.Mobile;

import java.util.List;
import java.util.Scanner;

public class MobileView {
    private MobileController mobileController;
    private Scanner scanner;

    public MobileView(MobileController mobileController) {
        this.mobileController = mobileController;
        this.scanner = new Scanner(System.in);
    }

    public void displayAllMobiles() {
        List<Mobile> mobiles = mobileController.getAllMobiles();
        if (mobiles.isEmpty()) {
            System.out.println("Không có điện thoại nào trong danh sách.");
            return;
        }
        for (Mobile mobile : mobiles) {
            System.out.println(mobile);
        }
    }

    public void addMobile() {
        System.out.print("Nhập ID: ");
        String id = scanner.nextLine();
        System.out.print("Nhập Tên: ");
        String name = scanner.nextLine();
        System.out.print("Nhập Giá: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("Nhập Số lượng: ");
        int stock = Integer.parseInt(scanner.nextLine());
        System.out.print("Nhập Nhà sản xuất: ");
        String manufacturer = scanner.nextLine();

        System.out.print("Loại điện thoại (1: AuthMobile, 2: ImportedMobile): ");
        int type = Integer.parseInt(scanner.nextLine());

        Mobile mobile;
        if (type == 1) {
            System.out.print("Nhập Thời gian bảo hành: ");
            String warrantyPeriod = scanner.nextLine();
            System.out.print("Nhập Phạm vi bảo hành: ");
            String warrantyCoverage = scanner.nextLine();
            mobile = new AuthMobile(id, name, price, stock, manufacturer, warrantyPeriod, warrantyCoverage);
        } else {
            System.out.print("Nhập Quốc gia nhập khẩu: ");
            String importedCountry = scanner.nextLine();
            System.out.print("Đã sửa chữa (true/false): ");
            boolean isRepaired = Boolean.parseBoolean(scanner.nextLine());
            mobile = new ImportedMobile(id, name, price, stock, manufacturer, importedCountry, isRepaired);
        }

        try {
            mobileController.addMobile(mobile);
            System.out.println("Điện thoại đã được thêm thành công.");
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    public void searchMobile() {
        System.out.print("Nhập ID điện thoại cần tìm: ");
        String id = scanner.nextLine();
        Mobile mobile = mobileController.getMobileById(id);
        if (mobile == null) {
            System.out.println("Không tìm thấy điện thoại với ID: " + id);
            return;
        }
        System.out.println(mobile);
    }

    public void updateMobile() {
        System.out.print("Nhập ID điện thoại cần cập nhật: ");
        String id = scanner.nextLine();
        Mobile existingMobile = mobileController.getMobileById(id);
        if (existingMobile == null) {
            System.out.println("Không tìm thấy điện thoại với ID: " + id);
            return;
        }

        System.out.print("Nhập Tên mới (hoặc nhấn Enter để giữ nguyên): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            existingMobile.setName(name);
        }

        System.out.print("Nhập Giá mới (hoặc nhấn Enter để giữ nguyên): ");
        String priceInput = scanner.nextLine();
        if (!priceInput.isEmpty()) {
            existingMobile.setPrice(Double.parseDouble(priceInput));
        }

        System.out.print("Nhập Số lượng mới (hoặc nhấn Enter để giữ nguyên): ");
        String stockInput = scanner.nextLine();
        if (!stockInput.isEmpty()) {
            existingMobile.setStock(Integer.parseInt(stockInput));
        }

        try {
            mobileController.updateMobile(id, existingMobile);
            System.out.println("Điện thoại đã được cập nhật thành công.");
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    public void deleteMobile() {
        System.out.print("Nhập vào ID cần xóa: ");
        String id = scanner.nextLine();
        System.out.println("Bạn có chắc muốn xóa điện thoại có ID: " + id + "? (yes/no)");

        String choice = scanner.nextLine().trim().toLowerCase();

        switch (choice) {
            case "yes":
                mobileController.deleteMobile(id);
                System.out.println("Điện thoại đã xóa thành công.");
                break;
            case "no":
                System.out.println("Bạn đã chọn không xóa điện thoại này.");
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ.");
        }
    }

    public void run() {
        while (true) {
            System.out.println("\n--- Quản lý điện thoại ---");
            System.out.println("1. Hiển thị tất cả điện thoại");
            System.out.println("2. Thêm điện thoại");
            System.out.println("3. Cập nhật điện thoại");
            System.out.println("4. Xóa điện thoại");
            System.out.println("5. Tìm kiếm ");
            System.out.println("0. Thoát");
            System.out.print("Chọn một tùy chọn: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    displayAllMobiles();
                    break;
                case 2:
                    addMobile();
                    break;
                case 3:
                    updateMobile();
                    break;
                case 4:
                    try {
                        deleteMobile();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 5:
                    searchMobile();
                case 0:
                    System.out.println("Thoát chương trình.");
                    return;
                default:
                    System.out.println("Tùy chọn không hợp lệ. Vui lòng thử lại.");
            }
        }
    }
}