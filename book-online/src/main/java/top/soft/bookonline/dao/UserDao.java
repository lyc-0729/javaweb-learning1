package top.soft.bookonline.dao;
import top.soft.bookonline.entity.User;
import java.sql.SQLException;

/**
 * @parm user
 * @return
 */
public interface UserDao {
    void insertUser(User user);

    int User(User user);

    User findUser(User user);

    void addUser(User user) throws SQLException;

    User selectByEmailPassword(String ue, String password) throws SQLException;

    boolean isEmailRegistered(long email);

    User findUserByAccount(String account);

    User findUserByAccountAndPassword(String account, String password);
}