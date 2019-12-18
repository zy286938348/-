package cn.dao.impl;

import cn.dao.UserDao;
import cn.entity.User;
import cn.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * UserDAO的实现类
 */
public class UserDaoImpl implements UserDao {

    /**
     * 通过username和password查找user对象
     * 实现登录功能
     * @param username 用户名
     * @param password 密码
     * @return 查找到的user对象  找不到返回null
     */
    @Override
    public User selectUserByUsernameAndPassword(String username, String password,String type) {

        Connection connection = DBUtils.getConnection();

        String sql = "SELECT * FROM user WHERE username = ? AND password = ? AND type = ?";
        PreparedStatement pstmt = DBUtils.getPreparedStatement(connection, sql, username,password,type);
        ResultSet rs = null;
        User user = null;
        try {
            rs = pstmt.executeQuery();
            while (rs.next()){
                user = new User(rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("sex"),
                        rs.getString("address"),
                        rs.getString("type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeConnection(connection);
        }
        return user;
    }

    /**
     * 注册时判断是否存在该用户名
     * @param username
     * @return
     */
    @Override
    public User selectUserByUsername(String username) {
        Connection connection = DBUtils.getConnection();

        String sql = "SELECT * FROM user WHERE username = ?";
        PreparedStatement pstmt = DBUtils.getPreparedStatement(connection, sql, username);
        ResultSet rs = null;
        User user = null;
        try {
            rs = pstmt.executeQuery();
            while (rs.next()){
                user = new User(rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("sex"),
                        rs.getString("address"),
                        rs.getString("type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeConnection(connection);
        }
        return user;
    }


    /**
     * 更新user数据
     * @param user 要更新的user数据
     * @return 影响条数
     */
    @Override
    public int updateUser(User user) {

        Connection connection = DBUtils.getConnection();
        String sql = "UPDATE user SET password = ? , name = ? , sex = ? , address = ? , type = ? WHERE username = ?";
        PreparedStatement pstmt = DBUtils.getPreparedStatement(connection, sql, user.getPassword(),
                user.getName(), user.getSex(), user.getAddress(), user.getType(), user.getUsername());
        int flag = 0;
        try {
            flag = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeConnection(connection);
            if (pstmt != null){
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    /**
     * 新增用户
     * @param user 要新增的用户 username属性为主键 必须写
     * @return 影响条数
     */
    @Override
    public int insertUser(User user) {
        Connection connection = DBUtils.getConnection();
        String sql = "INSERT INTO user (username , password, name, sex, address, type) VALUES (?,?,?,?,?,?)";
        PreparedStatement pstmt = DBUtils.getPreparedStatement(connection, sql,
                user.getUsername(), user.getPassword(), user.getName(), user.getSex(), user.getAddress(), user.getType());

        int flag = 0;
        try {
            flag = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeConnection(connection);
            if (pstmt != null){
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    /**
     * 通过主键username删除User记录
     * @param username 需要删除的user的username属性
     * @return 影响行数
     */
    @Override
    public int deleteUserByPrinaryKey(String username) {

        Connection connection = DBUtils.getConnection();
        String sql = "DELETE FROM user WHERE username = ?";
        PreparedStatement pstmt = DBUtils.getPreparedStatement(connection, sql, username);

        int flag = 0;
        try {
            flag = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeConnection(connection);
            if (pstmt != null){
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }
}
