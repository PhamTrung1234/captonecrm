package crm.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import crm.config.MySqlConfig;
import crm.entity.RoleEntity;

public class RoleRepository {
	 
	public RoleEntity getRoleById(int id) {
		String sql = "SELECT * FROM roles r WHERE r.id =?";
		RoleEntity roles = new RoleEntity();
		try {
			Connection connection = MySqlConfig.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rSet = ps.executeQuery();
			while(rSet.next()) {
				roles.setId(rSet.getInt("id"));
				roles.setDescription(rSet.getString("description"));
				roles.setName(rSet.getString("name"));
			}
			connection.close();
		} catch (Exception e) {
			System.out.println("lỗi lấy role theo id "+e.getMessage());
		}
		return roles;
	}
	
	 public int updateRole(RoleEntity roles) {
		String sql = "UPDATE roles  SET description=?,name=? WHERE id =?";
		int result = 0;
		try {
			Connection connection = MySqlConfig.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, roles.getDescription());
			ps.setString(2, roles.getName());
			ps.setInt(3, roles.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("lỗi update role "+e.getMessage());
		}
		return result;
	}
	
	  public int deleteRoleById(int id) {
		String sql = "DELETE FROM roles  r WHERE r.id=?";
		int result = 0 ; 
		try {
			Connection connection = MySqlConfig.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			result = ps.executeUpdate();
			connection.close();
		} catch (Exception e) {
			System.out.println("lỗi xóa role "+ e.getMessage());
		}
		return result;
	}
	
	  public int addRole (RoleEntity role) {
	    	 String sql = "INSERT INTO roles(description ,name  ) values (?,?);";
	    	 int rSet = 0;
	    	 try {
				Connection connection = MySqlConfig.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setString(1, role.getDescription());
				ps.setString(2, role.getName());
				rSet = ps.executeUpdate();
				connection.close();
			} catch (Exception e) {
				System.out.println("lỗi thêm role "+e.getMessage());
			}
	    	 return rSet;
	     }
	     
	     
	     public List<RoleEntity> getListRoles() {
			String sql = "SELECT * FROM roles";
			List<RoleEntity> listRoles = new ArrayList<RoleEntity>();
			try {
				Connection connection = MySqlConfig.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
				ResultSet rSet = ps.executeQuery();
				while(rSet.next()) {
					RoleEntity roleEntity = new RoleEntity();
					roleEntity.setId(rSet.getInt("id"));
					roleEntity.setName(rSet.getString("name"));
					roleEntity.setDescription(rSet.getString("description"));
					listRoles.add(roleEntity);
				}
				connection.close();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("lỗi lấy list role "+ e.getMessage() );
			}
			return listRoles;
		}
}
