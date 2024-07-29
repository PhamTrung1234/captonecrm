package crm.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import crm.entity.BaseRespon;
import crm.service.ProjectService;
import crm.service.ProjectServiceImp;
@WebServlet (name = "delete-project",urlPatterns = {"/delete-project"})
public class ProjectApiController extends HttpServlet{
	    ProjectServiceImp projectService = new ProjectService(); 
	    @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    	int id = Integer.parseInt(req.getParameter("id"));
	    	BaseRespon base = new BaseRespon(id, "", projectService.deleteProjectById(id));
	    	Gson gson= new Gson(); 
	    	String jsonData = gson.toJson(base);
	    	resp.setContentType("application/json");
	    }
}
