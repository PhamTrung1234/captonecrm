package crm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm.enumurl.EnumUrlParam;
@WebFilter(filterName = "filter",urlPatterns = "/*")
public class AuthenticationFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		boolean isValid = false;
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse reqs = (HttpServletResponse) response;
		Cookie[] cookie = req.getCookies();
		for (Cookie cookie2 : cookie) {
			if(cookie2.getName().equals("login")) {
				isValid = true;
				break;
			}
		}
		String path = req.getServletPath();
		String context = req.getContextPath();
		
		
		if(path.equalsIgnoreCase("/login")) {
            if(!isValid) {
				
				chain.doFilter(request, response);
				
			}else {
				reqs.sendRedirect(context+EnumUrlParam.ADD_USER.getEndpoint());
			}
		}else {
			if (isValid) {
				chain.doFilter(request, response);
			}else {
				reqs.sendRedirect(context+"/login");
			}
		}
	}
        
}
