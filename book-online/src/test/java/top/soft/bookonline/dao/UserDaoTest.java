package top.soft.bookonline.dao;

import org.junit.jupiter.api.Test;
import top.soft.bookonline.dao.impl.UserDaoImpl;
import top.soft.bookonline.entity.User;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    @Test
    void insertUser() {
        UserDao userDao= new UserDaoImpl() {
            @Override
            public User signIn(String account, String password) {
                return null;
            }

            @Override
            public void signUp(String account, String password) {

            }

            @Override
            public boolean register(User user) throws SQLException {
                return false;
            }
        };
        User user=User.builder()
                .account("lyc")
                .nickname("lyc").password("123456").address("江苏南京").avatar("1.png").build();
        userDao.insertUser(user);
    }
}