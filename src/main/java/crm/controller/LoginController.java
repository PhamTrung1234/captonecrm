package crm.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm.validation.Validation;
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
 		
 		
 		
 		if(login.checkLogIn(emai, passWord)) {
 			Cookie cookie = new Cookie("login", "true");
 			cookie.setMaxAge(3000);
 	 		resp.addCookie(cookie);
 			resp.sendRedirect(context+EnumUrlParam.ADD_USER.getEndpoint());
 		}
 	}
 }
}
