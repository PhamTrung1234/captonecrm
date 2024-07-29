package crm.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import crm.entity.BaseRespon;
import crm.service.UserService;
import crm.service.UserServiceImp;

@WebServlet (name = "apiUser",urlPatterns = {"/api-delete-user"})
public class UserApiController extends HttpServlet{
       UserServiceImp userService = new UserService();
	   @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    	int id = Integer.parseInt(req.getParameter("id"));
	    	BaseRespon base = new BaseRespon(id, "", userService.deleteUserById(id)) ;
	    	Gson gson = new Gson();
	    	String jsonData = gson.toJson(base);
	    	resp.setContentType("application/json");
	    	
	    }
}
