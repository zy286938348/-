package cn.frame;

import cn.dao.BookDao;
import cn.dao.BuyBook;
import cn.dao.impl.BookDaoImpl;
import cn.dao.impl.BuyBookDaoImpl;
import cn.entity.Book;
import cn.entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class commodityUser extends JFrame {
    //创建BookDAO对象
    BookDao bookDao = new BookDaoImpl();
    JTable t_books = null;
    BuyBook buyBook = new BuyBookDaoImpl();

//    String name = "张三";
//    String adress = "上海";
    int num = 0;

    public commodityUser(User user) {

        setTitle("电商购物平台-商品查询页面");
        setSize(1000,750);
        setLocation(500,140);
        init();
        importent(user);
        bookList(bookDao.selectAll());
        //设置窗口不可被拉伸
        setResizable(false);
        //设置点击后直接关闭程序而不是隐藏
        setDefaultCloseOperation(3);
        setVisible(true);
    }

    public void importent(User user){
        JLabel welcomeName = new JLabel("您好，"+user.getName()+"  用户");
        welcomeName.setFont(new Font("黑体",Font.LAYOUT_NO_LIMIT_CONTEXT,25));

        JLabel adressName = new JLabel("来自于："+user.getAddress());
        adressName.setFont(new Font("黑体",Font.LAYOUT_NO_LIMIT_CONTEXT,25));


        ImageIcon icon = new ImageIcon("src/cn/image/user.png");
        JButton userButton = new JButton(icon);

        setLayout(null);

        welcomeName.setBounds(20, 25, 400, 40);
        add(welcomeName);

        adressName.setBounds(650,25,250,40);
        add(adressName);

        userButton.setBounds(920,25,40,40);
        add(userButton);
        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserMsg(user);
            }
        });

        /**
         * 购物管理
         */
        JButton buy = new JButton("购物管理");
        buy.setFont(new Font("黑体",Font.LAYOUT_NO_LIMIT_CONTEXT,25));
        buy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserBuy(buyBook.selectBookByUseName(user.getUsername()),user);
            }
        });
        buy.setBounds(800, 630, 160,50);
        add(buy);
    }


    public void init() {
        JSeparator jSeparator = new JSeparator();

        JLabel l_bookName = new JLabel("书籍名:");
        l_bookName.setFont(new Font("黑体",Font.LAYOUT_NO_LIMIT_CONTEXT,25));
        JTextField t_bookName = new JTextField();
        t_bookName.setFont(new Font("黑体",Font.LAYOUT_NO_LIMIT_CONTEXT,25));

        JLabel l_jComboBoxName = new JLabel("分类：");
        l_jComboBoxName.setFont(new Font("黑体",Font.LAYOUT_NO_LIMIT_CONTEXT,25));
        String[] jComboBoxName = {"-请选择-","工具类","小说类","历史","软件编程"};
        JComboBox jComboBox = new JComboBox(jComboBoxName);
        jComboBox.setFont(new Font("黑体",Font.LAYOUT_NO_LIMIT_CONTEXT,25));

        JButton b_query = new JButton("查询");
        b_query.setFont(new Font("黑体",Font.LAYOUT_NO_LIMIT_CONTEXT,25));
        b_query.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bookName = t_bookName.getText();
                String jComboBoxName = String.valueOf(jComboBox.getSelectedItem());
                if (jComboBoxName.equals("-请选择-")){
                    jComboBoxName = "";
                }
                List<Book> selectBooks = bookDao.selectBooksByNameAndCategory(bookName,jComboBoxName,jComboBoxName);
                if (selectBooks.size()==0){
                    JOptionPane.showMessageDialog(null, "没有找到对应的书籍", "提示", JOptionPane.ERROR_MESSAGE);
                }else{
                    //清空表格数据
                    ((DefaultTableModel)t_books.getModel()).getDataVector().clear();
                    bookList(selectBooks);
                }
            }
        });

        /**
         * 购买数量
         */
        JLabel number = new JLabel("购物车商品数："+num+"件");
        number.setFont(new Font("黑体",Font.LAYOUT_NO_LIMIT_CONTEXT,25));


        /**
         * 自定义布局
         */
        setLayout(null);
        /**
         *分割线
         */
        jSeparator.setBounds(20,70,960,20);
        add(jSeparator);

        /**
         * 书籍名
         */
        l_bookName.setBounds(20, 100, 160, 40);
        add(l_bookName);
        /**
         * 书籍名输入框
         */
        t_bookName.setBounds(130, 100, 280, 40);
        add(t_bookName);
        /**
        * 分类
        */
        l_jComboBoxName.setBounds(430, 100, 100, 35);
        add(l_jComboBoxName);
        /**
         * 分类框
         */
        jComboBox.setBounds(520,100,250,40);
        add(jComboBox);
        /**
         * 查询按钮
         */
        b_query.setBounds(800, 95, 160, 50);
        add(b_query);
        /**
         * 购买
         */
        number.setBounds(25, 630, 300,50);
        add(number);


    }

    /**
     * 书籍界面
     */
    public void bookList(List<Book> bookList){
//        List<Book> bookList = bookService.selectAll();
        Object[] columnName = {"书籍编号","书名","作者","单价","库存"};
        Object[][] books = new Object [bookList.size()][5];

        for(int i=0; i<bookList.size(); i++) {
            for(int j=0; j<5; j++) {
                switch(j) {
                    case 0: books[i][j] = bookList.get(i).getId();break;
                    case 1: books[i][j] = bookList.get(i).getBookName();break;
                    case 2: books[i][j] = bookList.get(i).getBookAuthor();break;
                    case 3: books[i][j] = bookList.get(i).getPrice();break;
                    case 4: books[i][j] = bookList.get(i).getNum();break;
                }
            }
        }
        /**
         * { public boolean isCellEditable(int row, int column) { return false; }}
         * 设置了表格中的内容不可编辑
         */
//        t_books = new JTable(books,columnName);
        //非常重要，解决了数据无法清除的问题
        DefaultTableModel model = new DefaultTableModel(books,columnName) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        t_books = new JTable(model);
        t_books.setRowHeight(30);
        t_books.setCellSelectionEnabled(true);

        /**
         * 设置表头属性
         */
        //表头不可被拖动
        t_books.getTableHeader().setReorderingAllowed(false);
        t_books.getTableHeader().setPreferredSize(new Dimension(100,50));
        //设置的是表头的字体大小和字体类型
        t_books.getTableHeader().setFont(new Font("黑体",Font.LAYOUT_NO_LIMIT_CONTEXT,25));
        //设置的是表格内容字体的大小
        t_books.setFont(new Font("黑体",Font.LAYOUT_NO_LIMIT_CONTEXT,25));

        JScrollPane pane = new JScrollPane();
        pane.getViewport().add(t_books);

        setLayout(null);
        /**
         * 将JTable装入了一个容器中
         */
        pane.setBounds(25, 160, 950,450);
        add(pane);
    }
}