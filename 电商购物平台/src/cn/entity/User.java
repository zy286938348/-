package cn.entity;

/**
 * user实体类
 */
public class User {

    //User的Type类型值
    public static final String USER_TYPE_USER = "普通用户";
    public static final String USER_TYPE_ADMIN = "管理员";

    private String username;

    private String password;

    private String name;

    private String sex;

    private String address;

    private String type;

    @Override
    public String toString() {
        return "\nUserBuy{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public User(String username, String password, String name, String sex, String address, String type) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.sex = sex;
        this.address = address;
        this.type = type;
    }

    public User(String username, String password, String type) {
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public User() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
