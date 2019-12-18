package cn.entity;

/**
 * book实体类
 */
public class Book {

    private int id;

    private String bookName;

    private String bookAuthor;

    private int num;

    private int price;

    private String category;

    private String category2;

    @Override
    public String toString() {
        return "\nBook{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", num=" + num +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", category2='" + category2 + '\'' +
                '}';
    }

    public Book() {

    }

    public Book(String bookName, String bookAuthor, int num) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.num = num;
    }

    public Book(String bookName, String bookAuthor, int num, int price, String category, String category2) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.num = num;
        this.price = price;
        this.category = category;
        this.category2 = category2;
    }

    public Book(int id, String bookName, String bookAuthor, int num, int price, String category, String category2) {
        this.id = id;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.num = num;
        this.price = price;
        this.category = category;
        this.category2 = category2;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategory2() {
        return category2;
    }

    public void setCategory2(String category2) {
        this.category2 = category2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
