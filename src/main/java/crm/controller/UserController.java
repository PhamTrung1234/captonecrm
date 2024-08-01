package crm.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import crm.entity.RoleEntity;
import crm.entity.UserDetail;
import crm.entity.UserEntity;
import crm.enumurl.EnumUrlParam;
import crm.service.RoleService;
import crm.service.RoleServiceImp;
import crm.service.UserService;
import crm.service.UserServiceImp;
import crm.validation.Validation;
@WebServlet(name = "userMenenger" ,urlPatterns = {"/add-user","/show-list-user","/update-user","/user-detail"})
public class UserController extends HttpServlet{
	RoleServiceImp roles = new RoleService();
	UserServiceImp userService = new UserService(); 
	Validation validation =new Validation();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String path = req.getServletPath();
    	EnumUrlParam checkPath = EnumUrlParam.fromPath(path);
    	
    	switch (checkPath) {
		case ADD_USER:
			addUser(req, resp);
			break;
        case USER:
        	showListUser(req, resp);
        	break;
        case UPDATE_USER:
        	updateUser(req, resp);
        	break;
        case USER_DETAIL:
        	userDetail(req, resp);
        	break;
		default:
			break;
		}
    	
    
    	
    }
    public void showListUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	int id =0;
    	String roles = "";
    	Cookie[] cookies = req.getCookies();
    	for (Cookie cookie : cookies) {
			if(cookie.getName().equals("role")) {
				roles = cookie.getValue();
			}
			if(cookie.getName().equals("id")) {
				id = Integer.parseInt(cookie.getValue());
			}
		}
    	
      	List<UserEntity> listUsers = userService.getListUser();
      	List<UserEntity> listMember = new ArrayList<UserEntity>();
      	switch (roles) {
		case "USER":
			for (UserEntity userEntity : listUsers) {
				
				if(userEntity.getId()==id) {
					
					listMember.add(userEntity);
				}
			}
			req.setAttribute("listuser", listMember);
			System.out.println(listMember);
			break;

		default:
			req.setAttribute("listuser", listUsers);
			break;
		}
    	
    	req.getRequestDispatcher("user-table.jsp").forward(req, resp);
	}
    
    public void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	List<RoleEntity> listRoles = roles.listRoles();
    	
    	req.setAttribute("listRoles",listRoles);
    	req.getRequestDispatcher("user-add.jsp").forward(req, resp);
	}
    public void updateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	int id = Integer.parseInt(req.getParameter("id"));
        List<RoleEntity> listRoles1 = roles.listRoles();
        UserEntity users = userService.getUserById(id);
        req.setAttribute("users", users); 
    	req.setAttribute("listRoles",listRoles1);
    	req.getRequestDispatcher("update-user.jsp").forward(req, resp);
	}
    public void userDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	int id = Integer.parseInt(req.getParameter("id"));
        UserDetail users = userService.getUserDetailById(id);
        req.setAttribute("users", users);
    	req.setAttribute("listnomal", userService.checkStatus(users, 1));
    	req.setAttribute("liststart", userService.checkStatus(users, 2));
    	req.setAttribute("listend", userService.checkStatus(users, 3));
    	double sizeUser = users.getListTasks().size();
    	double sizeNormal = userService.checkStatus(users, 1).size();
    	double sizeStart = userService.checkStatus(users, 2).size();
    	double sizeEnd = userService.checkStatus(users, 3).size();
    	if(users.getListTasks().size()>0) {
    	req.setAttribute("chuaBatDau", sizeNormal/sizeUser*100);
    	req.setAttribute("chuaHoanThanh", sizeStart/sizeUser*100);
    	req.setAttribute("daHoanThanh", sizeEnd/sizeUser*100);
    	}
    	
    	req.getRequestDispatcher("user-details.jsp").forward(req, resp);
	}
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       req.setCharacterEncoding("UTF-8");
       String firstName = req.getParameter("firstName");
 	   String lastName = req.getParameter("lastName");
 	   String email = req.getParameter("email");
 	   String phone = req.getParameter("Phone");
 	   String passWord = req.getParameter("Password");
	   String path = req.getServletPath();
	   EnumUrlParam checkPath = EnumUrlParam.fromPath(path);
 	   boolean checkEmail = validation.patternMatches(email);
 	   boolean checkNullEmail = validation.checkNullOrEmpty(email, "email");
 	   boolean checkFirstName = validation.checkNullOrEmpty(firstName, "first_name");
 	   boolean checkLastName = validation.checkNullOrEmpty(lastName, "last_name");
 	   boolean checkPhone = validation.checkNullOrEmpty(phone, "số điện thoại");
 	   boolean checkPassWord = validation.checkNullOrEmpty(passWord, "passWord");
 	   
 	   if(!checkEmail || !checkFirstName || !checkLastName || !checkNullEmail || !checkPassWord || !checkPhone || !userService.checkEmail(userService.getListUser(), email)) {
 		   
 		    switch (checkPath) {
			case ADD_USER:
				addUser(req, resp);
				break;
            case UPDATE_USER:
            	updateUser(req, resp);
			default:
				break;
			}
 	   }else {
 		  int roleId = Integer.parseInt(req.getParameter("role"));
 	 	   RoleEntity roles = new RoleEntity();
 		 roles.setId(roleId);
		UserEntity users = new UserEntity(0, firstName, lastName, passWord, email, phone, roles);
		switch (checkPath) {
		case ADD_USER:
			userService.addUser(users);
			break;
		case UPDATE_USER:
			int id = Integer.parseInt(req.getParameter("id"));
			users.setId(id);
			userService.updateUserById(users);
			break;
		default:
			break;
		}
		String context = req.getContextPath();
		resp.sendRedirect(context + EnumUrlParam.USER.getEndpoint());
	}
 	   
 	   
    	   
    }
    
   
}
