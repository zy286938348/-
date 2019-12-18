package cn.frame;

import cn.dao.BookDao;
import cn.dao.BuyBook;
import cn.dao.impl.BookDaoImpl;
import cn.dao.impl.BuyBookDaoImpl;
import cn.entity.Book;
import cn.entity.User;
import cn.entity.UserBook;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserBuy extends JFrame {
    BuyBook buyBook = new BuyBookDaoImpl();
    BookDao bookDao = new BookDaoImpl();
    JTable table = null;
    UserBook userBook = null;

    public UserBuy(List<UserBook> userBooks,User user){
        setTitle("电商购物平台-用户界面");
        setSize(1100,620);
        setLocation(333,165);
        setResizable(false);
        add();
        bookList(userBooks);
        userAddBookOrDeleteBook(user);
        setVisible(true);
        //设置点击后直接关闭程序而不是隐藏
        setDefaultCloseOperation(3);
        //设置点击关闭时仅关闭当前的界面
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void add() {
        JLabel buy = new JLabel("购物车：");
        buy.setFont(new Font("黑体", Font.LAYOUT_NO_LIMIT_CONTEXT,40));

        /**
         * 分割线
         */
        JSeparator jSeparator = new JSeparator(SwingConstants.VERTICAL);

        JLabel select = new JLabel("选择书籍：");
        select.setFont(new Font("黑体", Font.LAYOUT_NO_LIMIT_CONTEXT,40));

        setLayout(null);

        buy.setBounds(20, 25, 200, 40);
        add(buy);

        jSeparator.setBounds(660, 40, 10, 520);
        add(jSeparator);

        select.setBounds(680, 25, 260, 40);
        add(select);

    }

    public void bookList(List<UserBook> bookList){

        Object[] columnName = {"书名","作者","购买数量"};
        Object[][] books = new Object [bookList.size()][3];

        for(int i=0; i<bookList.size(); i++) {
            for(int j=0; j<5; j++) {
                switch(j) {
                    case 0: books[i][j] = bookList.get(i).getBookName();break;
                    case 1: books[i][j] = bookList.get(i).getBookAuthor();break;
                    case 2: books[i][j] = bookList.get(i).getNum();break;
                }
            }
        }

        DefaultTableModel model = new DefaultTableModel(books,columnName) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);
        table.setRowHeight(30);
        table.setCellSelectionEnabled(true);

        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setPreferredSize(new Dimension(100,50));
        //设置的是表头的字体大小和字体类型
        table.getTableHeader().setFont(new Font("黑体",Font.LAYOUT_NO_LIMIT_CONTEXT,25));
        //设置的是表格内容字体的大小
        table.setFont(new Font("黑体",Font.LAYOUT_NO_LIMIT_CONTEXT,25));

        JScrollPane pane = new JScrollPane();
        pane.getViewport().add(table);

        setLayout(null);

        pane.setBounds(30, 100, 600,400);
        add(pane);
    }

    public void userAddBookOrDeleteBook(User user){

        JButton delete = new JButton("清空购物车");
        delete.setFont(new Font("黑体", Font.LAYOUT_NO_LIMIT_CONTEXT,30));
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buyBook.deleteUserBook(user.getUsername());
                JOptionPane.showMessageDialog(null, "删除成功请重新打开页面", "提示", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JLabel id_select = new JLabel("ID：");
        id_select.setFont(new Font("黑体", Font.LAYOUT_NO_LIMIT_CONTEXT,30));
        JTextField select_id = new JTextField();
        select_id.setFont(new Font("黑体", Font.LAYOUT_NO_LIMIT_CONTEXT,30));

        JLabel num_select = new JLabel("数量：");
        num_select.setFont(new Font("黑体", Font.LAYOUT_NO_LIMIT_CONTEXT,35));
        JTextField select_num = new JTextField();
        select_num.setFont(new Font("黑体", Font.LAYOUT_NO_LIMIT_CONTEXT,30));

        JButton add = new JButton("添加书籍");
        add.setFont(new Font("黑体", Font.LAYOUT_NO_LIMIT_CONTEXT,30));
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(select_id.getText());
                int num = Integer.parseInt(select_num.getText());
                Book book = bookDao.selectBookByPrimaryKey(id);
                userBook = new UserBook(user.getUsername(),book.getBookName(),book.getBookAuthor(),num);
                buyBook.insertUserBook(userBook);
                select_id.setText("");
                select_num.setText("");
                JOptionPane.showMessageDialog(null, "添加成功请重新打开页面", "提示", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        setLayout(null);
        delete.setBounds(400,520,200,40);
        add(delete);



        id_select.setBounds(680, 100, 150, 40);
        add(id_select);
        select_id.setBounds(800, 100, 200, 40);
        add(select_id);

        num_select.setBounds(680, 170, 150, 40);
        add(num_select);
        select_num.setBounds(800, 170, 200, 40);
        add(select_num);

        add.setBounds(850,260,200,55);
        add(add);
    }
}