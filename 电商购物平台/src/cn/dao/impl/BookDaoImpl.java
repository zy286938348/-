package cn.dao.impl;

import cn.dao.BookDao;
import cn.entity.Book;
import cn.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * bookDAO的实现类
 */
public class BookDaoImpl implements BookDao {

    /**
     * 通过主键查询book
     * @param id 要查询的book的主键
     * @return book对象
     */
    @Override
    public Book selectBookByPrimaryKey(int id) {

        Connection connection = DBUtils.getConnection();
        String sql = "SELECT * FROM book WHERE id = ?";
        PreparedStatement pstmt = DBUtils.getPreparedStatement(connection, sql, id);
        Book book = null;
        try {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                book = new Book(rs.getInt("id"),
                        rs.getString("bookName"),
                        rs.getString("bookAuthor"),
                        rs.getInt("num"),
                        rs.getInt("price"),
                        rs.getString("category"),
                        rs.getString("category2"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    /**
     * 通过书名和类型模糊查询book
     * @param bookName 书名
     * @param category1 类型
     * @param category2 类型2
     * @return 查找到的book的list
     */
    @Override
    public List<Book> selectBooksByNameAndCategory(String bookName, String category1, String category2) {

        Connection connection = DBUtils.getConnection();
        String sql = "SELECT * FROM book WHERE bookName LIKE ? AND (category LIKE ? OR category2 LIKE ?)";
        PreparedStatement pstmt = DBUtils.getPreparedStatement(connection, sql, "%"+bookName+"%", "%"+category1+"%", "%"+category2+"%");
        Book book = null;
        List<Book> list = new ArrayList<>();
        try {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                book = new Book(rs.getInt("id"),
                        rs.getString("bookName"),
                        rs.getString("bookAuthor"),
                        rs.getInt("num"),
                        rs.getInt("price"),
                        rs.getString("category"),
                        rs.getString("category2"));
                list.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 查找所有book
     * @return 包含book对象的list
     */
    @Override
    public List<Book> selectAll() {
        Connection connection = DBUtils.getConnection();
        String sql = "SELECT * FROM book";
        PreparedStatement pstmt = DBUtils.getPreparedStatement(connection, sql);
        Book book = null;
        List<Book> list = new ArrayList<>();
        try {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                book = new Book(rs.getInt("id"),
                        rs.getString("bookName"),
                        rs.getString("bookAuthor"),
                        rs.getInt("num"),
                        rs.getInt("price"),
                        rs.getString("category"),
                        rs.getString("category2"));
                list.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 更新book数据
     * @param book 要更新的book对象
     * @return 影响条数
     */
    @Override
    public int updateBook(Book book) {
        Connection connection = DBUtils.getConnection();
        String sql = "UPDATE book SET bookName = ? , " +
                "bookAuthor = ? , " +
                "num = ? , " +
                "price = ? , " +
                "category = ? , " +
                "category2 = ? " +
                "WHERE id = ? ";

        PreparedStatement pstmt = DBUtils.getPreparedStatement(connection, sql, book.getBookName(),
                book.getBookAuthor(), book.getNum(), book.getPrice(), book.getCategory(),book.getCategory2(), book.getId());
        int flag = 0;
        try {
            flag = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 插入book信息
     * @param book 要插入的book信息
     * @return 影响的条数
     */
    @Override
    public int insertBook(Book book) {
        Connection connection = DBUtils.getConnection();
        String sql = "INSERT INTO book " +
                "(bookName, bookAuthor, num, price, category, category2) " +
                "VALUES (?,?,?,?,?,?)";

        PreparedStatement pstmt = DBUtils.getPreparedStatement(connection, sql, book.getBookName(), book.getBookAuthor(),
                book.getNum(), book.getPrice(), book.getCategory(), book.getCategory2());

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
            DBUtils.closeConnection(connection);
        }
        return flag;
    }

    /**
     * 通过主键id 删除book
     * @param id 需要删除的book类的id
     * @return 影响的条数
     */
    @Override
    public int deleteBook(int id) {
        Connection connection = DBUtils.getConnection();
        String sql = "DELETE FROM book WHERE id = ?";

        PreparedStatement pstmt = DBUtils.getPreparedStatement(connection, sql, id);
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
            DBUtils.closeConnection(connection);
        }
        return flag;
    }
}