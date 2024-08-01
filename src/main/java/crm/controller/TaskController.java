package crm.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm.dto.ProjectDTO;
import crm.dto.StatusDTO;
import crm.dto.TaskByIdDTO;
import crm.dto.TaskDTO;
import crm.entity.TaskEntity;
import crm.entity.UserEntity;
import crm.enumurl.EnumUrlParam;
import crm.service.ProjectService;
import crm.service.ProjectServiceImp;
import crm.service.StatusService;
import crm.service.TaskService;
import crm.service.TaskServiceImp;
import crm.service.UserService;
import crm.service.UserServiceImp;
import crm.validation.ConvertTime;
import crm.validation.Validation;
@WebServlet(name = "task",urlPatterns = {"/add-task","/task","/update-task"})
public class TaskController extends HttpServlet{
   ProjectServiceImp projectService  = new ProjectService();
   UserServiceImp userService = new UserService();
   ConvertTime time = new ConvertTime();
   TaskServiceImp taskService= new TaskService();
   StatusService statusService =new StatusService();
   Validation validation = new Validation();
   @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	   
	   String path = req.getServletPath();
	   EnumUrlParam checkPath = EnumUrlParam.fromPath(path);
	   switch (checkPath) {
	case ADD_TASK:
		addTask(req, resp);
		break;
	case TASK:
		showListTask(req, resp);
		break;
	case UPDATE_TASK:
		updateTask(req, resp);
		break;
	default:
		break;
	}
}
   public void showListTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	  
	   int id = 0;
	  String roles = "";
	  Cookie[] cookies = req.getCookies();
	  List<TaskDTO> listTasks= taskService.getAllTask();
	  for (Cookie cookie : cookies) {
		if(cookie.getName().equals("role")) {
			roles = cookie.getValue();
		}
		if(cookie.getName().equals("id")) {
			id = Integer.parseInt(cookie.getValue());
		}
	}
	   switch (roles) {
	case "LEADER":
		List<Integer> listIdProjectByIdLeader = projectService.getProjectByIdLeader(72);
		List<TaskDTO> listTaskByLeader = new ArrayList<TaskDTO>();
		
		for (Integer integer : listIdProjectByIdLeader) {
			for (TaskDTO tasks : listTasks) {
				if(tasks.idProject()==integer) {
					listTaskByLeader.add(tasks);
				}
			}
		}
		req.setAttribute("listtask", listTaskByLeader);
		break;

	default:
		req.setAttribute("listtask", listTasks);
		break;
	}
	   
		
		
		req.getRequestDispatcher("task.jsp").forward(req, resp);
}
   
   public void addTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	   List<ProjectDTO> listProject = projectService.getListProject();
	   List<UserEntity> listUser = userService.getListUser();
	   List<UserEntity> listMember = new ArrayList<UserEntity>();
	   
	   for (UserEntity userEntity : listUser) {
		  
		if(userEntity.getRole().getName().equals("USER")) {
			
			listMember.add(userEntity);
		}
	   }
	   
	   req.setAttribute("listproject", listProject);
	   req.setAttribute("listuser", listMember);
	   req.getRequestDispatcher("task-add.jsp").forward(req, resp);
}
   public void updateTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	   List<ProjectDTO> listProject = projectService.getListProject();
	   List<UserEntity> listUser = userService.getListUser();
	   req.setAttribute("listproject", listProject);
	   req.setAttribute("listuser", listUser);
	   List<StatusDTO> listStatus = statusService.getAllStatus();
	   req.setAttribute("liststatus", listStatus);
	   
	   int id = Integer.parseInt(req.getParameter("id"));
	   TaskByIdDTO tasks = taskService.getTaskById(id);
	   req.setAttribute("task", tasks);
	   req.getRequestDispatcher("task-update.jsp").forward(req, resp);
}
   
   @SuppressWarnings("deprecation")
@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int idProject = Integer.parseInt(req.getParameter("idProject"));
		int idUser = Integer.parseInt(req.getParameter("idUser"));
		String name = req.getParameter("name");
		Timestamp fisrtDate = time.convertStringToTimetamp(req.getParameter("first"));
		Timestamp lastDate = time.convertStringToTimetamp(req.getParameter("last"));
		LocalDate localFirst = fisrtDate.toLocalDateTime().toLocalDate();
		LocalDate localLast = lastDate.toLocalDateTime().toLocalDate();
		ProjectDTO project = projectService.getProjectById(idProject);
		String path = req.getServletPath();
		EnumUrlParam checkPath = EnumUrlParam.fromPath(path);
		if(fisrtDate.getYear()>=70&& lastDate.getYear()>=70
				&&fisrtDate.compareTo(lastDate)<0
				&&localFirst.compareTo(project.startTime())>=0
				&&localFirst.compareTo(project.endTime())<=0
				&&localLast.compareTo(project.endTime())<=0
				&&validation.checkNullOrEmpty(name, "tên công việc")) {
			TaskEntity tasks = new TaskEntity(0, idUser, idProject, 3, name, fisrtDate, lastDate);
			
			switch (checkPath) {
			case ADD_TASK:
				 taskService.addTask(tasks);
				break;
			case UPDATE_TASK:
				int id = Integer.parseInt(req.getParameter("id"));
				int idStatus = Integer.parseInt(req.getParameter("idStatus"));
				tasks.setId(id);
				tasks.setIdStatus(idStatus);
				taskService.updateTask(tasks);
				break;
			default:
				break;
			}
		   
		    String context = req.getContextPath();
		    resp.sendRedirect(context+EnumUrlParam.TASK.getEndpoint());
		}else {
			System.out.println("ngay nhap khong dung dinh dang");
			if(fisrtDate.compareTo(lastDate)>0) {
				System.out.println("ngay bat dau lon hon ngay ket thuc");
			}else if (localFirst.compareTo(project.startTime())<0) {
				System.out.println("ngay bat dau: "+localFirst+" truoc ngay bat dau du an: "+project.startTime());
			}else if (localFirst.compareTo(project.endTime())>0) {
				System.out.println("ngay bat dau: "+localFirst+" sau ngay ket thuc du an: "+project.endTime());
			}else if (localLast.compareTo(project.endTime())>0) {
				System.out.println("ngay ket thuc: "+localLast+" sau ngay ket thuc du an: "+project.endTime());
			}
			
			switch (checkPath) {
			case ADD_TASK:
				addTask(req, resp);
				break;
			case UPDATE_TASK:
				updateTask(req, resp);
			default:
				break;
			}
			
		}
		
	}
}
