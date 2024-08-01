package crm.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import crm.config.MySqlConfig;
import crm.dto.LoginDTO;

public class LoginRepository {
	public LoginDTO checkLogIn (String emai, String password) {
  	  LoginDTO login = null;
  	  String sql = "SELECT u.id,s.name  FROM users u join roles s on u.id_role = s.id WHERE u.password =? and u.email =?";
  	  try {
			
			Connection connection = MySqlConfig.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, password);
			ps.setString(2, emai);
			ResultSet rSet = ps.executeQuery();
			while(rSet.next()){
				login = new LoginDTO(rSet.getInt("id"), rSet.getString("name"));
			}
			connection.close();
		} catch (Exception e) {
			System.out.println("loi dang nhap "+ e.getMessage());
		}
  	  return login;
    }

}
