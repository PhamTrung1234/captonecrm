package crm.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm.entity.RoleEntity;
import crm.enumurl.EnumUrlParam;
import crm.service.RoleService;
import crm.service.RoleServiceImp;
import crm.validation.Validation;
@WebServlet (name = "rolecontroller" ,urlPatterns = {"/add-role","/roles","/update-role"})
public class RoleController extends HttpServlet{
	 RoleServiceImp roleService = new RoleService();
	 Validation vali = new Validation();
       @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String path = req.getServletPath();
    	EnumUrlParam checkPath = EnumUrlParam.fromPath(path);
    	switch (checkPath) {
		case ADD_ROLE:
			req.getRequestDispatcher("role-add.jsp").forward(req, resp);
			break;
        case ROLE:
        	List<RoleEntity> listRoles = roleService.listRoles();
        	req.setAttribute("listroles", listRoles);
        	req.getRequestDispatcher("role-table.jsp").forward(req, resp);
        	break;
        case UPDATE_ROLE:
        	int id = Integer.parseInt(req.getParameter("id"));
        	RoleEntity roles = roleService.getRoleById(id);
        	req.setAttribute("roles", roles);
        	req.getRequestDispatcher("update-role.jsp").forward(req, resp);
		default:
			break;
		}
    }
       @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    	   String path = req.getServletPath();
    	   String context = req.getContextPath();
    	   req.setCharacterEncoding("UTF-8");
    	   String name = req.getParameter("name");
    	   String desr = req.getParameter("desr");
    	   RoleEntity roles = new RoleEntity();
    	   boolean checkName = vali.checkNullOrEmpty(name, "ten quyen");
    	   boolean checkDesr = vali.checkNullOrEmpty(desr, "mo ta");
    	   
    	   if(!checkName || !checkDesr) {
    		   req.getRequestDispatcher("role-add.jsp").forward(req, resp);
    	   }else {
			
    		   roles.setName(name);
        	   roles.setDescription(desr);
        	   if(path.equalsIgnoreCase("/add-role")) {
        		   roleService.addRole(roles);
        	   }else if (path.equalsIgnoreCase("/update-role")) {
        		   int id = Integer.parseInt(req.getParameter("id"));
        		   roles.setId(id);
				   roleService.updateRole(roles);
			}
        	   
        	   resp.sendRedirect(context + EnumUrlParam.ROLE.getEndpoint());
		}
    	  
    	   
    }
       
}
