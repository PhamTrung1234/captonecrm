package crm.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import crm.entity.BaseRespon;
import crm.service.TaskService;
import crm.service.TaskServiceImp;
@WebServlet(name = "deletetask" ,urlPatterns = {"/delete-task"})
public class TaskApiController extends HttpServlet{
     TaskServiceImp taskService = new TaskService();
     @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	int id = Integer.parseInt(req.getParameter("id"));
    	BaseRespon base = new BaseRespon(id, "", taskService.deleteTaskById(id));
    	Gson gson = new Gson();
    	String toJsonString = gson.toJson(base);
    }
}
