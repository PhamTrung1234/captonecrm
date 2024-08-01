package crm.repository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import crm.config.MySqlConfig;
import crm.dto.ProjectDTO;
import crm.dto.ProjectTaskDTO;
import crm.entity.ProjectEntity;
import crm.entity.UserEntity;

public class ProjectRepository {
	 
	
	
	 public List<Integer> getProjectByIdLeader(int id) {
		String sql = "SELECT * FROM project p where p.id_leader=?";
		List<Integer> listId = new ArrayList<Integer>();
		try {
			Connection connection = MySqlConfig.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rSet = ps.executeQuery();
			while(rSet.next()) {
				listId.add(rSet.getInt("id"));
			}
			connection.close();
		} catch (Exception e) {
			System.out.println("lỗi lấy project by id leader "+e.getMessage());
		}
		return listId;
	}
	
	  public List<ProjectTaskDTO> getProjectDetailById(int id) {
		String sql= "SELECT u.first_name ,u.last_name  ,s.name nameStatus ,t.name nameTask,t.start_date ,t.end_date \r\n"
				+ "FROM users u \r\n"
				+ "join task t on t.id_user  = u.id \r\n"
				+ "JOIN  project p on t.id_project  = p.id \r\n"
				+ "JOIN status s on t.id_status = s.id \r\n"
				+ "WHERE p.id = ?";
		List<ProjectTaskDTO> listTasks = new ArrayList<ProjectTaskDTO>();
		try {
			Connection connection = MySqlConfig.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rSet = ps.executeQuery();
			while(rSet.next()) {
				ProjectTaskDTO project = new ProjectTaskDTO(rSet.getString("first_name")+" "+rSet.getString("last_name"),
						rSet.getString("nameTask"), rSet.getString("nameStatus"),
						rSet.getTimestamp("start_date").toLocalDateTime().toLocalDate(),
						rSet.getTimestamp("end_date").toLocalDateTime().toLocalDate());
				listTasks.add(project);
			}
			connection.close();
		} catch (Exception e) {
			System.out.println("loi lay project detail "+e.getMessage());
		}
		return listTasks;
 	}
	
	  public int updateProjectById(ProjectEntity project) {
		String sql = "UPDATE project SET name=?,start_date=?,end_date=?,id_leader=? WHERE id=?";
		int result = 0;
		try {
			Connection connection = MySqlConfig.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, project.getName());
			ps.setTimestamp(2, project.getFirstTime());
			ps.setTimestamp(3, project.getLastTime());
			ps.setInt(4, project.getUsers().getId());
			ps.setInt(5, project.getId());
			result = ps.executeUpdate();
			System.out.println("update project thanh cong");
			connection.close();
		} catch (Exception e) {
			System.out.println("update project that bai "+e.getMessage());
		}
		return result;
	}
	
      public ProjectDTO getProjectById(int id) {
		String sql = "SELECT * FROM project p WHERE p.id=?";
		ProjectDTO project = null;
		try {
			Connection connection = MySqlConfig.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rSet = ps.executeQuery();
			while(rSet.next()) {
				UserEntity users = new UserEntity(rSet.getInt("id_leader"), "", "", "", "", "", null);
				project = new ProjectDTO(id, rSet.getString("name"),
						rSet.getTimestamp("start_date").toLocalDateTime().toLocalDate(),
						rSet.getTimestamp("end_date").toLocalDateTime().toLocalDate(),users);
	
			}
			connection.close();
		} catch (Exception e) {
			System.out.println("loi lay project theo id "+e.getMessage());
		}
		return project;
	}	
	
	
	   public int deleteProjectById(int id) {
		 String sql = "DELETE FROM project p WHERE p.id=?";
		 int result = 0;
		 try {
			Connection connection = MySqlConfig.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			result = ps.executeUpdate();
			System.out.println("xoa project thanh cong");
			connection.close();
		} catch (Exception e) {
			System.out.println("loi xoa project "+ e.getMessage());
		}
		 return result;
	}
	
	
	   public List<ProjectDTO> getListProject() {
		   String sql = "SELECT * FROM project";
		   List<ProjectDTO> listProject = new ArrayList<ProjectDTO>() ;
		   try {
			Connection connection = MySqlConfig.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rSet = ps.executeQuery();
			while(rSet.next()) {
				UserEntity users = new UserEntity(rSet.getInt("id_leader"), "", "", "", "", "", null);
				ProjectDTO project = new ProjectDTO(rSet.getInt("id"),
						rSet.getString("name"),
						rSet.getTimestamp("start_date").toLocalDateTime().toLocalDate(),
						rSet.getTimestamp("end_date").toLocalDateTime().toLocalDate(),
						users);
				listProject.add(project);
			}
			connection.close();
		} catch (Exception e) {
			listProject =null;
			System.out.println("lỗi lấy list project");
		}
		   return listProject;
	}
	  
	
       public int addProject(ProjectEntity project) {
		      String sql = "INSERT INTO project values (0,?,?,?,?)";
		      int result =0;
		      
		      try {
		    	  Connection connection = MySqlConfig.getConnection();
		    	  PreparedStatement ps = connection.prepareStatement(sql);
		    	  ps.setString(1, project.getName());
		    	  ps.setTimestamp(2, project.getFirstTime());
		    	  ps.setTimestamp(3, project.getLastTime());
		    	  ps.setInt(4, project.getUsers().getId());
		    	  result = ps.executeUpdate();
		    	  connection.close();
				  System.out.println("them project thanh cong");
			} catch (Exception e) {
				System.out.println("loi them project "+ e.getMessage());
			}
		      return result;
	}
}
