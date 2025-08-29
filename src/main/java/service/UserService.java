package service;

import model.User;

public interface UserService {
    User login(String username, String password);
    void insert(User user);
    boolean register(String username, String password, String email, String fullname, String phone);
    boolean checkExistEmail(String email);
    boolean checkExistUsername(String username);
    boolean checkExistPhone(String phone);
    boolean resetPassword(String username, String email, String phone, String newPassword);
}
