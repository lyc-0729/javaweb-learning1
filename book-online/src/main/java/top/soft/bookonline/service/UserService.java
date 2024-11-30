package top.soft.bookonline.service;

import top.soft.bookonline.entity.User;

import java.sql.SQLException;

public interface UserService {
    User signIn(String account, String password);

    void signUp(String account, String password);

    boolean register(User user) throws SQLException;
}
