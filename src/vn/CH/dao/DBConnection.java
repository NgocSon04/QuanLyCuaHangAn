package vn.CH.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection() {
        Connection cons = null;
        try {
            // Thông tin cấu hình Database
            String url = "jdbc:mysql://localhost:3306/QuanLyCuaHang"; 
            String user = "root"; // Tên đăng nhập MySQL của bạn (thường là root)
            String password = "070704"; // Mật khẩu MySQL của bạn (nếu có thì điền vào đây)
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            cons = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cons;
    }
    
    public static void main(String[] args) {
        // Chạy thử hàm main này để xem kết nối được chưa
        System.out.println(getConnection());
    }
}
