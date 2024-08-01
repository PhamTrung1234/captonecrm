package crm.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "logout",urlPatterns = "/log-out")
public class LogOutController extends HttpServlet{
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        	Cookie[] cookies = req.getCookies();
        	boolean isExit = false;
        	for (Cookie cookie : cookies) {
				if(cookie.getName().equals("login")) {
					isExit = true;
				}
			}
        	if(isExit) {
        		Cookie cookie = new Cookie("login", "true");
        		cookie.setMaxAge(0);
        		Cookie cookie2 = new Cookie("id", "0");
        		Cookie cookie3 = new Cookie("role", "abc");
        		cookie2.setMaxAge(0);
        		cookie3.setMaxAge(0);
        		resp.addCookie(cookie3);
        		resp.addCookie(cookie);
        		resp.addCookie(cookie2);
        	}
        	String context =  req.getContextPath();
        	resp.sendRedirect(context+"/login");
        }
}
