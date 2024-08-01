package crm.service;

import java.util.List;

import crm.dto.ProjectDTO;
import crm.dto.ProjectTaskDTO;
import crm.entity.ProjectEntity;

public interface ProjectServiceImp {
      boolean addProject(ProjectEntity projectEntity);
      List<ProjectDTO> getListProject();
      boolean deleteProjectById(int id);
      ProjectDTO getProjectById(int id);
      boolean updateProject(ProjectEntity projectEntity);
      List<ProjectTaskDTO> getProjectDetailById(int id);
      List<ProjectTaskDTO> checkStatus(List<ProjectTaskDTO> listTasks,int found);
      List<Integer> getProjectByIdLeader(int id);
}
