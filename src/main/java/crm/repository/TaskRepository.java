package crm.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import crm.config.MySqlConfig;
import crm.dto.TaskByIdDTO;
import crm.dto.TaskDTO;
import crm.entity.TaskEntity;

public class TaskRepository {
	
	public int updateTask(TaskEntity task) {
		String sql = "UPDATE task SET id_user =?,id_project=?,id_status =?,name =?,start_date =?,end_date =? WHERE id=?";
		int result = 0;
		try {
			Connection connection = MySqlConfig.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, task.getIdUser());
			ps.setInt(2, task.getIdPRoject());
			ps.setInt(3, task.getIdStatus());
			ps.setString(4, task.getName());
			ps.setTimestamp(5, task.getStartDate());
			ps.setTimestamp(6, task.getEndDate());
			ps.setInt(7, task.getId());
			result = ps.executeUpdate();
			System.out.println("update thanh cong");
			connection.close();
		} catch (Exception e) {
			System.out.println("loi update task "+e.getMessage());
		}
		return result;
	}
	
	public TaskByIdDTO getTaskByID(int id) {
		String sql = "SELECT * FROM task t WHERE t.id=?";
		TaskByIdDTO task = null;
		try {
			Connection connection = MySqlConfig.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rSet = ps.executeQuery();
			while(rSet.next()) {
				task=new TaskByIdDTO(id, rSet.getInt("id_project"),
						rSet.getInt("id_status"),
						rSet.getInt("id_user"),
						rSet.getString("name"),
						rSet.getTimestamp("start_date").toLocalDateTime().toLocalDate(),
						rSet.getTimestamp("end_date").toLocalDateTime().toLocalDate());
			}
			connection.close();
		} catch (Exception e) {
			System.out.println("loi lay task theo id "+e.getMessage());
		}
		return task;
	}
	
	
	public int deleteTaskById(int id) {
		String sql = "DELETE FROM task t WHERE t.id=?";
		int result = 0;
		try {
			Connection connection = MySqlConfig.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			result = ps.executeUpdate();
			System.out.println("xoa task thanh cong");
			connection.close();
		} catch (Exception e) {
			System.out.println("loi soa task "+e.getMessage());
		}
		return result;
	}
	   
	public List<TaskDTO> getAllTask() {
		String sql = "SELECT t.id,p.id idproject, u.first_name ,u.last_name ,t.name ,p.name nameproject,s.name namestatus,t.start_date ,t.end_date FROM task t join project p on p.id =t.id_project JOIN users u on u.id =t.id_user JOIN status s on t.id_status =s.id ";
		List<TaskDTO> listTaskDTOs= new ArrayList<TaskDTO>();
		try {
			Connection connection = MySqlConfig.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rSet = ps.executeQuery();
			while(rSet.next()) {
				TaskDTO tasks = new TaskDTO(rSet.getInt("id"),rSet.getInt("idproject")
						,rSet.getString("first_name")+" "+rSet.getString("last_name"),
						rSet.getString("nameproject"), rSet.getString("namestatus"),
						rSet.getString("name")
						, rSet.getTimestamp("start_date").toLocalDateTime().toLocalDate(),
						rSet.getTimestamp("end_date").toLocalDateTime().toLocalDate());
				listTaskDTOs.add(tasks);
			}
			connection.close();
		} catch (Exception e) {
			System.out.println("loi lay list task "+e.getMessage());
		}
		return listTaskDTOs;
	}
	
        public int addTask(TaskEntity task) {
			String sql = "INSERT INTO task values (0,?,?,3,?,?,?)";
			int result = 0;
			try {
				Connection connection = MySqlConfig.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setInt(1, task.getIdUser());
				ps.setInt(2, task.getIdPRoject());
				ps.setString(3, task.getName());
				ps.setTimestamp(4, task.getStartDate());
				ps.setTimestamp(5, task.getEndDate());
				result = ps.executeUpdate();
				System.out.println("thêm task thành công");
				connection.close();
			} catch (Exception e) {
				System.out.println("lỗi thêm task "+ e.getMessage());
			}
			return result;
		}
}
