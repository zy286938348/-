package cn.utils;

import java.io.*;

/**
 * 文件工具类
 */
public class FButils {
    /**
     * 判断上一次登录是否记住密码
     * @return
     */
    public static boolean remmenberLogin() {
        File file = new File("src/login.txt");
        BufferedReader reader = null;
        String line = null;
        try{
            reader = new BufferedReader(new FileReader(file));
            line = reader.readLine();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (line.equals("true")){
                return true;
            }else {
                return false;
            }
        }
    }
    /**
     * 记住密码
     */
    public static void remmenber(String login){
        //创建流
        File src = new File("src/login.txt");
        OutputStream file = null;
        try{
            file = new FileOutputStream(src,false);
            byte[] bytes = login.getBytes();
            file.write(bytes,0,bytes.length);
            file.flush();  //强制刷新输出缓存区的元素
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭流
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**写入记住密码的用户名
     *
     * @param userName
     */
    public static void setText(String userName){
        //创建流
        File src = new File("src/loginUser.txt");
        OutputStream file = null;
        try{
            file = new FileOutputStream(src,false);   //加入true则是尾随输入不会将前边的元素删除然后重新输入
            byte[] bytes = userName.getBytes();
            file.write(bytes,0,bytes.length);
            file.flush();  //强制刷新输出缓存区的元素
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭流
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取记住密码的用户名
     *
     */
    public static String getUserNmae(){
        File file = new File("src/loginUser.txt");
        BufferedReader reader = null;
        String line = null;
        try{
            reader = new BufferedReader(new FileReader(file));
            line = reader.readLine();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return line;
        }
    }

}