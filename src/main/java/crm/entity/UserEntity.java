package crm.entity;

public class UserEntity {
	 private int id;
	    private String first_name;
	    private String last_name ;
	    private String password;
	    private String email;
	    private String phone ;
	    private RoleEntity role;
	    
		public UserEntity(int id, String first_name, String last_name, String password, String email, String phone,
				RoleEntity role) {
			super();
			this.id = id;
			this.first_name = first_name;
			this.last_name = last_name;
			this.password = password;
			this.email = email;
			this.phone = phone;
			this.role = role;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getFirst_name() {
			return first_name;
		}
		public void setFirst_name(String first_name) {
			this.first_name = first_name;
		}
		public String getLast_name() {
			return last_name;
		}
		public void setLast_name(String last_name) {
			this.last_name = last_name;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public RoleEntity getRole() {
			return role;
		}
		public void setRole(RoleEntity role) {
			this.role = role;
		}
		@Override
		public String toString() {
			return "UserEntity [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", password="
					+ password + ", email=" + email + ", phone=" + phone + ", role=" + role + "]";
		}
}
