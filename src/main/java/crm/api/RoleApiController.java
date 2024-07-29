package crm.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import crm.entity.BaseRespon;
import crm.service.RoleService;
import crm.service.RoleServiceImp;
@WebServlet (name = "deleterole",urlPatterns = "/delete-roles")
public class RoleApiController extends HttpServlet{
	RoleServiceImp roleService = new RoleService();
      @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	int id = Integer.parseInt(req.getParameter("id"));
    	BaseRespon base =  new BaseRespon(id, "", roleService.deleteRoleById(id));
    	Gson gson = new Gson();
    	String jsonData = gson.toJson(base);
    	resp.setContentType("application/json");
    }
}
