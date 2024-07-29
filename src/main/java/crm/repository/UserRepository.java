package crm.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import crm.config.MySqlConfig;

import crm.dto.UserTaskDTO;
import crm.entity.RoleEntity;
import crm.entity.UserDetail;
import crm.entity.UserEntity;


public class UserRepository {
	
	@SuppressWarnings("null")
	public UserDetail getUserDetailById(int id) {
		String sql = "SELECT u.first_name ,u.last_name ,u.email ,s.name nameStatus ,p.name nameProject,t.name nameTask,t.start_date ,t.end_date \r\n"
				+ "FROM users u \r\n"
				+ "join task t on t.id_user  = u.id \r\n"
				+ "JOIN  project p on t.id_project  = p.id \r\n"
				+ "JOIN status s on t.id_status = s.id \r\n"
				+ "WHERE u.id = ?";
		UserDetail users = new UserDetail();
		List<UserTaskDTO> listTasks = new ArrayList<UserTaskDTO>();
		try {
			Connection connection = MySqlConfig.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rSet = ps.executeQuery();
			while(rSet.next()) {
				users.setEmail(rSet.getString("email"));
				users.setNameUser(rSet.getString("first_name")+" "+rSet.getString("last_name"));
				UserTaskDTO tasks = new UserTaskDTO(rSet.getString("nameStatus"), rSet.getString("nameProject"),
						                                 rSet.getString("nameTask"),
						                                 rSet.getTimestamp("start_date").toLocalDateTime().toLocalDate(),
						                                 rSet.getTimestamp("end_date").toLocalDateTime().toLocalDate());
				listTasks.add(tasks);
				
			}
			users.setListTasks(listTasks);
			connection.close();
		} catch (Exception e) {
			System.out.println("loi lay user detail "+e.getMessage());
		}
		return users;
	}
	
	public UserEntity getUserById(int id) {
		String sql = "SELECT * From users u where u.id=?";
		UserEntity usersEntity = null ;
		try {
			Connection connection = MySqlConfig.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rSet = ps.executeQuery();
			while(rSet.next()) {
				RoleEntity roles = new RoleEntity();
				roles.setId(rSet.getInt("id_role"));
				usersEntity = new UserEntity(rSet.getInt("id"), rSet.getString("first_name"), rSet.getString("last_name"), rSet.getString("password"), rSet.getString("email"), rSet.getString("phone"), roles);
			}
			connection.close();
		} catch (Exception e) {
			System.out.println("loi lay user theo id "+e.getMessage());
		}
		return usersEntity;
	}
	
	public int updateUserById(UserEntity user) {
		String sql = "UPDATE users  SET first_name =?,last_name =?,email =?,phone =?,password =?,id_role=? WHERE id =?";
		int result = 0;
		try {
			Connection connection = MySqlConfig.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, user.getFirst_name());
			ps.setString(2, user.getLast_name());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getPhone());
			ps.setString(5, user.getPassword());
			ps.setInt(7, user.getId());
			ps.setInt(6, user.getRole().getId());
			result = ps.executeUpdate();
			
			connection.close();
		} catch (Exception e) {
			System.out.println("lỗi update user "+e.getMessage() );
		}
		return result ;
	}
	
	public int deleteUserById(int id) {
		String sql = "DELETE FROM users u WHERE u.id=?";
		int result = 0;
		try {
			Connection connection = MySqlConfig.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			result = ps.executeUpdate();
			connection.close();
		} catch (Exception e) {
			System.out.println("lỗi xóa user "+e.getMessage());
		}
		return result;
	}
	
      public int addUser (UserEntity user) {
    	  String sql = "INSERT INTO users values (0,?,?,?,?,?,?)";
    	  int result = 0;
    	  try {
			Connection connection = MySqlConfig.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, user.getPassword());
			ps.setString(2, user.getFirst_name());
			ps.setString(3, user.getLast_name());
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getPhone());
			ps.setInt(6, user.getRole().getId());
			result = ps.executeUpdate();
			connection.close();
		} catch (Exception e) {
			System.out.println("loi them user "+ e.getMessage());
		}
    	  return result;
      }
      
      public List<UserEntity> getListUser() {
		String sql = "SELECT u.id ,u.first_name ,u.last_name ,u.email,r.name  FROM users u join roles r on u.id_role = r.id";
		List<UserEntity> listUser = new ArrayList<UserEntity>();
		try {
			Connection connection = MySqlConfig.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rSet = ps.executeQuery();
			while(rSet.next()) {
				RoleEntity roles = new RoleEntity();
				roles.setName(rSet.getString("name"));
				UserEntity user = new UserEntity(rSet.getInt("id"), rSet.getString("first_name"),rSet.getString("last_name") , "", rSet.getString("email"), "", roles);
				listUser.add(user);
			}
			connection.close();
		} catch (Exception e) {
			System.out.println("loi lay list thong tin user "+ e.getMessage());
		}
		return listUser;
	}
}
