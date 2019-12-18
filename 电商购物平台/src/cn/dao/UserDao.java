package cn.dao;

import cn.entity.User;

import java.util.List;

/**
 * user的DAO层
 */
public interface UserDao {

     User selectUserByUsernameAndPassword(String username , String password,String type);

     User selectUserByUsername(String username);

     int updateUser(User user);

     int insertUser(User user);

     int deleteUserByPrinaryKey(String username);

}
