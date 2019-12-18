package cn.dao;

import cn.entity.UserBook;

import java.util.List;

public interface BuyBook {
    /**
     * 根据用户信息获取指定书籍
     * @param userName
     * @return
     */
    List<UserBook> selectBookByUseName(String userName);

    /**
     * 根据用户信息删除指定书籍
     */
    int deleteUserBook(String userName);

    /**
     * 插入书籍
     * @param book
     * @return
     */
    int insertUserBook(UserBook book);
}
