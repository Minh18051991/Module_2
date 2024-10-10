package game_shop_management.controller;

import game_shop_management.model.UserModel;

public class AuthController {
    public boolean login(String username, String password) {
        // Kiểm tra thông tin đăng nhập
        return UserModel.checkCredentials(username, password);
    }

    public boolean register(String username, String password, String email) {
        // Kiểm tra xem tên đăng nhập đã tồn tại chưa
        if (UserModel.usernameExists(username)) {
            return false; // Tên đăng nhập đã tồn tại
        }
        // Nếu chưa tồn tại, tạo tài khoản mới
        UserModel.createAccount(username, password, email);
        return true;
    }
}