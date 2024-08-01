package crm.enumurl;

public enum EnumUrlParam {
	 USER("/show-list-user"),
     ADD_USER("/add-user"),
     UPDATE_USER("/update-user"),
     USER_DETAIL("/user-detail"),
     PROJECT("/project"),
     ADD_PROJECT("/add-project"),
     UPDATE_PROJECT("/update-project"),
     PROJECT_DETAIL("/project-detail"),
     ADD_ROLE("/add-role"),
     ROLE("/roles"),
     UPDATE_ROLE("/update-role"),
	 ADD_TASK("/add-task"),
	 UPDATE_TASK("/update-task"),
	 TASK("/task"),
	 DELETE_USER("/api-delete-user"),
	 DELETE_PROJECT("/delete-project"),
	 DELETE_TASK("/delete-task"),
	 DELETE_ROLE("/delete-roles"),
	 ERROR("/error-403"),
	 LOGIN("/login"),
	 PROJECTBYID("/project-id");
	
    private String endpoint;

    EnumUrlParam(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }
   
   

	

	public static EnumUrlParam fromPath(String path) {
        for (EnumUrlParam endpoint : values()) {
            if (endpoint.getEndpoint().equals(path)) {
                return endpoint;
            }
        }
        return null;
    }
	
}
