package crm.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm.dto.LeaderDTO;
import crm.dto.ProjectDTO;
import crm.dto.ProjectTaskDTO;
import crm.entity.ProjectEntity;
import crm.entity.UserEntity;
import crm.enumurl.EnumUrlParam;
import crm.service.ProjectService;
import crm.service.ProjectServiceImp;
import crm.service.UserService;
import crm.service.UserServiceImp;
import crm.validation.ConvertTime;
import crm.validation.Validation;
@WebServlet (name = "project",urlPatterns = {"/add-project","/project","/update-project","/project-detail","/project-id"})
public class ProjectController extends HttpServlet{
	    ProjectServiceImp projectService = new ProjectService();
	    UserServiceImp userServiceImp = new UserService();
	    ConvertTime time = new ConvertTime();
	    Validation vali = new Validation();
         @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        	 String path = req.getServletPath();
        	 EnumUrlParam checkPath = EnumUrlParam.fromPath(path);
        	 switch (checkPath) {
			case ADD_PROJECT:
				List<LeaderDTO> listLeader = userServiceImp.listLeader();
				req.setAttribute("listleader", listLeader);
				req.getRequestDispatcher("groupwork-add.jsp").forward(req, resp);
				break;
            case PROJECT:
            	List<ProjectDTO> listProject = projectService.getListProject();
            	req.setAttribute("listproject", listProject);
            	req.getRequestDispatcher("groupwork.jsp").forward(req, resp);
            	break;
            case UPDATE_PROJECT:
            	
            	updateProject(req, resp);
            	break;
            case PROJECT_DETAIL:
            	projectDetail(req, resp);
            	break;
            case PROJECTBYID:
            	showProjectById(req, resp);
            	break;
			default:
				break;
			}
        	 
        }
         public void showProjectById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			Cookie[] cookie = req.getCookies();
			String roles = "";
			List<ProjectDTO> listProject = projectService.getListProject();
			List<ProjectDTO> listProjectById = new ArrayList<ProjectDTO>();
			int id = 0;
			for (Cookie cookie2 : cookie) {
				if(cookie2.getName().equals("role")) {
					roles = cookie2.getValue();
				}
				if(cookie2.getName().equals("id")) {
					id= Integer.parseInt(cookie2.getValue());
				}
			}
			
			if(roles.equalsIgnoreCase("LEADER")) {
				for (ProjectDTO projectDTO : listProject) {
					if(projectDTO.users().getId()== id) {
						listProjectById.add(projectDTO);
					}
				}
			}
			
        	req.setAttribute("listproject", listProjectById);
        	req.getRequestDispatcher("groupworkbyid.jsp").forward(req, resp);
		}
         
         public void projectDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        	 int id = Integer.parseInt(req.getParameter("id"));
        	 List<ProjectTaskDTO> listTasks = projectService.getProjectDetailById(id);
        	 double sizePRoject =  listTasks.size();
        	 req.setAttribute("listtask", listTasks);
        	 req.setAttribute("listnomal", projectService.checkStatus(listTasks, 1));
        	 req.setAttribute("liststart", projectService.checkStatus(listTasks, 2));
        	 req.setAttribute("listend", projectService.checkStatus(listTasks, 3));
        	 double sizeNomal = projectService.checkStatus(listTasks, 1).size();
        	 double sizeStart = projectService.checkStatus(listTasks, 2).size();
        	 double sizeEnd = projectService.checkStatus(listTasks, 3).size();
        	 if(sizePRoject>0) {
        		 req.setAttribute("chuaBatDau", sizeNomal/sizePRoject*100);
        		 req.setAttribute("daBatDau", sizeStart/sizePRoject*100);
        		 req.setAttribute("hoanthanh", sizeEnd/sizePRoject*100);
        		 
        	 }
        	 
        	 req.getRequestDispatcher("groupwork-detail.jsp").forward(req, resp);
		}
         public void updateProject(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        	 int id = Integer.parseInt(req.getParameter("id"));
         	ProjectDTO project = projectService.getProjectById(id);
         	List<LeaderDTO> listLeader1 = userServiceImp.listLeader();
			req.setAttribute("listleader", listLeader1);
			
         	req.setAttribute("project", project);
         	req.getRequestDispatcher("groupwork-update.jsp").forward(req, resp);
		}
        @SuppressWarnings("deprecation")
		@Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        	req.setCharacterEncoding("UTF-8");
        	String name = req.getParameter("name");
           String firstTime = req.getParameter("stars-time");
           String lastTime = req.getParameter("end-time");
           Timestamp newFirst = time.convertStringToTimetamp(firstTime);
           Timestamp newLast = time.convertStringToTimetamp(lastTime);
           int idLeader = Integer.parseInt(req.getParameter("idUser"));
           UserEntity users = new UserEntity(idLeader, "", "", "", "", "", null);
           
           if(newFirst.getYear()>=70 && newLast.getYear()>=70 && newFirst.compareTo(newLast)<0 && vali.checkNullOrEmpty(name, "tên dự án")) {
           ProjectEntity pEntity = new ProjectEntity();
           pEntity.setId(0);
           pEntity.setName(name);
           pEntity.setFirstTime(newFirst);
           pEntity.setLastTime(newLast);
           pEntity.setUsers(users);
           String path = req.getServletPath();
           EnumUrlParam checkPath = EnumUrlParam.fromPath(path);
           switch (checkPath) {
		      case ADD_PROJECT:
			     projectService.addProject(pEntity);
			   break;
		      case UPDATE_PROJECT:
		    	  int id=Integer.parseInt(req.getParameter("id"));
		    	  pEntity.setId(id);
		    	  projectService.updateProject(pEntity);
		    	  break;
		      default:
			   break;
		}
           
           String context = req.getContextPath();
           resp.sendRedirect(context+EnumUrlParam.PROJECT.getEndpoint());
           }else {
			System.out.println("lỗi ngày nhập không đúng định dạng hoặc ngày bắt đầu trước ngày kết thúc");
			req.getRequestDispatcher("groupwork-add.jsp").forward(req, resp);
		}
           
           
          
          
        }
}
