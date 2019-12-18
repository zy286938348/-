package cn.entity;

public class UserBook {
    private String userName;

    private String bookName;

    private String bookAuthor;

    private int num;

    public UserBook() {
    }

    public UserBook(String bookName, String bookAuthor, int num) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.num = num;
    }

    public UserBook(String userName, String bookName, String bookAuthor, int num) {
        this.userName = userName;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.num = num;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "UserBook{" +
                ", userName='" + userName + '\'' +
                ", bookName='" + bookName + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", num=" + num +
                '}';
    }
}
