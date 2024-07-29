package crm.entity;

import java.sql.Timestamp;

public class TaskEntity {
     private int id;
     private int idUser;
     private int idPRoject;
     private int idStatus;
     private String name;
     private java.sql.Timestamp startDate;
     private java.sql.Timestamp endDate;
	public TaskEntity(int id, int idUser, int idPRoject, int idStatus, String name, Timestamp startDate,
			Timestamp endDate) {
		super();
		this.id = id;
		this.idUser = idUser;
		this.idPRoject = idPRoject;
		this.idStatus = idStatus;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public int getIdPRoject() {
		return idPRoject;
	}
	public void setIdPRoject(int idPRoject) {
		this.idPRoject = idPRoject;
	}
	public int getIdStatus() {
		return idStatus;
	}
	public void setIdStatus(int idStatus) {
		this.idStatus = idStatus;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public java.sql.Timestamp getStartDate() {
		return startDate;
	}
	public void setStartDate(java.sql.Timestamp startDate) {
		this.startDate = startDate;
	}
	public java.sql.Timestamp getEndDate() {
		return endDate;
	}
	public void setEndDate(java.sql.Timestamp endDate) {
		this.endDate = endDate;
	}
    
	
}
