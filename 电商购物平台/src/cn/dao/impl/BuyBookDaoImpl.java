package cn.dao.impl;

import cn.dao.BuyBook;
import cn.entity.UserBook;
import cn.utils.BuyUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BuyBookDaoImpl implements BuyBook {
    @Override
    public List<UserBook> selectBookByUseName(String userName) {
        Connection connection = BuyUtils.getConnection();
        String sql = "SELECT * FROM userbook WHERE userName LIKE ? ";
        PreparedStatement pstmt = BuyUtils.getPreparedStatement(connection, sql, "%"+userName+"%");
        UserBook book = null;
        List<UserBook> list = new ArrayList<>();
        try {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                book = new UserBook(rs.getString("bookName"),
                        rs.getString("bookAuthor"),
                        rs.getInt("num"));
                list.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int deleteUserBook(String userName) {
        Connection connection = BuyUtils.getConnection();
        String sql = "DELETE FROM userbook WHERE userName = ?";

        PreparedStatement pstmt = BuyUtils.getPreparedStatement(connection, sql, userName);
        int flag = 0;
        try {
            flag = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (pstmt != null){
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            BuyUtils.closeConnection(connection);
        }
        return flag;
    }

    @Override
    public int insertUserBook(UserBook book) {
        Connection connection = BuyUtils.getConnection();
        String sql = "INSERT INTO userbook " +
                "(userName,bookName, bookAuthor, num) " +
                "VALUES (?,?,?,?)";

        PreparedStatement pstmt = BuyUtils.getPreparedStatement(connection, sql,  book.getUserName(), book.getBookName(), book.getBookAuthor(),book.getNum());
        int flag = 0;
        try {
            flag = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (pstmt != null){
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            BuyUtils.closeConnection(connection);
        }
        return flag;
    }
}