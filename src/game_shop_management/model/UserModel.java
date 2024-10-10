package game_shop_management.model;

import game_shop_management.util.FileHandler;

import java.util.ArrayList;
import java.util.List;

public class UserModel {
    private static final String FILE_PATH = "src/game_shop_management/data/Users.csv"; // Đường dẫn tệp CSV
    private static List<User> users = new ArrayList<>();

    // Đọc tệp CSV khi khởi động ứng dụng
    static {
        loadUsers();
    }

    // Phương thức để tạo tài khoản mới
    public static boolean createAccount(String username, String password, String email) {
        if (usernameExists(username)) {
            return false;
        }
        users.add(new User(username, password, email));
        saveUsers(); // Ghi lại vào tệp CSV
        return true;
    }

    // Kiểm tra thông tin đăng nhập
    public static boolean checkCredentials(String username, String password) {
        return users.stream()
                .filter(user -> user != null) // Lọc bỏ các giá trị null
                .anyMatch(user -> user.getUsername().equals(username) && user.getPassword().equals(password));
    }

    // Kiểm tra xem tên đăng nhập có tồn tại không
    public static boolean usernameExists(String username) {
        return users.stream()
                .filter(user -> user != null) // Lọc bỏ các giá trị null
                .anyMatch(user -> user.getUsername().equals(username));
    }

    // Tải người dùng từ tệp CSV
    private static void loadUsers() {
        users = FileHandler.loadFromFile(FILE_PATH, line -> {
            String[] fields = line.split(",");
            if (fields.length == 3) {
                // Kiểm tra dữ liệu không rỗng
                if (!fields[0].trim().isEmpty() && !fields[1].trim().isEmpty() && !fields[2].trim().isEmpty()) {
                    return new User(fields[0].trim(), fields[1].trim(), fields[2].trim());
                }
            }
            return null; // Trả về null nếu dữ liệu không hợp lệ
        });

        // Lọc bỏ các giá trị null
        users.removeIf(user -> user == null);
    }

    // Ghi người dùng vào tệp CSV
    private static void saveUsers() {
        FileHandler.saveToFile(users, FILE_PATH, "username,password,email");
    }

    // Lớp nội bộ để lưu thông tin người dùng
    public static class User {
        private String username;
        private String password;
        private String email;

        public User(String username, String password, String email) {
            this.username = username;
            this.password = password;
            this.email = email;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public String getEmail() {
            return email;
        }
        public String toCSV() {
            return String.format("%s,%s,%s", username, password, email);
        }
    }
}