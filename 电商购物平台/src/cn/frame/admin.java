package cn.frame;

import cn.Final;
import cn.dao.BookDao;
import cn.dao.impl.BookDaoImpl;
import cn.entity.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class admin extends JFrame {
    BookDao bookDao = new BookDaoImpl();

    public admin(){
        setTitle("电商购物平台-管理界面");
        setSize(1100,700);
        setLocation(333,165);
        setResizable(false);
        add();
        setVisible(true);
        //设置点击后直接关闭程序而不是隐藏
        setDefaultCloseOperation(3);
        //设置点击关闭时仅关闭当前的界面
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void add(){

        JLabel addBook = new JLabel("添加书籍：");
        addBook.setFont(new Font("黑体", Font.LAYOUT_NO_LIMIT_CONTEXT,40));

        JLabel bookName = new JLabel("书名：");
        bookName.setFont(new Font("黑体", Font.LAYOUT_NO_LIMIT_CONTEXT,30));
        JTextField j_bookName = new JTextField();
        j_bookName.setFont(new Font("楷体",Font.PLAIN,30));

        JLabel bookAuthor = new JLabel("作者：");
        bookAuthor.setFont(new Font("黑体", Font.LAYOUT_NO_LIMIT_CONTEXT,30));
        JTextField j_bookAuthor = new JTextField();
        j_bookAuthor.setFont(new Font("楷体",Font.PLAIN,30));

        JLabel num = new JLabel("数量：");
        num.setFont(new Font("黑体", Font.LAYOUT_NO_LIMIT_CONTEXT,30));
        JTextField j_num = new JTextField();
        j_num.setFont(new Font("楷体",Font.PLAIN,30));

        JLabel price = new JLabel("单价：");
        price.setFont(new Font("黑体", Font.LAYOUT_NO_LIMIT_CONTEXT,30));
        JTextField j_price = new JTextField();
        j_price.setFont(new Font("楷体",Font.PLAIN,30));

        JLabel category = new JLabel("类别1：");
        category.setFont(new Font("黑体", Font.LAYOUT_NO_LIMIT_CONTEXT,30));
        JComboBox j_category = new JComboBox(Final.JCOMBOXNAME1);
        j_category.setFont(new Font("楷体",Font.PLAIN,30));

        JLabel category2 = new JLabel("类别2：");
        category2.setFont(new Font("黑体", Font.LAYOUT_NO_LIMIT_CONTEXT,30));
        JComboBox j_category2 = new JComboBox(Final.JCOMBOXNAME2);
        j_category2.setFont(new Font("楷体",Font.PLAIN,30));

        JButton b_add = new JButton("添加");
        b_add.setFont(new Font("黑体",Font.PLAIN,30));
        b_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bookName = j_bookName.getText();
                String bookAuthor = j_bookAuthor.getText();
                String num = j_num.getText();
                String price = j_price.getText();
                String category = String.valueOf(j_category.getSelectedItem());
                String category2 = String.valueOf(j_category2.getSelectedItem());

                if (bookName.equals("") || bookAuthor.equals("") || num.equals("") || price.equals("") || category.equals("-请选择-") || category2.equals("-请选择-")){
                    JOptionPane.showMessageDialog(null, "添加失败，内容不可为空", "错误", JOptionPane.ERROR_MESSAGE);
                }else{
                    Book book = new Book(bookName,bookAuthor,Integer.parseInt(num),Integer.parseInt(price),category,category2);
                    bookDao.insertBook(book);
                    JOptionPane.showMessageDialog(null, "添加成功，请刷新书籍界面", "提示", JOptionPane.INFORMATION_MESSAGE);
                }
                j_bookName.setText("");
                j_bookAuthor.setText("");
                j_num.setText("");
                j_price.setText("");
                j_category.setSelectedIndex(0);
                j_category2.setSelectedIndex(0);
            }
        });

        /**
         * 分割线
         */
        JSeparator jSeparator = new JSeparator(SwingConstants.VERTICAL);

        JLabel deleteBook = new JLabel("删除书籍：");
        deleteBook.setFont(new Font("黑体", Font.LAYOUT_NO_LIMIT_CONTEXT,40));

        JLabel bookId = new JLabel("书籍ID：");
        bookId.setFont(new Font("黑体", Font.LAYOUT_NO_LIMIT_CONTEXT,30));
        JTextField j_bookId = new JTextField();
        j_bookId.setFont(new Font("楷体",Font.PLAIN,30));


        JButton b_delete = new JButton("删除");
        b_delete.setFont(new Font("黑体",Font.PLAIN,30));
        b_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id= Integer.parseInt(j_bookId.getText());
                int flag = bookDao.deleteBook(id);
                if (flag==1){
                    JOptionPane.showMessageDialog(null, "删除成功，请刷新书籍界面", "提示", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "删除失败，该书不存在", "错误", JOptionPane.ERROR_MESSAGE);
                }
                j_bookId.setText("");
            }
        });


        setLayout(null);

        addBook.setBounds(30,30,300,40);
        add(addBook);

        bookName.setBounds(80,100,150,40);
        add(bookName);
        j_bookName.setBounds(180,100,200,40);
        add(j_bookName);

        bookAuthor.setBounds(80,160,150,40);
        add(bookAuthor);
        j_bookAuthor.setBounds(180,160,200,40);
        add(j_bookAuthor);

        num.setBounds(80,220,150,40);
        add(num);
        j_num.setBounds(180,220,200,40);
        add(j_num);

        price.setBounds(80,280,150,40);
        add(price);
        j_price.setBounds(180,280,200,40);
        add(j_price);

        category.setBounds(80,340,150,40);
        add(category);
        j_category.setBounds(180,340,200,40);
        add(j_category);

        category2.setBounds(80,400,150,40);
        add(category2);
        j_category2.setBounds(180,400,200,40);
        add(j_category2);

        b_add.setBounds(350,500,150,60);
        add(b_add);

        jSeparator.setBounds(550,50,5,570);
        add(jSeparator);

        deleteBook.setBounds(580,30,300,40);
        add(deleteBook);

        bookId.setBounds(630,100,150,40);
        add(bookId);

        j_bookId.setBounds(750,100,200,40);
        add(j_bookId);

        b_delete.setBounds(800,180,150,60);
        add(b_delete);
    }
}