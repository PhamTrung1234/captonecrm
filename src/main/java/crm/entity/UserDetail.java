package crm.entity;

import java.util.List;

import crm.dto.UserTaskDTO;

public class UserDetail {
       private String nameUser;
       private String email;
       List<UserTaskDTO> listTasks ;
	
	public String getNameUser() {
		return nameUser;
	}
	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<UserTaskDTO> getListTasks() {
		return listTasks;
	}
	public void setListTasks(List<UserTaskDTO> listTasks) {
		this.listTasks = listTasks;
	}
	@Override
	public String toString() {
		return "UserDetail [nameUser=" + nameUser + ", email=" + email + ", listTasks=" + listTasks + "]";
	}
	
       
}
