package crm.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import crm.config.MySqlConfig;

public class LoginRepository {
	public int checkLogIn (String emai, String password) {
  	  int id = 0;
  	  String sql = "SELECT u.id  FROM users u WHERE u.password =? and u.email =?";
  	  try {
			
			Connection connection = MySqlConfig.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, password);
			ps.setString(2, emai);
			ResultSet rSet = ps.executeQuery();
			while(rSet.next()){
				id = rSet.getInt("id");
			}
			connection.close();
		} catch (Exception e) {
			System.out.println("loi dang nhap "+ e.getMessage());
		}
  	  return id;
    }

}
