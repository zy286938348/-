package cn.dao;

import cn.entity.Book;

import java.util.List;

/**
 * book类的DAO层
 */
public interface BookDao {

    Book selectBookByPrimaryKey(int id);

    List<Book> selectBooksByNameAndCategory(String bookName, String category1, String category2);

    List<Book> selectAll();

    int updateBook(Book book);

    int insertBook(Book book);

    int deleteBook(int id);

}