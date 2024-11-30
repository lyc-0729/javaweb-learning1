package top.soft.bookonline.dao.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import top.soft.bookonline.dao.UserDao;
import top.soft.bookonline.entity.User;
import top.soft.bookonline.util.JdbcUtil;

import java.sql.SQLException;

public abstract class UserDaoImpl implements UserDao {
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate(JdbcUtil.getDataSource());

    @Override
    public void insertUser(User user) {
        // 在插入之前检查账号是否已存在
        if (findUserByAccount(user.getAccount()) != null) {
            throw new RuntimeException("账号已存在，请使用其他账号注册");
        }

        // 如果账号不存在，则执行插入操作
        String sql = "INSERT INTO t_user (account, password, nickname, avatar) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getAccount(), user.getPassword(), user.getNickname(), user.getAvatar());
    }

    @Override
    public int User(User user) {
        return 0;
    }

    @Override
    public User findUser(User userDto) {
        return findUserByAccountAndPassword(userDto.getAccount(), userDto.getPassword());
    }

    @Override
    public void addUser(User user) throws SQLException {

    }

    @Override
    public User selectByEmailPassword(String ue, String password) throws SQLException {
        return null;
    }

    @Override
    public boolean isEmailRegistered(long email) {
        return false;
    }

    @Override
    public User findUserByAccount(String account) {
        try {
            String sql = "SELECT * FROM t_user WHERE account = ?";
            return jdbcTemplate.queryForObject(
                    sql,
                    new BeanPropertyRowMapper<>(User.class),
                    account
            );
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null; // 如果查询不到结果，返回 null
        }
    }

    @Override
    public User findUserByAccountAndPassword(String account, String password) {
        try {
            String sql = "SELECT * FROM t_user WHERE account = ? AND password = ?";
            return jdbcTemplate.queryForObject(
                    sql,
                    new BeanPropertyRowMapper<>(User.class),
                    account,
                    password
            );
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null; // 如果查询不到结果，返回 null
        }
    }

    public abstract User signIn(String account, String password);

    public abstract void signUp(String account, String password);

    public abstract boolean register(User user) throws SQLException;
}
