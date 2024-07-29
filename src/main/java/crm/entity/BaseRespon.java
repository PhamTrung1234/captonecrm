package crm.entity;

public class BaseRespon {
	private int statusCode;
    private String messege;
    private Object data ;
    
    
	
	public BaseRespon(int statusCode, String messege, Object data) {
		super();
		this.statusCode = statusCode;
		this.messege = messege;
		this.data = data;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessege() {
		return messege;
	}
	public void setMessege(String messege) {
		this.messege = messege;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
