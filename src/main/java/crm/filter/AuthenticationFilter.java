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
		String roles = "";
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse reqs = (HttpServletResponse) response;
		Cookie[] cookie = req.getCookies();
		
		for (Cookie cookie2 : cookie) {
			if(cookie2.getName().equals("login")) {
				isValid = true;
				
			}
			if(cookie2.getName().equalsIgnoreCase("role")) {
				roles = cookie2.getValue();
				
			}
		}
		
		String path = req.getServletPath();
		
		String context = req.getContextPath();
		EnumUrlParam checkPath = EnumUrlParam.fromPath(path);
		if( path.equalsIgnoreCase(EnumUrlParam.ERROR.getEndpoint())){
			chain.doFilter(request, response);
		}
		if(path.equalsIgnoreCase("/login")) {
            if(!isValid) {
				
				chain.doFilter(request, response);
				
			}else {
				reqs.sendRedirect(context+EnumUrlParam.ADD_USER.getEndpoint());
			}
		}else {
			if (isValid) {
				
				switch (roles) {
				case "USER":
					switch (checkPath) {
					case USER:
						chain.doFilter(request, response);
						break;
                    case ADD_USER:
                    	chain.doFilter(request, response);
                    	break;
                    case USER_DETAIL:
                    	chain.doFilter(request, response);
                    	break;
					default:
						reqs.sendRedirect(context+EnumUrlParam.ERROR.getEndpoint());
						break;
					}
					break;
                case "LEADER":
                	switch (checkPath) {
					case USER:
						chain.doFilter(request, response);
						break;
					case ADD_USER:
                    	chain.doFilter(request, response);
                    	break;
                    case USER_DETAIL:
                    	chain.doFilter(request, response);
                    	break;
                    case PROJECT:
                    	reqs.sendRedirect(context+ EnumUrlParam.PROJECTBYID.getEndpoint());
                    	break;
                    case PROJECTBYID:
                    	chain.doFilter(request, response);
                    	break;
                    case TASK:
                    	chain.doFilter(request, response);
                    	break;
                    case UPDATE_PROJECT:
                    	chain.doFilter(request, response);
                    	break;
                    case UPDATE_TASK:
                    	chain.doFilter(request, response);
                    	break;
                    case DELETE_PROJECT:
                    	chain.doFilter(request, response);
                    	break;
                    case DELETE_TASK:
                    	chain.doFilter(request, response);
                    	break;
					default:
						reqs.sendRedirect(context+EnumUrlParam.ERROR.getEndpoint());
						break;
					}
				default:
					chain.doFilter(request, response);
					break;
				}
				
			}else {
				reqs.sendRedirect(context + "/login");
			}
		}
	}
	public void checkFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		boolean isValid = false;
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse reqs = (HttpServletResponse) response;
		Cookie[] cookie = req.getCookies();
		String context = req.getContextPath();
		for (Cookie cookie2 : cookie) {
			if(cookie2.getName().equals("login")) {
				isValid = true;
				break;
			}
		}
		
	}
        
}
