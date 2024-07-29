package crm.entity;



public class ProjectEntity {
       private int id;
       private String name ;
       private java.sql.Timestamp firstTime;
       private java.sql.Timestamp lastTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public java.sql.Timestamp getFirstTime() {
		return firstTime;
	}
	public void setFirstTime(java.sql.Timestamp firstTime) {
		this.firstTime = firstTime;
	}
	public java.sql.Timestamp getLastTime() {
		return lastTime;
	}
	public void setLastTime(java.sql.Timestamp lastTime) {
		this.lastTime = lastTime;
	}
	@Override
	public String toString() {
		return "ProjectEntity [id=" + id + ", name=" + name + ", firstTime=" + firstTime + ", lastTime=" + lastTime
				+ "]";
	}
	
	
	  
	   
       
}
