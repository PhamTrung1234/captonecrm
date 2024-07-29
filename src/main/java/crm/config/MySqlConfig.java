package crm.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConfig {
	public static Connection getConnection() {
    	Connection connection = null;
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		connection= DriverManager.getConnection("jdbc:mysql://localhost:6603/crmapp", "root", "trungmta");
    	}catch (Exception e) {
    		System.out.println("lỗi không tìm thấy driver" + e.getMessage());
		}
    	return connection;
    }
}
