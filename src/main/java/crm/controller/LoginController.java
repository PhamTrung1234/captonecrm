package crm.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm.validation.Validation;
import crm.dto.LoginDTO;
import crm.enumurl.EnumUrlParam;
import crm.service.LoginService;
import crm.service.LoginServiceImp;
@WebServlet (name = "login",urlPatterns = {"/login"})
public class LoginController extends HttpServlet{
	private LoginServiceImp login = new LoginService();
    @Override
 protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
 	
 	   req.getRequestDispatcher("login.jsp").forward(req, resp);
 	
 }
    @Override
 protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
 	Validation validation = new Validation();
 	String emai = req.getParameter("email");
     String passWord = req.getParameter("password");
     String context = req.getContextPath();
 	boolean checkEmail = validation.checkNullOrEmpty(emai, "email");
 	boolean checkPassWord = validation.checkNullOrEmpty(passWord, "passWord");
 	if(checkEmail && checkPassWord) {
 		LoginDTO loginDTO = login.checkLogIn(emai, passWord);
 		
 		
 		if(loginDTO != null) {
 			Cookie cookie = new Cookie("login", "true");
 			
 			Cookie cookie2 = new Cookie("role", loginDTO.nameRole());
 			
 			Cookie cookie3 = new Cookie("id", Integer.toString(loginDTO.id()));
 			cookie.setMaxAge(3000);
 			cookie2.setMaxAge(3000);
 			cookie3.setMaxAge(3000);
 			resp.addCookie(cookie3);
 	 		resp.addCookie(cookie);
 	 		resp.addCookie(cookie2);
 			resp.sendRedirect(context+EnumUrlParam.ADD_USER.getEndpoint());
 		}
 	}
 }
}
